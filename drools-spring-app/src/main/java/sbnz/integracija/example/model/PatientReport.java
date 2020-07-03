package sbnz.integracija.example.model;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("1s")
public class PatientReport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum ReportCondition {
		HEALTHY, CRITICAL, DEAD
	}
	
	private long patientId;
	private double temperature;
	private double respiratoryRate;
	private double oxygenSaturation;
	private double lymphociteCount;
	private double heartRate;
	private int extremeValue;
	private ReportCondition reportCondition;
	
	public PatientReport() {
		super();
	}
	
	public PatientReport(int i) {
		super();
		reportCondition = ReportCondition.HEALTHY;
		this.extremeValue = 0;
		// min inclusive max exclusive
		this.temperature = ThreadLocalRandom.current().nextDouble(36, 38);
		// healthy od 12 do 16 po min
		this.respiratoryRate = ThreadLocalRandom.current().nextDouble(12, 16);
		// healthy oxygen saturation od 60 do 100
		this.oxygenSaturation = ThreadLocalRandom.current().nextDouble(60, 100);
		// healthy lymphocite count od 1000 do 48000
		this.lymphociteCount = ThreadLocalRandom.current().nextDouble(1000, 4800);
		// healthy heart rate od 80 do 150
		this.heartRate = ThreadLocalRandom.current().nextDouble(80, 150);
	}
	
	// ovaj zivi
	// nece da radi kad prosledis id pa mora sa dva konstruktora
	public PatientReport(Long pateintId, PatientReport pReport) {
		super();
		reportCondition = pReport.getReportCondition();
		this.extremeValue = pReport.getExtremeValue();
		if (this.extremeValue > 5) {
			this.extremeValue = 5;
		}
		this.extremeValue = 0;
		// ovaj zivi
		if (patientId % 2 == 0) {
			this.temperature = ThreadLocalRandom.current().nextDouble(36, 38);
			this.respiratoryRate = ThreadLocalRandom.current().nextDouble(12, 16);
			this.oxygenSaturation = ThreadLocalRandom.current().nextDouble(60, 100);
			this.lymphociteCount = ThreadLocalRandom.current().nextDouble(1000, 4800);
			this.heartRate = ThreadLocalRandom.current().nextDouble(80, 150);
		}
	}
	
	// ovaj umire
	// nece da radi kad proseldis id pa mora sa dva konstruktora
	public PatientReport(PatientReport pReport, Long patientId) {
		super();
		reportCondition = pReport.getReportCondition();
		this.extremeValue = pReport.getExtremeValue();
		if (this.extremeValue > 5) {
			this.extremeValue = 5;
		}
		this.temperature = pReport.getTemperature() + pReport.getTemperature() * 0.05;
		this.respiratoryRate = pReport.getRespiratoryRate() + pReport.getRespiratoryRate() * 0.03;
		this.oxygenSaturation = pReport.getOxygenSaturation() + pReport.getOxygenSaturation() * 0.05;
		this.lymphociteCount = pReport.getLymphociteCount() + pReport.getLymphociteCount() + 0.1;
		this.heartRate = pReport.getHeartRate() + pReport.getHeartRate() * 0.005;
		
	}
	
	public PatientReport(double temperature, double respiratoryRate, double oxygenSaturation, double lymphociteCount,
			double heartRate) {
		super();
		this.temperature = temperature;
		this.respiratoryRate = respiratoryRate;
		this.oxygenSaturation = oxygenSaturation;
		this.lymphociteCount = lymphociteCount;
		this.heartRate = heartRate;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public double getOxygenSaturation() {
		return oxygenSaturation;
	}

	public void setOxygenSaturation(double oxygenSaturation) {
		this.oxygenSaturation = oxygenSaturation;
	}

	public double getLymphociteCount() {
		return lymphociteCount;
	}

	public void setLymphociteCount(double lymphociteCount) {
		this.lymphociteCount = lymphociteCount;
	}

	public double getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(double heartRate) {
		this.heartRate = heartRate;
	}
	
	public ReportCondition getReportCondition() {
		return reportCondition;
	}

	public void setReportCondition(ReportCondition reportCondition) {
		this.reportCondition = reportCondition;
	}

	public int getExtremeValue() {
		return extremeValue;
	}

	public void setExtremeValue(int extremeValue) {
		this.extremeValue = extremeValue;
	}
	
}