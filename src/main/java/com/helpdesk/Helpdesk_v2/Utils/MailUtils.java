/**
 * 
 */
package com.helpdesk.Helpdesk_v2.API;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.helpdesk.Helpdesk_v2.Constant.MailConstant;
import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Service.UserService;

@Component
public class MailAPI {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailConstant mailConstant;
	
	@Autowired
	private JavaMailSender mailSender;
	

	public void send_updateTicket(String[] emails, String titleOfTicket) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(emails);

		msg.setSubject("Alert Alert Alert !!!!!!!");
		msg.setText("Have a new comment in ticket " + titleOfTicket);

		javaMailSender.send(msg);

	}
	
	
	public void sendUser_addTicket(String userId,TicketEntity ticketEntity, String emailTitle, String emailBody, String emailFooter) {

		String email = userService.findOne(userId).getEmail();
		
		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
							+ "<br> Time: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE)).format(ticketEntity.getStartDate())
							+ "<br> Title: " + ticketEntity.getTitle() 
							+ "<br> Description: " + ticketEntity.getDescription()
							+ "<br> Place: " + ticketEntity.getPlace() 
							+ "<br> Images: " + ticketEntity.getImages() + "<br>";

		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		try {
			helper.setText(emailBody
					+ "<br> " + content
					+ "<br>" + emailFooter, true);
			helper.setTo(email);
			helper.setSubject(emailTitle);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		mailSender.send(mimeMessage);	
		
	}
	
	
	
	public void sendUser_statusChange(String userId, TicketEntity ticketEntity) {

		String email = userService.findOne(userId).getEmail();

		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
							+ "<br> Time: " + ticketEntity.getStartDate().toString() 
							+ "<br> Title: " + ticketEntity.getTitle() 
							+ "<br> Description: " + ticketEntity.getDescription()
							+ "<br> Status: " + ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).getName().getEn() + " at " + ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).getTime()
							+ "<br> Technician: " + ticketEntity.getTechnicianName();
					
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		try {
			helper.setTo(email);
			helper.setSubject(mailConstant.mail_title_status_change_user);
			
			helper.setText(mailConstant.mail_body_status_change_user
					+ "<br> " + content
					+ "<br>" + mailConstant.mail_footer_status_change_user, true); 

			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {

			e.printStackTrace();
		} 
		
		mailSender.send(mimeMessage);	

	}
	
	
	
	
	public void sendTechinician_statusChange(String userId,TicketEntity ticketEntity) {

		String email = userService.findOne(userId).getEmail();
		
		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
							+ "<br> Time: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE)).format(ticketEntity.getStartDate())
							+ "<br> Title: " + ticketEntity.getTitle() 
							+ "<br> Description: " + ticketEntity.getDescription()
							+ "<br> Place: " + ticketEntity.getPlace() 
							+ "<br> Images: " + ticketEntity.getImages() + "<br>";

		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		try {
			helper.setTo(email);
			helper.setSubject(mailConstant.mail_title_status_change_user);
			
			helper.setText(mailConstant.mail_body_status_change_technician
					+ "<br> " + content
					+ "<br>" + mailConstant.mail_footer_status_change_technician, true); 

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {

			e.printStackTrace();
		} 
		
		mailSender.send(mimeMessage);	
		
	}
	
	
	
	public void sendAdmin_dropTicket(TicketEntity ticketEntity) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		String email = userService.findOne("5f6ff6a57cbfb744d95344c8").getEmail();

		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
							+ "<br> Time: " + ticketEntity.getStartDate().toString() 
							+ "<br> Title: " + ticketEntity.getTitle() 
							+ "<br> Description: " + ticketEntity.getDescription()
							+ "<br> Place: " + ticketEntity.getPlace() 
							+ "<br> Images: " + ticketEntity.getImages();
		
		

		try {
			helper.setText(mailConstant.mail_body_drop_ticket_admin
					+ "<br> " + content
					+ "<br>" + mailConstant.mail_footer_drop_ticket_admin, true);
			helper.setTo(email);
			helper.setSubject(mailConstant.mail_title_drop_ticket_admin);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {

			e.printStackTrace();
		} 
		
		mailSender.send(mimeMessage);	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
