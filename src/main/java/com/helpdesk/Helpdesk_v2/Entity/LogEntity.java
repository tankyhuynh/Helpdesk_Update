/**
 * 
 */
package com.helpdesk.Helpdesk_v2.Entity;
/**
* @author root {6:42:16 PM}:
 * @version Creation time: Sep 29, 2020 6:42:16 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log")
public class LogEntity {

	@Id
	private String id;
	private Date time;
	private String content;
	private String icon;
	
	/**
	 * 
	 */
	public LogEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public LogEntity(String content, String icon) {
		super();
		this.time = Calendar.getInstance().getTime();
		this.content = content;
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
	
	
	
	
	
}
