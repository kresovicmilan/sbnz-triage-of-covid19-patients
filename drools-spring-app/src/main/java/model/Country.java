package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Country implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum DevelompentLevel {
        LOW, HIGH, UNKNOWN
    };
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    @Column
	private String countryName;
    
    @Enumerated(EnumType.STRING)
	private DevelompentLevel countryDevelopmentLevel;
    
    @Column
	private boolean covidPositive;
    
    @Column
	private double idvIndex;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Patient> patient;
    
    @ManyToMany(mappedBy = "countriesVisited")
    private List<Patient> patientsVisited;
	
	public Country() {
		
	}

	public Country(long id, String countryName, boolean covidPositive, double idvIndex) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.covidPositive = covidPositive;
		this.idvIndex = idvIndex;
		this.countryDevelopmentLevel = DevelompentLevel.UNKNOWN;
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

	public DevelompentLevel getCountryDevelopmentLevel() {
		return countryDevelopmentLevel;
	}

	public void setCountryDevelopmentLevel(DevelompentLevel countryDevelopmentLevel) {
		this.countryDevelopmentLevel = countryDevelopmentLevel;
	}

	public boolean isCovidPositive() {
		return covidPositive;
	}

	public void setCovidPositive(boolean covidPositive) {
		this.covidPositive = covidPositive;
	}

	public double getIdvIndex() {
		return idvIndex;
	}

	public void setIdvIndex(double idvIndex) {
		this.idvIndex = idvIndex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryDevelopmentLevel == null) ? 0 : countryDevelopmentLevel.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + (covidPositive ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(idvIndex);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (countryDevelopmentLevel != other.countryDevelopmentLevel)
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (covidPositive != other.covidPositive)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(idvIndex) != Double.doubleToLongBits(other.idvIndex))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", countryName=" + countryName + ", countryDevelopmentLevel="
				+ countryDevelopmentLevel + ", covidPositive=" + covidPositive + ", idvIndex=" + idvIndex + "]";
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

	public List<Patient> getPatientsVisited() {
		return patientsVisited;
	}

	public void setPatientsVisited(List<Patient> patientsVisited) {
		this.patientsVisited = patientsVisited;
	}
	
}
