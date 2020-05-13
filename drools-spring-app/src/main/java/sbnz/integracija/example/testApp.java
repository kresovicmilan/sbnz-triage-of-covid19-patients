package sbnz.integracija.example;

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
		
		Patient p = new Patient();
		Country c = new Country(1l, "Serbia", false, 0.24);
		kSession.insert(c);
		int fired = kSession.fireAllRules();
		p.setCountry(c);
		p.setLastFever(36.8);
		
		
		System.out.println("DEVELOMPENT: " + p.getCountry().getCountryDevelopmentLevel());
		System.out.println("FEVER: " + p.getLastFever());
		kSession.insert(p);
		fired = kSession.fireAllRules();
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("RISK: " + p.getRiskOfCovid());
	}

}
