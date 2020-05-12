package model;

import java.io.Serializable;

public class Country implements Serializable {

	private long id;
	private String countryName;
	private double idvIndex;
	private boolean covidPositive;
	private boolean lowIdvIndex;
	
	public Country() {
		
	}

	public Country(long id, String countryName, double idvIndex, boolean covidPositive, boolean lowIdvIndex) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.idvIndex = idvIndex;
		this.covidPositive = covidPositive;
		this.lowIdvIndex = lowIdvIndex;
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

	public double getIdvIndex() {
		return idvIndex;
	}

	public void setIdvIndex(double idvIndex) {
		this.idvIndex = idvIndex;
	}

	public boolean isCovidPositive() {
		return covidPositive;
	}

	public void setCovidPositive(boolean covidPositive) {
		this.covidPositive = covidPositive;
	}

	public boolean isLowIdvIndex() {
		return lowIdvIndex;
	}

	public void setLowIdvIndex(boolean lowIdvIndex) {
		this.lowIdvIndex = lowIdvIndex;
	}

	
}
