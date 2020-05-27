package sbnz.integracija.example.dto;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.Country.DevelompentLevel;

public class CountryDTO {
	private String countryName;
	private boolean covidPositive;
	private DevelompentLevel countryDevelopmentLevel;
	private double idvIndex;
	
	public CountryDTO() {
		
	}
	
	public CountryDTO (Country c) {
		this.countryName = c.getCountryName();
		this.covidPositive = c.isCovidPositive();
		this.countryDevelopmentLevel = c.getCountryDevelopmentLevel();
		this.idvIndex = c.getIdvIndex();
	}
	
	public CountryDTO(String countryName, boolean covidPositive, DevelompentLevel countryDevelopmentLevel,
			double idvIndex) {
		super();
		this.countryName = countryName;
		this.covidPositive = covidPositive;
		this.countryDevelopmentLevel = countryDevelopmentLevel;
		this.idvIndex = idvIndex;
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
