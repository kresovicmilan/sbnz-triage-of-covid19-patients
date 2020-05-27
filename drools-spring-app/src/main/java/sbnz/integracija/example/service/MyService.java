package sbnz.integracija.example.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Patient;
import sbnz.integracija.example.repository.CountryRepository;
import sbnz.integracija.example.util.MyLogger;

@Service
public class MyService {

	private final KieContainer kieContainer;
	
	@Autowired
	CountryRepository countryRepository;

	@Autowired
	public MyService(KieContainer kieContainer){
		this.kieContainer = kieContainer;
	}
	
	public Patient getCuringMeassures(Patient p, String cname) {
		/*KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		try {
			Country c = this.countryRepository.findByCountryName(cname).orElseThrow(Exception::new);
			p.setCountry(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		kSession.insert(p);
		int fired = kSession.fireAllRules();
		
		System.out.println("DEVELOMPENT: " + p.getCountry().getCountryDevelopmentLevel());
		System.out.println("PUCANA PRAVILA: " + fired);
		System.out.println("MERE LECENJA: " + p.getCuringMeasures());
		return p;*/
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
		return pHigh;
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
