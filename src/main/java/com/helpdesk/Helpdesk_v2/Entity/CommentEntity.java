package com.helpdesk.Helpdesk_v2.Entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
* @author root {11:38:34 AM}:
 * @version Creation time: Oct 12, 2020 11:38:34 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@Document("comment")
public class CommentEntity {

	@Id
	private String id;
	private String fullName;
	private String userId;
	private Date time = Calendar.getInstance().getTime();
	private boolean isEdited;
	private String content;
	
	public CommentEntity() {
		this.time = Calendar.getInstance().getTime();
	}

	public CommentEntity(String fullName, boolean isEdited, String content) {
		super();
		this.fullName = fullName;
		this.isEdited = isEdited;
		this.content = content;
	}
	
	public CommentEntity(String content) {
		super();
		this.content = content;
	}
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
	
	
	

	
	
}
