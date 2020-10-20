package com.helpdesk.Helpdesk_v2.Entity;

import java.util.Calendar;
import java.util.Date;

import com.helpdesk.Helpdesk_v2.Model.MultiLanguageObject;

public class StatusEntity {

	private MultiLanguageObject name = new MultiLanguageObject("Waiting", "Đang chờ");
	private Date time = Calendar.getInstance().getTime();
	
	public StatusEntity() {
	}
	
	public StatusEntity(MultiLanguageObject object) {
		this.name = name;
	}

	public StatusEntity(MultiLanguageObject name, Date time) {
		this.name = name;
		this.time = time;
	}

	public MultiLanguageObject getName() {
		return name;
	}

	public void setName(MultiLanguageObject name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
	
	
	
	
	
}
