package com.helpdesk.Helpdesk_v2.Entity;

import java.util.Calendar;
import java.util.Date;

public class StatusEntity {

	private String name = "Waiting";
	private Date time = Calendar.getInstance().getTime();
	
	public StatusEntity() {

	}
	
	public StatusEntity(String name) {
		super();
		this.name = name;
	}

	public StatusEntity(String name, Date time) {
		super();
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
	
	
	
}
