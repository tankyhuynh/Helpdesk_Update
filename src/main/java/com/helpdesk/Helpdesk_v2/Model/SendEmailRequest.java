/**
 * 
 */
package com.helpdesk.Helpdesk_v2.Model;
/**
* @author root {8:04:02 PM}:
 * @version Creation time: Sep 30, 2020 8:04:02 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
public class SendEmailRequest {

	private String email;
	private String content;
	
	
	/**
	 * 
	 */
	public SendEmailRequest() {
		// TODO Auto-generated constructor stub
	}


	public SendEmailRequest(String email, String content) {
		super();
		this.email = email;
		this.content = content;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
}
