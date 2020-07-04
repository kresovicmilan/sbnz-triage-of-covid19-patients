package sbnz.integracija.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.dto.PatientDTO;
import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Country.DevelompentLevel;
import sbnz.integracija.example.model.Patient.CovidStatus;
import sbnz.integracija.example.model.Patient.Risk;
import sbnz.integracija.example.model.Patient;
import sbnz.integracija.example.model.PatientReport;
import sbnz.integracija.example.repository.CountryRepository;
import sbnz.integracija.example.repository.PatientRepository;
import sbnz.integracija.example.util.MyLogger;

@Service
public class MyService {

	private final KieContainer kieContainer;

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	PatientRepository patientRepository;

	@Autowired
	public MyService(KieContainer kieContainer){
		this.kieContainer = kieContainer;
	}
	
	public PatientDTO getCuringMeassures(PatientDTO pDTO) {
		KieSession kieSession =  setupConfig();

		
		
		Patient p = convertPatientDTOToPatient(pDTO);
		// ako nesto ne nadje dobro vrati null, i vratice bad request kao odgovor
		if (p == null) {
			return null;
		}
		
		MyLogger ml = new MyLogger();
		kieSession.setGlobal("myLogger", ml);

		kieSession.insert(p);
		int fired = kieSession.fireAllRules();
		
		System.out.println("DEVELOMPENT: " + p.getCountry().getCountryDevelopmentLevel());
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("MERE LECENJA: " + p.getCuringMeasures());
		
		patientRepository.save(p);
		
		for (Patient pCheckAgain : p.getContactedPatients()) {
			System.out.println("CHECKING AGAIN FOR PATIENT WITH ID: " + pCheckAgain.getId());
			kieSession.dispose();
			//kieSession = kContainer.newKieSession();
			kieSession =  setupConfig();
			// ovu gore liniju dodao
			kieSession.setGlobal("myLogger", ml);
			kieSession.insert(pCheckAgain);
			fired = kieSession.fireAllRules();
			
			System.out.println("DEVELOMPENT: " + pCheckAgain.getCountry().getCountryDevelopmentLevel());
			System.out.println("PUCANA PRAVILA: " + fired);
			System.out.println("MERE LECENJA: " + pCheckAgain.getCuringMeasures());
			
			patientRepository.save(pCheckAgain);
		}
		
		System.out.println("CHECKING AGAIN FOR COUNTRY WITH ID: " + p.getCountry().getId());
		kieSession.dispose();
		//kieSession = kContainer.newKieSession();
		kieSession =  setupConfig();
		// ovu gore liniju dodao
		kieSession.setGlobal("myLogger", ml);
		kieSession.insert(p.getCountry());
		fired = kieSession.fireAllRules();
		
		System.out.println("Country: " + p.getCountry().getCountryName());
		System.out.println("Country IDVI index: " + p.getCountry().getIdvIndex());
		System.out.println("Country development level: " + p.getCountry().getCountryDevelopmentLevel().toString());
		System.out.println("Number of rules fired: " + fired + "\n");
		
		countryRepository.save(p.getCountry());
		
		return new PatientDTO(p);

	}
	
	public Country getCountryDevelopmentLevel(Country c) {
		KieSession kieSession =  setupConfig();
		
		
		System.out.println("--- APPLYING RULES FOR COUNTRY ---");
		System.out.println("--- Current state of the country ---");
		System.out.println(c.toString() + "\n");
		c.setCountryDevelopmentLevel(DevelompentLevel.UNKNOWN);
		

		kieSession.insert(c);
		int fired = kieSession.fireAllRules();
		
		System.out.println("--- Aftermath state of the country ---");
		System.out.println("Country: " + c.getCountryName());
		System.out.println("Country IDVI index: " + c.getIdvIndex());
		System.out.println("Country development level: " + c.getCountryDevelopmentLevel().toString());
		System.out.println("Number of rules fired: " + fired + "\n");
		
		countryRepository.save(c);
		return c;
	}
	
	private Patient convertPatientDTOToPatient(PatientDTO pDTO) {
		Patient p = new Patient();
		//try {
			//p = this.patientRepository.find
		//}
		System.out.println("PID: " + pDTO.getId());
		if (pDTO.getId() != 0) {
			try {
				p = patientRepository.findById(pDTO.getId()).orElseThrow(() -> new Exception());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;

			}
		}
		
		System.out.println("SISTEM 1");
		
		Country c = null;
		try {
			
			c = countryRepository.findById(pDTO.getCountryId()).orElseThrow(Exception::new);
		}
		catch(Exception e) {
			return null;
		}
		
		System.out.println("SISTEM 1.1");
		
		p.setAddTimestamp(new Date());
		
		p.setName(pDTO.getName());
		p.setLastname(pDTO.getLastName());
		p.setCountry(c);
		p.setCovidPositiveContact(pDTO.isCovidPositiveContact());
		p.setLastFever(pDTO.getLastFever());
		p.setContactedPatients(new ArrayList<Patient>());
		System.out.println("SISTEM 1.2");
		for (Long contactedP : pDTO.getContactedPatients()) {
			Patient pPom = null;
			try {

				Long tempLong = contactedP;
				System.out.println("LONG PACIJENTA JE: " + tempLong);
				pPom = patientRepository.findById(tempLong).orElseThrow(Exception::new);
				// da se doda na obe strane
				
				//pPom.getContactedPatientsByMe().add(p);
				//patientRepository.save(pPom); KAD SE OVO ODKOMENTARISE NE MOZE DA URADI
			}
			catch (Exception e) {
				System.out.println("PATEINT NOT FOUND FOR LIST OF CONTACTED PATIENTS");
				continue;
			}
			System.out.println("DODAJE PACIJENTA " + pPom.getName());
			p.getContactedPatients().add(pPom);
		}
		System.out.println("SISTEM 2");
		p.setCountriesVisited(new ArrayList<Country>());
		for (Long countryVisited : pDTO.getCountriesVisited()) {
			Country cPom = null;
			try {
				cPom = countryRepository.findById(countryVisited).orElseThrow(Exception::new);
				// dodaj u drzavu tog pacijenta 
				int addToListPom = 1;
				for (Patient visitedC : cPom.getPatientsVisited()) {
					if (visitedC.getId() == p.getId()) {
						addToListPom = 0;
					}
				}
				if (addToListPom == 1) {
					cPom.getPatientsVisited().add(p);
				}
				
			}
			catch (Exception e) {
				System.out.println("COUNTRY NOT FOUND FOR LIST OF VISITED COUNTRIES");
				continue;
			}
			p.getCountriesVisited().add(cPom);
		}
		System.out.println("SISTEM 3");
		p.setRespiratoryRate((int)pDTO.getRespiratoryRate());
		p.setHypoxia(pDTO.isHypoxia());
		p.setCold(pDTO.isHasCold());
		p.setSoreThroat(pDTO.isHasSoreThroat());
		p.setCough(pDTO.isHasCough());
		p.setDyspnea(pDTO.isHasDyspnea());
		p.setTachnypnea(pDTO.isHasTachypnea());
		p.setAlc(pDTO.getAcl());
		p.setPneumonia(pDTO.isPneumonia());
		p.setOxygenSaturation(pDTO.getOxygenSaturation());
		p.setCuringMeasures("");
		p.setLymphocyteCount(pDTO.getAcl());
		p.setNonHospitalPneumonia(pDTO.getNonHospitalPneumonia());
		// RESET TO UNKNOWN
		p.setHasColdSoreThroatOrCough(-1);
		p.setHasDyspneaOrHypoxia(-1);
		p.setHasFever(-1);
		p.setHasLowLymphocytes(-1);
		p.setHasPneumonia(-1);
		p.setCOVID19Positive(-1);
		p.setShouldDoTest(false);
		// RESET ENUMS
		p.setOxygenRisk(Risk.UNKNOWN);
		p.setContactRisk(Risk.UNKNOWN);
		p.setCoughOrFeverRisk(Risk.UNKNOWN);
		p.setCovidStatus(CovidStatus.UNKNOWN);
		p.setTestResults(CovidStatus.UNKNOWN);
		p.setRiskOfCovid(Risk.UNKNOWN);
		
		if (pDTO.isTestResults()) {
			p.setCOVID19Positive(1);
			p.setCovidStatus(Patient.CovidStatus.POSITIVE);
			p.setTestResults(CovidStatus.POSITIVE);
		} else {
			System.out.println("USAO U SET NEGATIVE");
			p.setCOVID19Positive(0);
			p.setCovidStatus(Patient.CovidStatus.NEGATIVE);
			p.setTestResults(CovidStatus.NEGATIVE);
		}
		
		
		// dodaj u drzavu tog pacijenta 
		int addToList = 1;
		for (Patient fromC : c.getPatient()) {
			if (fromC.getId() == p.getId()) {
				addToList = 0;
			}
		}
		if (addToList == 1) {
			c.getPatient().add(p);
		}
		return p;
	}
	
	public boolean addNewRule(String rule) {
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		
		long count = -7L;
		try (Stream<Path> files = Files.list(Paths.get(s + "/drlRules"))) {
		    count = files.count();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			FileUtils.writeStringToFile(new File(s + "/drlRules/test" + count + ".drl"), rule);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		KieServices ks = KieServices.Factory.get();
		KieRepository kr = ks.getRepository();
		KieFileSystem kfs = ks.newKieFileSystem();
		
		kfs.write("src/main/resources/sbnz/integracija/newRule" + count + ".drl", rule);
		KieBuilder kb = ks.newKieBuilder(kfs);
		kb.buildAll();
		return true;
	}
	
	public KieSession setupConfig() {
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
		KieSession kSession = kbase.newKieSession();
		return kSession;
	}
}
