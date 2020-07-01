package sbnz.integracija.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Patient;
import sbnz.integracija.example.model.PatientReport;
import sbnz.integracija.example.repository.CountryRepository;
import sbnz.integracija.example.repository.PatientRepository;
import sbnz.integracija.example.util.MyLogger;

@Service
public class ReportService {
	
	private final KieContainer kieContainer;
	private HashMap<Long, PatientReport> reports = new HashMap<Long, PatientReport>();
	private List<Patient> patients;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	EmailService emailService;

	@Autowired
	public ReportService(KieContainer kieContainer){
		this.kieContainer = kieContainer;
	}
	
	@Scheduled(fixedDelay = 120000)
	private void probaSchedule() {
		KieServices ks = KieServices.Factory.get();
		KieFileSystem kfs = ks.newKieFileSystem();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        kbconf.setOption(EventProcessingOption.STREAM);

		// Adding admin created rules to session
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		FileInputStream fis = null;
		// prodji kroz sve fileove u folderu
		File folder = new File(s + "/drlRules");
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles) {
			if (file.isFile()) {
				String fileName = file.getName();
				try {
					fis = new FileInputStream(s + "/drlRules/" + fileName);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				kfs.write("src/main/resources/sbnz/integracija/" + fileName,
						ks.getResources().newInputStreamResource(fis));
			}
		}
		
		
		KieBuilder kb = ks.newKieBuilder(kfs);
		kb.buildAll();
		
		
		KieContainer kContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
		KieBase kieBase = kContainer.getKieBase();
		KieSession kieSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kieSession.setGlobal("myLogger", ml);
		int fired;
		
		patients = patientRepository.findAll();
		for(Patient p : patients) {
			if (!this.reports.containsKey(p.getId())) {
				reports.put(p.getId(), new PatientReport(1));
				System.out.println("PRAVI NOVI REPORT\n\n");
			} else {
				reports.put(p.getId(), new PatientReport(p.getId(), reports.get(p.getId())));
				System.out.println("MENJA POSTOJECI REPORT REPORT\n\n");
			}
			
			System.out.println("CHECKING REPORT FOR PATIENT: " + p.getId());
			System.out.println("Patient report before: " + reports.get(p.getId()).getReportCondition().toString());
			System.out.println("Patient temperature before: " + reports.get(p.getId()).getTemperature());
			System.out.println("Patient extreme value before: " + reports.get(p.getId()).getExtremeValue());

			kieSession.dispose();
			kieSession = kContainer.newKieSession();
			kieSession.setGlobal("myLogger", ml);
			kieSession.insert(reports.get(p.getId()));
			fired = kieSession.fireAllRules();
			System.out.println("Patient report after: " + reports.get(p.getId()).getReportCondition().toString());
			System.out.println("Patient temperature after: " + reports.get(p.getId()).getTemperature());
			System.out.println("Patient extreme value after: " + reports.get(p.getId()).getExtremeValue());
		
			if (reports.get(p.getId()).getReportCondition().toString().equals("CRITICAL")) {
				try {
					emailService.sendEmail("dervy97@mailinator.com", "Critical patient", "Patient is in critical condition");
				} catch (MailException | MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if (reports.get(p.getId()).getReportCondition().toString().equals("DEAD")) {
				try {
					emailService.sendEmail("dervy97@mailinator.com", "Dead patient", "Patient is in dead condition");
				} catch (MailException | MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if (reports.get(p.getId()).getReportCondition().toString().equals("BROKEN_MACHINE")) {
				try {
					emailService.sendEmail("dervy97@mailinator.com", "Machine broken", "Machine broke down, check on patient and fix the machine");
				} catch (MailException | MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
