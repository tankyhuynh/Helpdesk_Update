package com.helpdesk.Helpdesk_v2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Repository.TicketRepo;

@Service
@Transactional
public class TicketService {

	@Autowired
	private TicketRepo ticketRepo;
	
	public List<TicketEntity> findAll() {	
		return ticketRepo.findAll(Sort.by(Sort.Direction.DESC, "startDate"));
	}
	
	public List<TicketEntity> findAllBy_DESCSort(String field) {	
		return ticketRepo.findAll(Sort.by(Sort.Direction.DESC, field));
	}
	
	public List<TicketEntity> findAllBy_ASCSort(String field) {	
		return ticketRepo.findAll(Sort.by(Sort.Direction.ASC, field));
	}
	
	public List<TicketEntity> findAllByTechinician(String technician) {
		return ticketRepo.findAllByTechnicianId(technician, Sort.by(Sort.Direction.DESC, "startDate"));
	}
	
	
	public List<TicketEntity> findAllByUser(String user) {
		return ticketRepo.findAllByUserId(user, Sort.by(Sort.Direction.DESC, "startDate"));
	}
	
	public TicketEntity findOne(String id) {
		return ticketRepo.findById(id).orElse(new TicketEntity());
	}
	
	public TicketEntity save(TicketEntity entity) {
		return ticketRepo.save(entity);
	}
	
	public TicketEntity saveAndFlush(TicketEntity entity) {
		return ticketRepo.save(entity);
	}
	
	public void saveAll(List<TicketEntity> ticketEntities) {
		for (TicketEntity ticketEntity : ticketEntities) {
			ticketRepo.save(ticketEntity);
		}

	}
	
	public void delete(String id) {
		ticketRepo.deleteById(id);
	}
	
	public void deleteAll() {
		ticketRepo.deleteAll();
	}
	
}
