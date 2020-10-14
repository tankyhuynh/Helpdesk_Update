package com.helpdesk.Helpdesk_v2.API;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.helpdesk.Helpdesk_v2.Entity.CommentEntity;
import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Service.CommentService;
import com.helpdesk.Helpdesk_v2.Service.TicketService;

/**
* @author root {8:41:39 PM}:
 * @version Creation time: Oct 13, 2020 8:41:39 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@RestController
@RequestMapping("/v1/comment")
public class CommentAPI {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MailAPI mailAPI;
	
	
	@GetMapping
	public List<CommentEntity> getAll() {
		return commentService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CommentEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(commentService.findOneById(id));
	}
	
	@PostMapping
	public CommentEntity save(@RequestBody CommentEntity commentEntity) {
		return commentService.save(commentEntity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CommentEntity> update(@PathVariable String id, @RequestBody CommentEntity commentEntity) {
		commentEntity.setId(id);

		return ResponseEntity.ok(commentService.save(commentEntity));
	}
	 
	
	@PutMapping("/ticket/{id}")
	@Transactional
	public ResponseEntity<TicketEntity> commment(@PathVariable String id, @RequestBody CommentEntity commentEntity) {
		TicketEntity ticketEntity = ticketService.findOne(id);
		commentEntity.setTime(Calendar.getInstance().getTime());
		
		commentEntity.setId(UUID.randomUUID().toString());
		ticketEntity.getComment().add(commentEntity.getId());
			
		
		commentEntity.setTicketId(id);
		ticketService.save(ticketEntity);
		commentService.save(commentEntity);
		
	
		List<String> userIds = new ArrayList<String>();
		for (CommentEntity commentId : commentService.findAllByTicketId(id)) {
			if (!userIds.contains(commentId.getUserId())) {
				userIds.add(commentId.getUserId());
			}
		}
		
		for (String userId : userIds) {
			mailAPI.send_updateTicket(userId);
		}
		
		
		
		

		return ResponseEntity.ok(ticketService.save(ticketEntity));
	}
	
	

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		commentService.delete(id);
	}
	
	
	@DeleteMapping("/all")
	public void deleteAll() {
		commentService.deleteAll();
	}
	
	
	
	
	
	
	
	
}
