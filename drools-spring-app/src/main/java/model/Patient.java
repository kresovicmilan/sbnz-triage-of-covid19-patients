package model;

import java.io.Serializable;
import java.util.List;

public class Patient implements Serializable{

	private long id;
	private String name;
	private String lastname;
	private double lastFever;
	private List<Patient> contactedPatients;
	private List<Country> countriesVisited;
	private int respiratoryRate; //u zavisnosti od respiratory rate-a, racunace se tachnypnea u pravilima
	private double hypoxia;
	private boolean soreThroat;
	private boolean cough;
	private boolean dyspnea;
	private boolean tachnypnea;
	private double alc; //apsolute lymphocite count
	private boolean pneumonia;
	
	public Patient() {
		
	}

	public Patient(long id, String name, String lastname, double lastFever, List<Patient> contactedPatients,
			List<Country> countriesVisited, int respiratoryRate, double hypoxia, boolean soreThroat, boolean cough,
			boolean dyspnea, boolean tachnypnea, double alc, boolean pneumonia) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.lastFever = lastFever;
		this.contactedPatients = contactedPatients;
		this.countriesVisited = countriesVisited;
		this.respiratoryRate = respiratoryRate;
		this.hypoxia = hypoxia;
		this.soreThroat = soreThroat;
		this.cough = cough;
		this.dyspnea = dyspnea;
		this.tachnypnea = tachnypnea;
		this.alc = alc;
		this.pneumonia = pneumonia;
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

	public double getLastFever() {
		return lastFever;
	}

	public void setLastFever(double lastFever) {
		this.lastFever = lastFever;
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

	public double getHypoxia() {
		return hypoxia;
	}

	public void setHypoxia(double hypoxia) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((contactedPatients == null) ? 0 : contactedPatients.hashCode());
		result = prime * result + (cough ? 1231 : 1237);
		result = prime * result + ((countriesVisited == null) ? 0 : countriesVisited.hashCode());
		result = prime * result + (dyspnea ? 1231 : 1237);
		temp = Double.doubleToLongBits(hypoxia);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		temp = Double.doubleToLongBits(lastFever);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (pneumonia ? 1231 : 1237);
		result = prime * result + respiratoryRate;
		result = prime * result + (soreThroat ? 1231 : 1237);
		result = prime * result + (tachnypnea ? 1231 : 1237);
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
		Patient other = (Patient) obj;
		if (Double.doubleToLongBits(alc) != Double.doubleToLongBits(other.alc))
			return false;
		if (contactedPatients == null) {
			if (other.contactedPatients != null)
				return false;
		} else if (!contactedPatients.equals(other.contactedPatients))
			return false;
		if (cough != other.cough)
			return false;
		if (countriesVisited == null) {
			if (other.countriesVisited != null)
				return false;
		} else if (!countriesVisited.equals(other.countriesVisited))
			return false;
		if (dyspnea != other.dyspnea)
			return false;
		if (Double.doubleToLongBits(hypoxia) != Double.doubleToLongBits(other.hypoxia))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(lastFever) != Double.doubleToLongBits(other.lastFever))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pneumonia != other.pneumonia)
			return false;
		if (respiratoryRate != other.respiratoryRate)
			return false;
		if (soreThroat != other.soreThroat)
			return false;
		if (tachnypnea != other.tachnypnea)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", lastname=" + lastname + ", lastFever=" + lastFever
				+ ", contactedPatients=" + contactedPatients + ", countriesVisited=" + countriesVisited
				+ ", respiratoryRate=" + respiratoryRate + ", hypoxia=" + hypoxia + ", soreThroat=" + soreThroat
				+ ", cough=" + cough + ", dyspnea=" + dyspnea + ", tachnypnea=" + tachnypnea + ", alc=" + alc
				+ ", pneumonia=" + pneumonia + "]";
	}
	
	
	
	
	
}
