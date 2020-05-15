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
	private boolean shouldDoCovidTest;
	private CovidStatus testResults;
	// dodao
	private double lymphocyteCount;
	private int nonHospitalPneumonia; // -1 unknown, 0 negative, 1 positive
	private boolean cold; // cold je prehlada fever je temperatura
	// pomocni koraci za ispaljivanje pravila dodao
	private int hasColdSoreThroatOrCough; // -1 unknown, 0 negative, 1 positive
	private int hasDyspneaOrHypoxia; // -1 unknown, 0 negative, 1 positive
	private int hasFever; // -1 unknown, 0 negative, 1 positive
	private int hasLowLymphocytes; // -1 unknown, 0 negative, 1 positive
	private int hasPneumonia; // -1 unknown, 0 negative, 1 positive
	private int hasNonHospitalPneumonia; // -1 unknown, 0 negative, 1 positive
	private int COVID19Positive; // -1 unknown, 0 negative, 1 positive
	

	public Patient() {
		this.curingMeasures = "";
		this.shouldDoCovidTest = false;
		this.testResults = CovidStatus.UNKNOWN;
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


	public boolean isHypoxia() {
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


	public double getOxygenSaturation() {
		return oxygenSaturation;
	}


	public void setOxygenSaturation(double oxygenSaturation) {
		this.oxygenSaturation = oxygenSaturation;
	}


	public CovidStatus getCovidStatus() {
		return covidStatus;
	}


	public void setCovidStatus(CovidStatus covidStatus) {
		this.covidStatus = covidStatus;
	}


	public String getCuringMeasures() {
		return curingMeasures;
	}


	public void setCuringMeasures(String curingMeasures) {
		this.curingMeasures = curingMeasures;
	}


	public boolean isShouldDoCovidTest() {
		return shouldDoCovidTest;
	}


	public void setShouldDoCovidTest(boolean shouldDoCovidTest) {
		this.shouldDoCovidTest = shouldDoCovidTest;
	}


	public CovidStatus getTestResults() {
		return testResults;
	}


	public void setTestResults(CovidStatus testResults) {
		this.testResults = testResults;
	}


	public double getLymphocyteCount() {
		return lymphocyteCount;
	}


	public void setLymphocyteCount(double lymphocyteCount) {
		this.lymphocyteCount = lymphocyteCount;
	}


	public int getNonHospitalPneumonia() {
		return nonHospitalPneumonia;
	}


	public void setNonHospitalPneumonia(int nonHospitalPneumonia) {
		this.nonHospitalPneumonia = nonHospitalPneumonia;
	}


	public boolean isCold() {
		return cold;
	}


	public void setCold(boolean cold) {
		this.cold = cold;
	}


	public int getHasColdSoreThroatOrCough() {
		return hasColdSoreThroatOrCough;
	}


	public void setHasColdSoreThroatOrCough(int hasColdSoreThroatOrCough) {
		this.hasColdSoreThroatOrCough = hasColdSoreThroatOrCough;
	}


	public int getHasDyspneaOrHypoxia() {
		return hasDyspneaOrHypoxia;
	}


	public void setHasDyspneaOrHypoxia(int hasDyspneaOrHypoxia) {
		this.hasDyspneaOrHypoxia = hasDyspneaOrHypoxia;
	}


	public int getHasFever() {
		return hasFever;
	}


	public void setHasFever(int hasFever) {
		this.hasFever = hasFever;
	}


	public int getHasLowLymphocytes() {
		return hasLowLymphocytes;
	}


	public void setHasLowLymphocytes(int hasLowLymphocytes) {
		this.hasLowLymphocytes = hasLowLymphocytes;
	}


	public int getHasPneumonia() {
		return hasPneumonia;
	}


	public void setHasPneumonia(int hasPneumonia) {
		this.hasPneumonia = hasPneumonia;
	}


	public int getHasNonHospitalPneumonia() {
		return hasNonHospitalPneumonia;
	}


	public void setHasNonHospitalPneumonia(int hasNonHospitalPneumonia) {
		this.hasNonHospitalPneumonia = hasNonHospitalPneumonia;
	}


	public int getCOVID19Positive() {
		return COVID19Positive;
	}


	public void setCOVID19Positive(int cOVID19Positive) {
		COVID19Positive = cOVID19Positive;
	}

	
}