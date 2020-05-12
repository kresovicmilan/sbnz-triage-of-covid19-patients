package sbnz.integracija.example;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import model.Country;

public class testApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testClassifyCountry();

	}
	
	public static void testClassifyCountry() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		Country c = new Country(1l, "Serbia", 0.2, false, false);
		System.out.println("LOW INDEKS: " + c.isLowIdvIndex());
		kSession.insert(c);
		int fired = kSession.fireAllRules();
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("LOW INDEKS: " + c.isLowIdvIndex());
	}

}
