package sbnz.integracija.example.event;

import sbnz.integracija.example.model.Examination;

@org.kie.api.definition.type.Role(org.kie.api.definition.type.Role.Type.EVENT)
public class ExaminationEvent {
	
	private Examination examination;
	
	public ExaminationEvent() {
		
	}
	
	public ExaminationEvent(Examination r) {
		this.examination = r;
	}

	public Examination getExemination() {
		return examination;
	}

	public void setExemination(Examination examination) {
		this.examination = examination;
	}
	
	

}
