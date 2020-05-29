package sbnz.integracija.example.service;

import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.dto.PatientDTO;
import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Country.DevelompentLevel;
import sbnz.integracija.example.model.Patient.CovidStatus;
import sbnz.integracija.example.model.Patient.Risk;
import sbnz.integracija.example.model.Patient;
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
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		Patient p = convertPatientDTOToPatient(pDTO);
		// ako nesto ne nadje dobro vrati null, i vratice bad request kao odgovor
		if (p == null) {
			return null;
		}
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		kSession.insert(p);
		int fired = kSession.fireAllRules();
		
		System.out.println("DEVELOMPENT: " + p.getCountry().getCountryDevelopmentLevel());
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("MERE LECENJA: " + p.getCuringMeasures());
		
		patientRepository.save(p);
		
		for (Patient pCheckAgain : p.getContactedPatients()) {
			System.out.println("CHECKING AGAIN FOR PATIENT WITH ID: " + pCheckAgain.getId());
			kSession.dispose();
			kSession = kContainer.newKieSession();
			kSession.setGlobal("myLogger", ml);
			kSession.insert(pCheckAgain);
			fired = kSession.fireAllRules();
			
			System.out.println("DEVELOMPENT: " + pCheckAgain.getCountry().getCountryDevelopmentLevel());
			System.out.println("PUCANA PRAVILA: " + fired);
			System.out.println("MERE LECENJA: " + pCheckAgain.getCuringMeasures());
			
			patientRepository.save(pCheckAgain);
		}
		
		System.out.println("CHECKING AGAIN FOR COUNTRY WITH ID: " + p.getCountry().getId());
		kSession.dispose();
		kSession = kContainer.newKieSession();
		kSession.setGlobal("myLogger", ml);
		kSession.insert(p.getCountry());
		fired = kSession.fireAllRules();
		return new PatientDTO(p);

	}
	
	public Country getCountryDevelopmentLevel(Country c) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kieSession = kContainer.newKieSession();
		
		System.out.println("--- APPLYING RULES FOR COUNTRY ---");
		System.out.println("--- Current state of the country ---");
		System.out.println(c.toString() + "\n");
		c.setCountryDevelopmentLevel(DevelompentLevel.UNKNOWN);
		
		MyLogger ml = new MyLogger();
		kieSession.setGlobal("myLogger", ml);

		kieSession.insert(c);
		int fired = kieSession.fireAllRules();
		
		System.out.println("--- Aftermath state of the country ---");
		System.out.println("Country: " + c.getCountryName());
		System.out.println("Country IDVI index: " + c.getIdvIndex());
		System.out.println("Country development level: " + c.getCountryDevelopmentLevel().toString());
		System.out.println("Number of rules fired: " + fired + "\n");
		return c;
	}
	
	private Patient convertPatientDTOToPatient(PatientDTO pDTO) {
		Patient p = new Patient();
		if (pDTO.getId() != -1) {
			p.setId(pDTO.getId());
		}
		
		Country c = null;
		try {
			c = countryRepository.findById(pDTO.getCountryId()).orElseThrow(Exception::new);
		}
		catch(Exception e) {
			return null;
		}
		
		p.setName(pDTO.getName());
		p.setLastname(pDTO.getLastName());
		p.setCountry(c);
		p.setCovidPositiveContact(pDTO.isCovidPositiveContact());
		p.setLastFever(pDTO.getLastFever());
		p.setContactedPatients(new ArrayList<Patient>());
		for (Long contactedP : pDTO.getContactedPatients()) {
			Patient pPom = null;
			try {
				pPom = patientRepository.findById(contactedP).orElseThrow(Exception::new);
				// da se doda na obe strane
				pPom.getContactedPatientsByMe().add(p);
				patientRepository.save(pPom);
			}
			catch (Exception e) {
				System.out.println("PATEINT NOT FOUND FOR LIST OF CONTACTED PATIENTS");
				continue;
			}
			p.getContactedPatients().add(pPom);
		}
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
}
