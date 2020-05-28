package sbnz.integracija.example.dto;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Country.DevelompentLevel;

public class CountryDTO {
	private long id;
	private String countryName;
	private boolean covidPositive;
	private DevelompentLevel countryDevelopmentLevel;
	private double idvIndex;
	
	public CountryDTO() {
		
	}
	
	public CountryDTO (Country c) {
		this.id = c.getId();
		this.countryName = c.getCountryName();
		this.covidPositive = c.isCovidPositive();
		this.countryDevelopmentLevel = c.getCountryDevelopmentLevel();
		this.idvIndex = c.getIdvIndex();
	}
	
	public CountryDTO(long id, String countryName, boolean covidPositive, DevelompentLevel countryDevelopmentLevel,
			double idvIndex) {
		super();
		this.id = id; 
		this.countryName = countryName;
		this.covidPositive = covidPositive;
		this.countryDevelopmentLevel = countryDevelopmentLevel;
		this.idvIndex = idvIndex;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public boolean isCovidPositive() {
		return covidPositive;
	}
	public void setCovidPositive(boolean covidPositive) {
		this.covidPositive = covidPositive;
	}
	public DevelompentLevel getCountryDevelopmentLevel() {
		return countryDevelopmentLevel;
	}
	public void setCountryDevelopmentLevel(DevelompentLevel countryDevelopmentLevel) {
		this.countryDevelopmentLevel = countryDevelopmentLevel;
	}
	public double getIdvIndex() {
		return idvIndex;
	}
	public void setIdvIndex(double idvIndex) {
		this.idvIndex = idvIndex;
	}
	
	
}
