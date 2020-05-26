package sbnz.integracija.example.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Patient;
import util.MyLogger;

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
		int fired = kSession.fireAllRules();

		kSession.insert(p);
		fired = kSession.fireAllRules();
		
		System.out.println("DEVELOMPENT: " + p.getCountry().getCountryDevelopmentLevel());
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("MERE LECENJA: " + p.getCuringMeasures());
		return p;
	}
}
