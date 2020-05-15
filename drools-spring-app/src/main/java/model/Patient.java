package model;

import java.io.Serializable;
import java.util.List;

public class Patient implements Serializable{
	
	public enum Risk {
        LOW, HIGH, UNKNOWN
    };
    
    public enum CovidStatus {
        POSITIVE, NEGATIVE, UNKNOWN
    };
    
	private long id;
	private String name;
	private String lastname;
	private Country country; //dodao
	private boolean covidPositiveContact;
	private double lastFever;
	private Risk riskOfCovid;
	private List<Patient> contactedPatients;
	private List<Country> countriesVisited;
	private int respiratoryRate; //u zavisnosti od respiratory rate-a, racunace se tachnypnea u pravilima
	private boolean hypoxia;
	private boolean soreThroat;
	private boolean cough;
	private boolean dyspnea;
	private boolean tachnypnea;
	private double alc; //apsolute lymphocite count
	private boolean pneumonia;
	private double oxygenSaturation;
	private CovidStatus covidStatus;
	private String curingMeasures;
	private CovidStatus testResults;
	private boolean shouldDoTest;
	
	private Risk coughOrFeverRisk;
	private Risk contactRisk;
	private Risk oxygenRisk;
	
	public Patient() {
		this.curingMeasures = "";
		this.testResults = CovidStatus.UNKNOWN;
		this.shouldDoTest = false;
		
		this.coughOrFeverRisk = Risk.UNKNOWN;
		this.contactRisk = Risk.UNKNOWN;
		this.oxygenRisk = Risk.UNKNOWN;
	}

	public Risk getCoughOrFeverRisk() {
		return coughOrFeverRisk;
	}

	public void setCoughOrFeverRisk(Risk coughOrFeverRisk) {
		this.coughOrFeverRisk = coughOrFeverRisk;
	}
	
	public Risk getContactRisk() {
		return contactRisk;
	}

	public void setContactRisk(Risk contactRisk) {
		this.contactRisk = contactRisk;
	}
	
	public Risk getOxygenRisk() {
		return oxygenRisk;
	}

	public void setOxygenRisk(Risk oxygenRisk) {
		this.oxygenRisk = oxygenRisk;
	}


	public boolean getShouldDoTest() {
		return shouldDoTest;
	}
	
	public void setShouldDoTest(boolean shouldDoTest) {
		this.shouldDoTest = shouldDoTest;
	}

	public String getCuringMeasures() {
		return curingMeasures;
	}

	public void setCuringMeasures(String curingMeasures) {
		this.curingMeasures = curingMeasures;
	}

	public CovidStatus getTestResults() {
		return testResults;
	}

	public void setTestResults(CovidStatus testResults) {
		this.testResults = testResults;
	}

	public double getOxygenSaturation() {
		return oxygenSaturation;
	}



	public void setOxygenSaturation(double oxygenSaturation) {
		this.oxygenSaturation = oxygenSaturation;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public boolean isCovidPositiveContact() {
		return covidPositiveContact;
	}

	public void setCovidPositiveContact(boolean covidPositiveContact) {
		this.covidPositiveContact = covidPositiveContact;
	}

	public double getLastFever() {
		return lastFever;
	}

	public void setLastFever(double lastFever) {
		this.lastFever = lastFever;
	}

	public Risk getRiskOfCovid() {
		return riskOfCovid;
	}

	public void setRiskOfCovid(Risk riskOfCovid) {
		this.riskOfCovid = riskOfCovid;
	}

	public List<Patient> getContactedPatients() {
		return contactedPatients;
	}

	public void setContactedPatients(List<Patient> contactedPatients) {
		this.contactedPatients = contactedPatients;
	}

	public List<Country> getCountriesVisited() {
		return countriesVisited;
	}

	public void setCountriesVisited(List<Country> countriesVisited) {
		this.countriesVisited = countriesVisited;
	}

	public int getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(int respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public boolean getHypoxia() {
		return hypoxia;
	}

	public void setHypoxia(boolean hypoxia) {
		this.hypoxia = hypoxia;
	}

	public boolean isSoreThroat() {
		return soreThroat;
	}

	public void setSoreThroat(boolean soreThroat) {
		this.soreThroat = soreThroat;
	}

	public boolean isCough() {
		return cough;
	}

	public void setCough(boolean cough) {
		this.cough = cough;
	}

	public boolean isDyspnea() {
		return dyspnea;
	}

	public void setDyspnea(boolean dyspnea) {
		this.dyspnea = dyspnea;
	}

	public boolean isTachnypnea() {
		return tachnypnea;
	}

	public void setTachnypnea(boolean tachnypnea) {
		this.tachnypnea = tachnypnea;
	}

	public double getAlc() {
		return alc;
	}

	public void setAlc(double alc) {
		this.alc = alc;
	}

	public boolean isPneumonia() {
		return pneumonia;
	}

	public void setPneumonia(boolean pneumonia) {
		this.pneumonia = pneumonia;
	}

	public CovidStatus getCovidStatus() {
		return covidStatus;
	}

	public void setCovidStatus(CovidStatus covidStatus) {
		this.covidStatus = covidStatus;
	}
	
	public void addTreatmentMeasure(String t) {
		String[] parsed = t.split("-");
		for (int i = 1; i < parsed.length; i++) {
			if(!this.curingMeasures.contains("-" + parsed[i])) {
				this.curingMeasures += "-" + parsed[i];
			}
		}
	}
	
	public void addOutputMeasure(String t) {
		this.curingMeasures = "";
		this.curingMeasures += t;
	}
	
	public void consoleOutputMeasure() {
		String[] parsed = this.getCuringMeasures().split("-");
		for (int i = 1; i < parsed.length; i++) {
			System.out.println("-" + parsed[i]);
		}
	}
	
}