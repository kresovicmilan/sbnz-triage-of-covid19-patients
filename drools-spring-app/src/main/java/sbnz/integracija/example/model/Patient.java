package sbnz.integracija.example.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import sbnz.integracija.example.dto.PatientDTO;

@Role(Role.Type.EVENT)
@Timestamp("addTimestamp")
@Entity
public class Patient implements Serializable{
	
	public enum Risk {
        LOW, HIGH, UNKNOWN
    };
    
    public enum CovidStatus {
        POSITIVE, NEGATIVE, UNKNOWN
    };
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column
    private Date addTimestamp;
    
    @Column
	private String name;
    
    @Column
	private String lastname;
    
    @JsonBackReference    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Country country; //dodao
    
    @Column
	private boolean covidPositiveContact;
    
    @Column
	private double lastFever;
    
    @Enumerated(EnumType.STRING)
	private Risk riskOfCovid;
    
    @ManyToMany//(fetch = FetchType.EAGER) Marko: dodao sam fetch type jer je izbacivao error da ne moze lazy da fetchuje entitet ali nije pomoglo
    @JoinTable(name="contacts",
    		joinColumns = { @JoinColumn(name = "contactedPatientsByMe_id") },
            inverseJoinColumns = { @JoinColumn(name = "contactedPatients_id") }
    )
	private List<Patient> contactedPatients;
	
    @ManyToMany//(fetch = FetchType.EAGER) Isto kao 5 redova iznad
    @JoinTable(name="contacts",
     joinColumns = { @JoinColumn(name = "contactedPatients_id") },
     inverseJoinColumns = { @JoinColumn(name = "contactedPatientsByMe_id") }
    )
	private List<Patient> contactedPatientsByMe;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "patient_countriesvisited",
            joinColumns = { @JoinColumn(name = "patient_id") },
            inverseJoinColumns = { @JoinColumn(name = "countriesvisited_id") }
    )
	private List<Country> countriesVisited;
    
    @Column
	private int respiratoryRate; //u zavisnosti od respiratory rate-a, racunace se tachnypnea u pravilima
    
    @Column
	private boolean hypoxia;
    
    @Column
	private boolean soreThroat;
    
    @Column
	private boolean cough;
    
    @Column
	private boolean dyspnea;
    
    @Column
	private boolean tachnypnea;
    
    @Column
	private double alc; //apsolute lymphocite count
    
    @Column
	private boolean pneumonia;
	
	@Column
	private double oxygenSaturation;
	
	@Enumerated(EnumType.STRING)
	private CovidStatus covidStatus;
	
	@Column
	private String curingMeasures;
	
	@Enumerated(EnumType.STRING)
	private CovidStatus testResults;
	
	@Column
	private boolean shouldDoTest;
	// dodao
	
	@Column
	private double lymphocyteCount;
	
	@Column
	private boolean nonHospitalPneumonia; 
	
	@Column
	private boolean cold; // cold je prehlada fever je temperatura
	
	// pomocni koraci za ispaljivanje pravila dodao
	@Column
	private int hasColdSoreThroatOrCough; // -1 unknown, 0 negative, 1 positive
	
	@Column
	private int hasDyspneaOrHypoxia; // -1 unknown, 0 negative, 1 positive
	
	@Column
	private int hasFever; // -1 unknown, 0 negative, 1 positive
	
	@Column
	private int hasLowLymphocytes; // -1 unknown, 0 negative, 1 positive
	
	@Column
	private int hasPneumonia; // -1 unknown, 0 negative, 1 positive
	
	@Column
	private int hasNonHospitalPneumonia; // -1 unknown, 0 negative, 1 positive
	
	@Column
	private int COVID19Positive; // -1 unknown, 0 negative, 1 positive
	
	@Enumerated(EnumType.STRING)
	private Risk coughOrFeverRisk;
	
	@Enumerated(EnumType.STRING)
	private Risk contactRisk;
	
	@Enumerated(EnumType.STRING)
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

	public double getLymphocyteCount() {
		return lymphocyteCount;
	}


	public void setLymphocyteCount(double lymphocyteCount) {
		this.lymphocyteCount = lymphocyteCount;
	}


	public boolean getNonHospitalPneumonia() {
		return nonHospitalPneumonia;
	}


	public void setNonHospitalPneumonia(boolean nonHospitalPneumonia) {
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

	public List<Patient> getContactedPatientsByMe() {
		return contactedPatientsByMe;
	}

	public void setContactedPatientsByMe(List<Patient> contactedPatientsByMe) {
		this.contactedPatientsByMe = contactedPatientsByMe;
	}

	public Date getAddTimestamp() {
		return addTimestamp;
	}

	public void setAddTimestamp(Date addTimestamp) {
		this.addTimestamp = addTimestamp;
	}	
	
}