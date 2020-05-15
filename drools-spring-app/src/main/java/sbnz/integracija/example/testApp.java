package sbnz.integracija.example;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.autoconfigure.cache.CacheType;

import model.Country;
import model.Patient;
import util.MyLogger;

public class testApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testClassifyPatient();

	}
	
	public static void testClassifyCountry() {
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
	
	public static void testClassifyPatient() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		
		Country c1 = new Country(1l, "Serbia", true, 0.24);
		Country c2 = new Country(1l, "NekaDrzava", false, 0.20);
		kSession.insert(c1);
		kSession.insert(c2);
		
		Patient p1 = new Patient();
		p1.setName("P1");
		int fired = kSession.fireAllRules();
		p1.setCountry(c1);
		p1.setLastFever(37.8);
		p1.setCovidPositiveContact(true);
		p1.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p1.setTestResults(Patient.CovidStatus.POSITIVE);
		p1.setRespiratoryRate(25);
		
		Patient p2 = new Patient();
		p2.setName("P2");
		p2.setCountry(c2);
		p2.setLastFever(37.5);
		p2.setCovidPositiveContact(false);
		List<Patient> contacted = new ArrayList<>();
		List<Country> countries = new ArrayList<>();
		contacted.add(p1);
		countries.add(c1);
		p2.setContactedPatients(contacted);
		p2.setCountriesVisited(countries);
		p2.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p2.setTestResults(Patient.CovidStatus.NEGATIVE);
		
		System.out.println("DEVELOMPENT: " + p1.getCountry().getCountryDevelopmentLevel());
		System.out.println("FEVER: " + p1.getLastFever());
		kSession.insert(p1);
		kSession.insert(p2);
		fired = kSession.fireAllRules();
		System.out.println("NUMBER OF RULES: " + fired);
		
		System.out.println("Pacijent 1 - Zdravstvene mere - " + p1.getName());
		p1.consoleOutputMeasure();
		
		System.out.println("\nPacijent 2 - Zdravstvene mere - " + p2.getName());
		p2.consoleOutputMeasure();
	}

}
