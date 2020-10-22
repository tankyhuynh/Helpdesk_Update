package com.helpdesk.Helpdesk_v2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableScheduling			
@CrossOrigin
public class HelpdeskV2Application implements CommandLineRunner {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
	}
		

}
	 