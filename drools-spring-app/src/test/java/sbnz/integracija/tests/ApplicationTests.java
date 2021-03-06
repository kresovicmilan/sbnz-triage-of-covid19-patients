package sbnz.integracija.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sbnz.integracija.example.event.ExaminationEvent;
import sbnz.integracija.example.model.AppUser;
import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Patient;
import sbnz.integracija.example.service.MyService;
import sbnz.integracija.example.model.Country.DevelompentLevel;
import sbnz.integracija.example.model.Examination;
import sbnz.integracija.example.util.MyLogger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private MyService myService;
	
	@Test
	public void contextLoads() {
	}
	
	// COUNTRY IDV INDEX TEST, COUNTRY HAS HIGH IDV INDEX
	@Test
	public void testCountry_HighIDVI() {
		
		KieSession kSession =  setupConfig();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		Country c = new Country(1l, "Serbia", false, 0.34);
		kSession.insert(c);
		kSession.fireAllRules();

		
		assertEquals(c.getCountryDevelopmentLevel(), DevelompentLevel.HIGH);
	}
	
	// COUNTRY IDV INDEX TEST, COUNTRY HAS LOW IDV INDEX
	@Test
	public void testCountry_LowIDVI() {
		
		KieSession kSession =  setupConfig();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		
		Country c = new Country(1l, "Serbia", false, 0.14);
		kSession.insert(c);
		kSession.fireAllRules();
		
		assertEquals(c.getCountryDevelopmentLevel(), DevelompentLevel.LOW);
	}

	
	// PATIENT LOW IDV INDEX COUNTRY AND COVID NEGATIVE
	// Prijem u predvidjeno odeljenje za podrzavajucu negu
	@Test
	public void testClassifyPatientLowIdvIndex1() {
		
		KieSession kSession =  setupConfig();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		Country c1 = new Country(1l, "Serbia", true, 0.24);
		kSession.insert(c1);
		kSession.fireAllRules();
		
		
		//Patient 1
		Patient p1 = new Patient();
		p1.setName("P1");
		p1.setCountry(c1);
		p1.setLastFever(37.8);
		p1.setCovidPositiveContact(true);
		p1.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p1.setTestResults(Patient.CovidStatus.POSITIVE);
		p1.setRespiratoryRate(25);
		Date d1 = new Date();
		p1.setAddTimestamp(d1);
		
		kSession.insert(p1);
		kSession.fireAllRules();
		p1.consoleOutputMeasure();
		
		assertEquals(0, p1.getCOVID19Positive());
		assertEquals("- Prijem u predvidjeno odeljenje za podrzavajucu negu", p1.getCuringMeasures());
	}
	
	// PATIENT LOW IDV INDEX COUNTRY AND COVID NEGATIVE
	// Razmotriti druge uzroke i ponoviti bris ako simptomi potraju 2 dana
	@Test
	public  void testClassifyPatientLowIdvIndex2() {
		
		KieSession kSession =  setupConfig();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);
		
		Country c1 = new Country(1l, "Serbia", true, 0.24);
		Country c2 = new Country(1l, "NekaDrzava", false, 0.20);
		kSession.insert(c1);
		kSession.insert(c2);
		kSession.fireAllRules();
		
		//Patient 2
		Patient p2 = new Patient();
		p2.setName("P2");
		p2.setCountry(c2);
		p2.setLastFever(37.5);
		p2.setCovidPositiveContact(false);
		List<Patient> contacted = new ArrayList<>();
		List<Country> countries = new ArrayList<>();
		countries.add(c1);
		p2.setContactedPatients(contacted);
		p2.setCountriesVisited(countries);
		p2.setCovidStatus(Patient.CovidStatus.UNKNOWN);
		p2.setTestResults(Patient.CovidStatus.NEGATIVE);
		Date d1 = new Date();
		p2.setAddTimestamp(d1);
		
		kSession.insert(p2);
		kSession.fireAllRules();
		
		assertEquals("- Razmotriti druge uzroke i ponoviti bris ako simptomi potraju 2 dana", p2.getCuringMeasures());
	}
	
	// PATIENT HIGH IDV INDEX COUNTRY AND COVID POSITIVE
	// Mera 4 zbog dyspnea, moze biti i mera 5(ovaj ispis zbog provere)Mera 5, mora biti sa merom 3 ili 4(ovaj ispis zbog provere)
	@Test
	public void testClassifyPatientHighIdvIndex1() {
		
		KieSession kSession =  setupConfig();

		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		// DRZAVE
		Country cHigh = new Country(2l, "Serbia", true, 0.5);
		kSession.insert(cHigh);
		kSession.fireAllRules();
		
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
		Date d1 = new Date();
		pHigh.setAddTimestamp(d1);
		
		kSession.insert(pHigh);
		kSession.fireAllRules();

		assertEquals("Mera 4 zbog dyspnea, moze biti i mera 5(ovaj ispis zbog provere)Mera 5, mora biti sa merom 3 ili 4(ovaj ispis zbog provere)", pHigh.getCuringMeasures());
	}
	
	// PATIENT HIGH IDV INDEX COUNTRY AND COVID NEGATIVE
	// Mera 1, mora biti samo mera 1(ovaj ispis zbog provere)
	@Test
	public void testClassifyPatientHighIdvIndex2() {

		KieSession kSession =  setupConfig();
		
		MyLogger ml = new MyLogger();
		kSession.setGlobal("myLogger", ml);

		// DRZAVE
		Country cHigh = new Country(2l, "Serbia", true, 0.5);
		
		kSession.insert(cHigh);
		kSession.fireAllRules();
		
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
		Date d1 = new Date();
		pHigh.setAddTimestamp(d1);
		
		kSession.insert(pHigh);
		kSession.fireAllRules();
		
		assertEquals("Mera 1, mora biti samo mera 1(ovaj ispis zbog provere)", pHigh.getCuringMeasures());

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
