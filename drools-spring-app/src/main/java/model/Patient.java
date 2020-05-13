package model;

import java.io.Serializable;
import java.util.List;

public class Patient implements Serializable{
	
	public enum Risk {
        LOW, HIGH, UNKNOWN
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
	
	public Patient() {
		
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
	
	
	
}
