package com.helpdesk.Helpdesk_v2.Constant;

import org.springframework.stereotype.Component;

/**
* @author root {4:53:22 PM}:
 * @version Creation time: Oct 3, 2020 4:53:22 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@Component
public class MailConstant {

	public static final String mail_title_add_ticket_user = "Unicorn Helpdesk System";
	public static final String mail_body_add_ticket_user = "Thanks for using services, this is the ticket you have already sent to us.";
	public static final String mail_footer_add_ticket_user = "We will handle the ticket ASAP.";
	public static final String mail_body_add_ticket_admin = "You just have a new ticket.";
	public static final String mail_footer_add_ticket_admin = "Please assign this ticket to a technician.";
	
	public static final String mail_title_status_change_user = "Unicorn Helpdesk System";
	public static final String mail_body_status_change_user = "Your ticket just change its status.";
	public static final String mail_footer_status_change_user = "Have a good day.";
	public static final String mail_body_status_change_technician = "You just assigned a ticket.";
	public static final String mail_footer_status_change_technician = "Have a good day.";
	
	public static final String mail_title_drop_ticket_admin = "Unicorn Helpdesk System";
	public static final String mail_body_drop_ticket_admin = "A ticket has been dropped, please assign it to another technician.";
	public static final String mail_footer_drop_ticket_admin = "Have a good day.";
	
	
	
	
	
	
}
