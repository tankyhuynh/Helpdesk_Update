package com.helpdesk.Helpdesk_v2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.helpdesk.Helpdesk_v2.Entity.CommentEntity;
import com.helpdesk.Helpdesk_v2.Entity.LogEntity;
import com.helpdesk.Helpdesk_v2.Repository.CommentRepo;

/**
* @author root {7:59:29 PM}:
 * @version Creation time: Oct 13, 2020 7:59:29 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@Service
public class CommentService {

	@Autowired
	private CommentRepo commentRepo;
	

	public List<CommentEntity> findAll() {
		return commentRepo.findAll(Sort.by(Sort.Direction.DESC, "time"));
	}
	
	public List<CommentEntity> findAllByTicketId(String ticketId) {
		return commentRepo.findAllByTicketId(ticketId);
	}

	
	public CommentEntity findOneById(String id) {
		return commentRepo.findOneById(id);
	}
	
	
	public CommentEntity save(CommentEntity entity) {
		return commentRepo.save(entity);
	}
	
	
	public void delete(String id) {
		commentRepo.deleteById(id);
	}
	
	public void deleteAll() {
		commentRepo.deleteAll();
	}
	
	
}
