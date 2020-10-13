package com.helpdesk.Helpdesk_v2.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ticket")
public class TicketEntity {

	@Id
	private String id;
	private String title;
	private Date startDate;
	private Date endDate;
	private List<String> images;
	private String description;
	private String place;

	private String userId;
	private String fullName;
	private List<StatusEntity> status ;
	private String technicianId;
	private String technicianName;
	private String modifiedBy;
	private List<String> comment;
	

	public TicketEntity() {
		
	}

	public TicketEntity(String id, String title, Date startDate, Date endDate, List<String> images, String description,
			String place, String userId, String fullName, List<StatusEntity> status, String technicianId,
			String technicianName) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.images = images;
		this.description = description;
		this.place = place;
		this.userId = userId;
		this.fullName = fullName;
		this.status = status;
		this.technicianId = technicianId;
		this.technicianName = technicianName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<StatusEntity> getStatus() {
		return status;
	}

	public void setStatus(List<StatusEntity> status) {
		this.status = status;
	}

	public String getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<String> getComment() {
		return comment;
	}

	public void setComment(List<String> comment) {
		this.comment = comment;
	}

	
	
	

	
	
	
	
	
	
	

}
