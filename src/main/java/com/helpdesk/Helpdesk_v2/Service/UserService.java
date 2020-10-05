package com.helpdesk.Helpdesk_v2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Entity.UserEntity;
import com.helpdesk.Helpdesk_v2.Repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	
	public List<UserEntity> findAll() {	
		return userRepo.findAll(Sort.by(Sort.Direction.DESC, "fullName"));
	}
	
	public List<UserEntity> findAllBy_DESCSort(String field) {	
		return userRepo.findAll(Sort.by(Sort.Direction.DESC, field));
	}
	
	public List<UserEntity> findAllBy_ASCSort(String field) {	
		return userRepo.findAll(Sort.by(Sort.Direction.ASC, field));
	}
	
	
	public List<UserEntity> findAllByRole(String role) {
		return userRepo.findAllByRole(role);
	}
	
	public UserEntity findOne(String id) {
		return userRepo.findById(id).orElse(new UserEntity());
	}
	
	public UserEntity findOneByUsernameAndPassword(String username, String password) {
		return userRepo.findOneByUsernameAndPassword(username, password);
	}
	
	public UserEntity save(UserEntity entity) {
		return userRepo.save(entity);
	}
	
	public void saveAll(List<UserEntity> userEntities) {
		for (UserEntity userEntity : userEntities) {
			userRepo.save(userEntity);
		}

	}
	
	public void delete(String id) {
		userRepo.deleteById(id);
	}
	
}
