package com.helpdesk.Helpdesk_v2.Repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;

@Repository
public interface TicketRepo extends MongoRepository<TicketEntity, String> {

	List<TicketEntity> findAllByTechnicianId(String technicianId, Sort sort);
	List<TicketEntity> findAllByUserId(String userId, Sort sort);
	
	
}
