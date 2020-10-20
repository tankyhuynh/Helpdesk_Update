package com.helpdesk.Helpdesk_v2.API;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.Helpdesk_v2.Constant.MailConstant;
import com.helpdesk.Helpdesk_v2.Constant.TicketConstant;
import com.helpdesk.Helpdesk_v2.Entity.CommentEntity;
import com.helpdesk.Helpdesk_v2.Entity.LogEntity;
import com.helpdesk.Helpdesk_v2.Entity.StatusEntity;
import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Model.MultiLanguageObject;
import com.helpdesk.Helpdesk_v2.Service.CommentService;
import com.helpdesk.Helpdesk_v2.Service.LogService;
import com.helpdesk.Helpdesk_v2.Service.TicketService;
import com.helpdesk.Helpdesk_v2.Service.UserService;
import com.helpdesk.Helpdesk_v2.Utils.FileUtils;

@RestController
@RequestMapping("/v1/ticket")
public class TicketAPI {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;

	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private MailAPI mailAPI;
	
	@Autowired
	private MailConstant mailConstant;
	
	@Autowired
	private TicketConstant ticketConstant;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private CommentService commentService;

	@GetMapping
	public ResponseEntity<List<TicketEntity>> getAll() throws Exception {
		System.out.println("In ticket");
		return ResponseEntity.ok(ticketService.findAll());
	}
	
	@GetMapping("/sort/desc/{fieldName}")
	public ResponseEntity<List<TicketEntity>> getAllByDESCSort(@PathVariable("fieldName") String fieldName) throws Exception {
		return ResponseEntity.ok(ticketService.findAllBy_DESCSort(fieldName));
	}
	
	@GetMapping("/sort/asc/{fieldName}")
	public ResponseEntity<List<TicketEntity>> getAllByASCSort(@PathVariable("fieldName") String fieldName) throws Exception {
		return ResponseEntity.ok(ticketService.findAllBy_ASCSort(fieldName));
	}

	@GetMapping("/technicians/{id}")
	public ResponseEntity<List<TicketEntity>> getAllByTechnician(@PathVariable("id") String technician)
			throws Exception {
		return ResponseEntity.ok(ticketService.findAllByTechinician(technician));
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<List<TicketEntity>> getAllByUser(@PathVariable("id") String user) throws Exception {
		return ResponseEntity.ok(ticketService.findAllByUser(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TicketEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(ticketService.findOne(id));
	}

	@PostMapping
	public ResponseEntity<TicketEntity> save(@RequestBody TicketEntity ticketEntity) throws Exception {

		List<String> URL = new ArrayList<String>();

		for (String item : ticketEntity.getImages()) {
			URL.add(fileUtils.decoder(item, "outputFile"));

		}
		ticketEntity.setImages(URL);

		ticketEntity.setStartDate(new Date(System.currentTimeMillis()));
		List<StatusEntity> status = new ArrayList<StatusEntity>();	
		status.add(new StatusEntity());
		ticketEntity.setStatus(status);
		
//		ticketEntity.setId((UUID.randomUUID().toString()));
		mailAPI.sendUser_addTicket(ticketEntity.getUserId(), ticketEntity, mailConstant.mail_title_add_ticket_user, mailConstant.mail_body_add_ticket_user, mailConstant.mail_footer_add_ticket_user);
		mailAPI.sendUser_addTicket("5f6ff6a57cbfb744d95344c8", ticketEntity, mailConstant.mail_title_add_ticket_user, mailConstant.mail_body_add_ticket_admin, mailConstant.mail_footer_add_ticket_admin);
		
		LogEntity logEntity = new LogEntity(userService.findOne(ticketEntity.getUserId()).getFullName() + ticketConstant.add_status +  ticketEntity.getId() + " vào", "https://img.icons8.com/ios-filled/64/000000/information.png");
		logService.save(logEntity);
		
		CommentEntity commentEntity = new CommentEntity(ticketEntity.getFullName(), false, ticketEntity.getDescription());
		
		commentEntity.setId(UUID.randomUUID().toString());
		commentEntity.setUserId(ticketEntity.getUserId());
		commentEntity.setTicketId(ticketEntity.getId());
		
		
		ticketEntity.setComment(Arrays.asList(commentEntity.getId()));
		commentService.save(commentEntity);
		
		
		return ResponseEntity.ok(ticketService.save(ticketEntity));
	}

	@PutMapping("/{id}")
	@Transactional	
	public ResponseEntity<TicketEntity> updateById(@PathVariable String id, @RequestBody TicketEntity ticketEntity)
			throws Exception {
		
		System.out.println("Ticket status from client: ");
		for (StatusEntity item : ticketEntity.getStatus()) {
			System.out.println(item.getName().getEn() + item.getName().getVi());
		}

		if (ticketService.findOne(id) != null) {
			ticketEntity.setId(id);
			TicketEntity oldEntity = ticketService.findOne(id);
			
			if (ticketEntity.getTechnicianId() != null && !ticketEntity.getTechnicianId().isEmpty()) {
					
				if (ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).getName().getEn().equals("Waiting")) {
					ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).setName(new MultiLanguageObject("Assigned", "Đã phân công"));
					System.out.println("Status == Waiting");
					mailAPI.sendTechinician_statusChange(ticketEntity.getTechnicianId(), ticketEntity);
				}
				
				else if(ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).getName().getEn().equals("Closed") || ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).getName().equals("Đã đóng")) {
					ticketEntity.setEndDate(Calendar.getInstance().getTime());
					ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).setName(new MultiLanguageObject("Closed", "Đã đóng"));
					mailAPI.sendUser_statusChange(ticketEntity.getUserId(), ticketEntity);
					mailAPI.sendTechinician_statusChange(ticketEntity.getTechnicianId(), ticketEntity);
				}
				else {
					ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).setName(new MultiLanguageObject("Assigned", "Đã phân công"));
					System.out.println("Status != Waiting");
					
				}

				mailAPI.sendUser_statusChange(ticketEntity.getUserId(), ticketEntity);
				mailAPI.sendTechinician_statusChange(ticketEntity.getTechnicianId(), ticketEntity);
				
			}
			
			LogEntity logEntity = new LogEntity(userService.findOne(ticketEntity.getModifiedBy()).getFullName() + ticketConstant.update_status +  ticketEntity.getId() + " vào", "https://img.icons8.com/ios-filled/64/000000/information.png");
			logService.save(logEntity);


			return ResponseEntity.status(HttpStatus.OK).body(ticketService.saveAndFlush(ticketEntity));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}/{modifiedBy}")
	public void deleteById(@PathVariable("id") String id, @PathVariable("modifiedBy") String modifiedBy) throws Exception {
		TicketEntity ticketEntity = ticketService.findOne(id);
		
		System.err.println("Hello");
		System.out.println("ModifiedBy: " + ticketEntity.getModifiedBy());
		LogEntity logEntity = new LogEntity(userService.findOne(modifiedBy).getFullName() + ticketConstant.delete_status +  ticketEntity.getId() + " vào ", "https://img.icons8.com/ios-filled/64/000000/information.png");
		logService.save(logEntity);
		
		mailAPI.sendAdmin_dropTicket(ticketEntity);
		ticketService.delete(ticketEntity.getId());

	}
	
	@DeleteMapping("/all")
	public void deleteAll(String[] ids) {
		ticketService.deleteAll();
	}
	
	
	
	
	
	
	
	
	
	
	

}
