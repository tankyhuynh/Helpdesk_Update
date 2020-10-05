package com.helpdesk.Helpdesk_v2.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Entity.UserEntity;
import com.helpdesk.Helpdesk_v2.Service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> getAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping("/sort/desc/{fieldName}")
	public ResponseEntity<List<UserEntity>> getAllByDESCSort(@PathVariable("fieldName") String fieldName) throws Exception {
		return ResponseEntity.ok(userService.findAllBy_DESCSort(fieldName));
	}
	
	@GetMapping("/sort/asc/{fieldName}")
	public ResponseEntity<List<UserEntity>> getAllByASCSort(@PathVariable("fieldName") String fieldName) throws Exception {
		return ResponseEntity.ok(userService.findAllBy_ASCSort(fieldName));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(userService.findOne(id));
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<UserEntity>> getAllByRole(@PathVariable("role") String role) {
		return ResponseEntity.ok(userService.findAllByRole(role));
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> save(@RequestBody UserEntity userEntity) {
		return ResponseEntity.ok(userService.save(userEntity));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserEntity> updateById(@PathVariable String id, @RequestBody UserEntity userEntity) {
		if ((userService.findOne(id)) != null) {
			userEntity.setId(id);
			return ResponseEntity.ok(userService.save(userEntity));
		}
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		 userService.delete(id);
	}
	
	
	
	
	
	
}
