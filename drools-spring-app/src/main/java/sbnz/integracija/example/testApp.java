package sbnz.integracija.example;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.autoconfigure.cache.CacheType;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Patient;
import util.MyLogger;

public class testApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Country has high IDV index
		//testClassifyCountryHighIdvIndex();
		
		// Country has low IDV index
//		testClassifyCountryLowIdvIndex();
		
		// Patient has cold, dyspnea and is COVID-19 positive
		//testClassifyPatientHighIdvIndex1();
		
		// Patient has cold and lymphocyte count is low
		//testClassifyPatientHighIdvIndex2();
		
		// Patients have been in contact with each other
		//testClassifyPatientLowIdvIndex1();
		
		// Patients have not been in contact with each other
		//testClassifyPatientLowIdvIndex2();
		
		testClassifyPatientLowIdvIndex1();
		
	}
	
	public static void testClassifyCountryHighIdvIndex() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		
		Country c = new Country(1l, "Serbia", false, 0.34);
		System.out.println("DEVELOMPENT: " + c.getCountryDevelopmentLevel());
		System.out.println("INDEKS: " + c.getIdvIndex());
		kSession.insert(c);
		int fired = kSession.fireAllRules();
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("DEVELOMPNET: " + c.getCountryDevelopmentLevel());
	}

	public static void testClassifyCountryLowIdvIndex() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		
		Country c = new Country(1l, "Serbia", false, 0.14);
		System.out.println("DEVELOMPENT: " + c.getCountryDevelopmentLevel());
		System.out.println("INDEKS: " + c.getIdvIndex());
		kSession.insert(c);
		int fired = kSession.fireAllRules();
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("DEVELOMPNET: " + c.getCountryDevelopmentLevel());
	}
	
	public static void testClassifyPatientLowIdvIndex1() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		
		Country c1 = new Country(1l, "Serbia", true, 0.24);
		Country c2 = new Country(1l, "NekaDrzava", false, 0.20);
		kSession.insert(c1);
		kSession.insert(c2);
		int fired = kSession.fireAllRules();
		
		//Patient 1
		Patient p1 = new Patient();
		p1.setName("P1");
		p1.setCountry(c1);
		p1.setLastFever(37.8);
		p1.setCovidPositiveContact(true);
		p1.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p1.setTestResults(Patient.CovidStatus.POSITIVE);
		p1.setRespiratoryRate(25);
		
		//Patient 2
		Patient p2 = new Patient();
		p2.setName("P2");
		p2.setCountry(c2);
		p2.setLastFever(37.5);
		p2.setCovidPositiveContact(false);
		List<Patient> contacted = new ArrayList<>();
		List<Country> countries = new ArrayList<>();
		contacted.add(p1);
		//countries.add(c1);
		p2.setContactedPatients(contacted);
		p2.setCountriesVisited(countries);
		p2.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p2.setTestResults(Patient.CovidStatus.NEGATIVE);
		
		System.out.println("---------------------");
		for(Patient p : p2.getContactedPatients()) {
			System.out.println(p.getName());
		}
		System.out.println("---------------------");
		
		System.out.println("DEVELOPMENT: " + p1.getCountry().getCountryDevelopmentLevel());
		System.out.println("FEVER: " + p1.getLastFever());
		
		kSession.insert(p1);
		fired = kSession.fireAllRules();
		
		KieServices ks2 = KieServices.Factory.get();
		KieContainer kContainer2 = ks2.getKieClasspathContainer();
		KieSession kSession2 = kContainer2.newKieSession();
		
		//kSession2.insert(c1);
		//kSession2.insert(c2);
		MyLogger ml2 = new MyLogger();
		kSession2.setGlobal("myLogger", ml2);
		p1.setLastFever(38.7);
		p1.setCough(true);
		kSession2.insert(p2);
		kSession2.insert(p1);
		p1.setCough(false);
		p1.setLastFever(35.9);
		kSession2.insert(p1);
		fired = kSession2.fireAllRules();
		
		System.out.println("NUMBER OF RULES: " + fired);
		
		System.out.println("\nPacijent 1 - Zdravstvene mere - " + p1.getName());
		p1.consoleOutputMeasure();
		
		System.out.println("\nPacijent 2 - Zdravstvene mere - " + p2.getName());
		p2.consoleOutputMeasure();
		
	}
	
	public static void testClassifyPatientLowIdvIndex2() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		
		Country c1 = new Country(1l, "Serbia", true, 0.24);
		Country c2 = new Country(1l, "NekaDrzava", false, 0.20);
		kSession.insert(c1);
		kSession.insert(c2);
		int fired = kSession.fireAllRules();
		
		//Patient 1
		Patient p1 = new Patient();
		p1.setName("P1");
		p1.setCountry(c1);
		p1.setLastFever(37.8);
		p1.setCovidPositiveContact(true);
		p1.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p1.setTestResults(Patient.CovidStatus.POSITIVE);
		p1.setRespiratoryRate(25);
		
		//Patient 2
		Patient p2 = new Patient();
		p2.setName("P2");
		p2.setCountry(c2);
		p2.setLastFever(37.5);
		p2.setCovidPositiveContact(false);
		List<Patient> contacted = new ArrayList<>();
		List<Country> countries = new ArrayList<>();
		//contacted.add(p1);
		countries.add(c1);
		p2.setContactedPatients(contacted);
		p2.setCountriesVisited(countries);
		p2.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p2.setTestResults(Patient.CovidStatus.NEGATIVE);
		
		System.out.println("DEVELOPMENT: " + p1.getCountry().getCountryDevelopmentLevel());
		System.out.println("FEVER: " + p1.getLastFever());
		
		kSession.insert(p1);	
		kSession.insert(p2);
		fired = kSession.fireAllRules();
		
		System.out.println("NUMBER OF RULES: " + fired);
		
		System.out.println("\nPacijent 1 - Zdravstvene mere - " + p1.getName());
		p1.consoleOutputMeasure();
		
		System.out.println("\nPacijent 2 - Zdravstvene mere - " + p2.getName());
		p2.consoleOutputMeasure();
		
	}
	
	public static void testClassifyPatientHighIdvIndex1() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		// DRZAVE
		Country cLow = new Country(1l, "Somalia", true, 0.24);
		Country cHigh = new Country(2l, "Serbia", true, 0.5);
		
		kSession.insert(cLow);
		kSession.insert(cHigh);
		int fired = kSession.fireAllRules();
		
		// PACIJENT HIGH
		Patient pHigh = new Patient();
		// Patient details
		pHigh.setName("Test");
		pHigh.setLastname("Testic");
		pHigh.setCountry(cHigh);
		
		// Input values - ovo se menja
		pHigh.setCold(true);
		pHigh.setSoreThroat(true);
		pHigh.setCough(true);

		pHigh.setDyspnea(true);
		pHigh.setOxygenSaturation(85); // ako je < 93 onda je hypoxia true
		
		pHigh.setLastFever(35);
		pHigh.setLymphocyteCount(1300); // ako je < 1100 onda je bolestan
		
		pHigh.setHasPneumonia(-1);
		pHigh.setHasNonHospitalPneumonia(-1);
		pHigh.setCOVID19Positive(1);
		
		// Initial values for variables - ovo sam program menja i inicijalno je ovako
		pHigh.setHasColdSoreThroatOrCough(-1);
		pHigh.setHasDyspneaOrHypoxia(-1);
		pHigh.setHasFever(-1);
		pHigh.setHasLowLymphocytes(-1);
		
		kSession.insert(pHigh);
		fired = kSession.fireAllRules();

		System.out.println("DEVELOMPENT: " + pHigh.getCountry().getCountryDevelopmentLevel());
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("MERE LECENJA: " + pHigh.getCuringMeasures());
	}

	public static void testClassifyPatientHighIdvIndex2() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		// DRZAVE
		Country cLow = new Country(1l, "Somalia", true, 0.24);
		Country cHigh = new Country(2l, "Serbia", true, 0.5);
		
		kSession.insert(cLow);
		kSession.insert(cHigh);
		int fired = kSession.fireAllRules();
		
		// PACIJENT HIGH
		Patient pHigh = new Patient();
		// Patient details
		pHigh.setName("Test");
		pHigh.setLastname("Testic");
		pHigh.setCountry(cHigh);
		
		// Input values - ovo se menja
		pHigh.setCold(true);
		pHigh.setSoreThroat(true);
		pHigh.setCough(true);

		pHigh.setDyspnea(false);
		pHigh.setOxygenSaturation(88); // ako je > 93 onda je hypoxia true
		
		pHigh.setLastFever(35);
		pHigh.setLymphocyteCount(900); // ako je < 1100 onda je bolestan
		
		pHigh.setHasPneumonia(-1);
		pHigh.setHasNonHospitalPneumonia(-1);
		pHigh.setCOVID19Positive(1);
		
		// Initial values for variables - ovo sam program menja i inicijalno je ovako
		pHigh.setHasColdSoreThroatOrCough(-1);
		pHigh.setHasDyspneaOrHypoxia(-1);
		pHigh.setHasFever(-1);
		pHigh.setHasLowLymphocytes(-1);
		
		kSession.insert(pHigh);
		fired = kSession.fireAllRules();

		System.out.println("DEVELOMPENT: " + pHigh.getCountry().getCountryDevelopmentLevel());
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("MERE LECENJA: " + pHigh.getCuringMeasures());
	}
}
