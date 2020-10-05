package com.helpdesk.Helpdesk_v2.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.Helpdesk_v2.Entity.UserEntity;

@Repository
public interface UserRepo extends MongoRepository<UserEntity, String>{

	UserEntity findOneByUsernameAndPassword(String username, String password);
	List<UserEntity> findAllByRole(String role);
	
}
