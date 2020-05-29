package sbnz.integracija.example.dto;

import java.util.ArrayList;
import java.util.List;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Patient;

public class PatientDTO {

	private long id;
	
	private String name;
	
	private String lastName;
	
	private Long countryId;
	
	private boolean covidPositiveContact;
	
	private double lastFever;
	
	private boolean hasCold;
	
	private double respiratoryRate;
	
	private boolean hypoxia;
	
	private boolean hasSoreThroat;
	
	private boolean hasCough;
	
	private boolean hasDyspnea;
	
	private boolean hasTachypnea;
	
	private double acl;
	
	private boolean pneumonia;
	
	private double oxygenSaturation;
	
	private boolean nonHospitalPneumonia;
	
	private List<Long> countriesVisited;
	
	private List<Long> contactedPatients;

	private String curingMeassures;
	
	public PatientDTO() {
		
	}
	
	public PatientDTO(String name, boolean hasCold, String lastName, Long countryId, boolean covidPositiveContact, double lastFever,
			double respiratoryRate, boolean hypoxia, boolean hasSoreThroat, boolean hasCough, boolean hasDyspnea, boolean hasTachypnea,
			double acl, boolean pneumonia, double oxygenSaturation, boolean nonHospitalPneumonia,
			List<Long> countriesVisited, List<Long> contactedPatients) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.countryId = countryId;
		this.covidPositiveContact = covidPositiveContact;
		this.lastFever = lastFever;
		this.hasCold = hasCold;
		this.respiratoryRate = respiratoryRate;
		this.hypoxia = hypoxia;
		this.hasSoreThroat = hasSoreThroat;
		this.hasCough = hasCough;
		this.hasDyspnea = hasDyspnea;
		this.hasTachypnea = hasTachypnea;
		this.acl = acl;
		this.pneumonia = pneumonia;
		this.oxygenSaturation = oxygenSaturation;
		this.nonHospitalPneumonia = nonHospitalPneumonia;
		this.countriesVisited = countriesVisited;
		this.contactedPatients = contactedPatients;
		this.curingMeassures = "";
	}
	
	public PatientDTO(Patient p) {
		this.name = p.getName();
		this.lastName = p.getLastname();
		this.countryId = p.getCountry().getId();
		this.covidPositiveContact = p.isCovidPositiveContact();
		this.lastFever = p.getLastFever();
		this.hasCold = p.isCold();
		this.respiratoryRate = p.getRespiratoryRate();
		this.hypoxia = p.isHypoxia();
		this.hasSoreThroat = p.isSoreThroat();
		this.hasCough = p.isCough();
		this.hasDyspnea = p.isDyspnea();
		this.hasTachypnea = p.isTachnypnea();
		this.acl = p.getAlc();
		this.pneumonia = p.isPneumonia();
		this.oxygenSaturation = p.getOxygenSaturation();
		this.nonHospitalPneumonia = p.getNonHospitalPneumonia();
		this.countriesVisited = new ArrayList<Long>();
		for (Country c : p.getCountriesVisited()) {
			this.countriesVisited.add(c.getId());
		}
		this.contactedPatients = new ArrayList<Long>();
		for (Patient p1 : p.getContactedPatients()) {
			this.contactedPatients.add(p1.getId());
		}
		this.curingMeassures = "";
	}
	
	

	
	public boolean isHasCold() {
		return hasCold;
	}

	public void setHasCold(boolean hasCold) {
		this.hasCold = hasCold;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
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

	public double getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public boolean isHypoxia() {
		return hypoxia;
	}

	public void setHypoxia(boolean hypoxia) {
		this.hypoxia = hypoxia;
	}

	public boolean isHasSoreThroat() {
		return hasSoreThroat;
	}

	public void setHasSoreThroat(boolean hasSoreThroat) {
		this.hasSoreThroat = hasSoreThroat;
	}

	public boolean isHasCough() {
		return hasCough;
	}

	public void setHasCough(boolean hasCough) {
		this.hasCough = hasCough;
	}

	public boolean isHasDyspnea() {
		return hasDyspnea;
	}

	public void setHasDyspnea(boolean hasDyspnea) {
		this.hasDyspnea = hasDyspnea;
	}

	public boolean isHasTachypnea() {
		return hasTachypnea;
	}

	public void setHasTachypnea(boolean hasTachypnea) {
		this.hasTachypnea = hasTachypnea;
	}

	public double getAcl() {
		return acl;
	}

	public void setAcl(double acl) {
		this.acl = acl;
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

	public boolean getNonHospitalPneumonia() {
		return nonHospitalPneumonia;
	}

	public void setNonHospitalPneumonia(boolean nonHospitalPneumonia) {
		this.nonHospitalPneumonia = nonHospitalPneumonia;
	}

	public List<Long> getCountriesVisited() {
		return countriesVisited;
	}

	public void setCountriesVisited(List<Long> countriesVisited) {
		this.countriesVisited = countriesVisited;
	}

	public List<Long> getContactedPatients() {
		return contactedPatients;
	}

	public void setContactedPatients(List<Long> contactedPatients) {
		this.contactedPatients = contactedPatients;
	}
	

	public String getCuringMeassures() {
		return curingMeassures;
	}

	public void setCuringMeassures(String curingMeassures) {
		this.curingMeassures = curingMeassures;
	}
}
