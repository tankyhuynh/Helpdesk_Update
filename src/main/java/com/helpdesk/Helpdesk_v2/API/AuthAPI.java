package com.helpdesk.Helpdesk_v2.API;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.Helpdesk_v2.Entity.UserEntity;
import com.helpdesk.Helpdesk_v2.Entity.loginRequest;
import com.helpdesk.Helpdesk_v2.Service.UserService;

@RestController
@RequestMapping("/v1")
public class AuthAPI {

	@Autowired
	private UserService userSerice;
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) session.getAttribute("USER_SESSION");

		if (messages == null) {
			messages = new ArrayList<>();
		}
		model.addAttribute("sessionMessages", messages);
		return "index";
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserEntity> process(Model model, HttpServletRequest request, @RequestBody loginRequest loginRequest) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("USER_SESSION");
		
		if (userSerice.findOneByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()) != null) {
			
				if (messages == null) {
					messages = new ArrayList<>();
					request.getSession().setAttribute("USER_SESSION", messages);
				}
				messages.add(loginRequest.getUsername() + loginRequest.getPassword());
				request.getSession().setAttribute("USER_SESSION", messages);
				return ResponseEntity.ok(userSerice.findOneByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()));
		}
		else System.out.println("Fail to login");
		
//		return ResponseEntity.badRequest().body("Bad request");

		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

	}


	@PostMapping("/logout")
	public ResponseEntity<String> destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return ResponseEntity.accepted().build();
	}
}
