package sbnz.integracija.example.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Patient;
import sbnz.integracija.example.util.MyLogger;

@Service
public class MyService {

	private final KieContainer kieContainer;

	@Autowired
	public MyService(KieContainer kieContainer){
		this.kieContainer = kieContainer;
	}
	
	public Patient getCuringMeassures(Patient p) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		kSession.insert(p);
		int fired = kSession.fireAllRules();
		
		System.out.println("DEVELOMPENT: " + p.getCountry().getCountryDevelopmentLevel());
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("MERE LECENJA: " + p.getCuringMeasures());
		return p;
	}
	
	public Country getCountryDevelopmentLevel(Country c) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kieSession = kContainer.newKieSession();
		
		System.out.println(c.toString());
		
		MyLogger ml = new MyLogger();
		kieSession.setGlobal("myLogger", ml);

		kieSession.insert(c);
		int fired = kieSession.fireAllRules();
		
		System.out.println("Get country development level\n");
		System.out.println("Country: " + c.getCountryName() + "\n");
		System.out.println("Country IDVI index: " + c.getIdvIndex() + "\n");
		System.out.println("Number of rules fired: " + fired + "\n");
		System.out.println("Country development level: " + c.getCountryDevelopmentLevel().toString() + "\n");
		return c;
	}
}
