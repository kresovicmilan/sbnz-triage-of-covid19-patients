package sbnz.integracija.example;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.autoconfigure.cache.CacheType;

import model.Country;
import model.Patient;

public class testApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testClassifyPatient();

	}
	
	public static void testClassifyCountry() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
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
		
		Patient p1 = new Patient();
		Country c = new Country(1l, "Serbia", true, 0.24);
		kSession.insert(c);
		int fired = kSession.fireAllRules();
		p1.setCountry(c);
		p1.setLastFever(36.8);
		p1.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		
		Patient p2 = new Patient();
		p2.setCountry(c);
		p2.setLastFever(37);
		p2.setCovidPositiveContact(false);
		List<Patient> contacted = new ArrayList<>();
		List<Country> countries = new ArrayList<>();
		contacted.add(p1);
		countries.add(c);
		p2.setContactedPatients(contacted);
		p2.setCountriesVisited(countries);
		
		System.out.println("DEVELOMPENT: " + p1.getCountry().getCountryDevelopmentLevel());
		System.out.println("FEVER: " + p1.getLastFever());
		kSession.insert(p1);
		kSession.insert(p2);
		fired = kSession.fireAllRules();
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("RISK p1: " + p1.getRiskOfCovid());
		System.out.println("RISK p2: " + p2.getRiskOfCovid());
	}

}