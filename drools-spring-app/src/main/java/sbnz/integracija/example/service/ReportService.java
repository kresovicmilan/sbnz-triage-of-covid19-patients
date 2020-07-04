package sbnz.integracija.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.io.ResourceFactory;
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
	
	private KieSession machineKSession;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	EmailService emailService;

	@Autowired
	public ReportService(KieContainer kieContainer){
		this.kieContainer = kieContainer;
		setMachineStatusChecker();
	}
	
	@Scheduled(fixedDelay = 5000000)
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
				if (!fileName.equals("machine-rules.drl")) {
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
		}
		
		
		KieBuilder kb = ks.newKieBuilder(kfs);
		kb.buildAll();
		
		
		KieContainer kContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
		
		
		KieBase kbase = kContainer.newKieBase(kbconf);
		//KieBase kieBase = kContainer.getKieBase();
		//KieSession kieSession = kContainer.newKieSession();
		KieSession kieSession = kbase.newKieSession();
		
		MyLogger ml = new MyLogger();
		kieSession.setGlobal("myLogger", ml);
		int fired;
		
		patients = patientRepository.findAll();
		
		
		for(Patient p : patients) {
			if (!this.reports.containsKey(p.getId())) {
				reports.put(p.getId(), new PatientReport(1));
				System.out.println("\n---------------------\nMaking a new report\n---------------------\n");
			} else {
				// ovaj zivi
				if (p.getId() % 2 == 0) {
					System.out.println("OVAJ ZIVI PARAN BROJ ID");
					reports.put(p.getId(), new PatientReport(2L, reports.get(p.getId())));
				}
				// ovaj umire
				else {
					System.out.println("OVAJ UMIRE NEPARAN BROJ ID");
					reports.put(p.getId(), new PatientReport(reports.get(p.getId()), 1L));
				}
				System.out.println("\n---------------------\nChanging existing report\n---------------------\n");
			}
			
			System.out.println("Checking report of a patient with an id: " + p.getId());
			System.out.println("Patient report status before: " + reports.get(p.getId()).getReportCondition().toString());
			System.out.println("Patient temperature before: " + reports.get(p.getId()).getTemperature());
			System.out.println("Patient extreme value before: " + reports.get(p.getId()).getExtremeValue());

			kieSession.dispose();
			//kieSession = kContainer.newKieSession();
			
			kieSession = kbase.newKieSession();
			kieSession.setGlobal("myLogger", ml);
			kieSession.setGlobal("myValue", Integer.valueOf(1));
			kieSession.insert(reports.get(p.getId()));
			fired = kieSession.fireAllRules();
			
			this.machineKSession.insert(reports.get(p.getId()));
			
			System.out.println("Patient report status after: " + reports.get(p.getId()).getReportCondition().toString());
			System.out.println("Patient temperature after: " + reports.get(p.getId()).getTemperature());
			System.out.println("Patient extreme value after: " + reports.get(p.getId()).getExtremeValue());
		
			/*if (reports.get(p.getId()).getReportCondition().toString().equals("CRITICAL")) {
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
				
			}*/
		}
	}
	
	private void setMachineStatusChecker() {
		KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        
        Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		FileInputStream fis = null;

		File folder = new File(s + "/drlRules");
		try {
			fis = new FileInputStream(s + "/drlRules/" + "machine-rules.drl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kfs.write("src/main/resources/sbnz/integracija/" + "machine-rules.drl",
				ks.getResources().newInputStreamResource(fis));
        
        KieBuilder kbuilder = ks.newKieBuilder(kfs);
        kbuilder.buildAll();
        if (kbuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalArgumentException("Couldn't build knowledge module" + kbuilder.getResults());
        }
        KieContainer kContainer = ks.newKieContainer(kbuilder.getKieModule().getReleaseId());
        KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        kbconf.setOption(EventProcessingOption.STREAM);
        KieBase kbase = kContainer.newKieBase(kbconf);
        
        KieSessionConfiguration ksconf = ks.newKieSessionConfiguration();
        ksconf.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        this.machineKSession = kbase.newKieSession(ksconf, null);
        
        this.machineKSession.setGlobal("reportService", this);	
	}
	
	public void machineIsBroken() {
		System.out.println("\n---------------------\nSending an email to the doctor about the broken machine\n---------------------\n");
		/*try {
			emailService.sendEmail("dervy97@mailinator.com", "Machine broken", "Machine broke down, check on patient and fix the machine");
		} catch (MailException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Scheduled(fixedDelay = 5000000)
	private void checkIfMachineIsBroken() {
		System.out.println("\n---------------------\nChecking if machine is broken");
		int firedMachineStatus = this.machineKSession.fireAllRules();
		System.out.println("---------------------\n");
	}
	
	public int getPatientReportsNumber() {
		return reports.size();
	}
}
