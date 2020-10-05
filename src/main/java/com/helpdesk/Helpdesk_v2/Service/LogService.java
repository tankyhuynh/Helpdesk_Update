/**
 * 
 */
package com.helpdesk.Helpdesk_v2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.helpdesk.Helpdesk_v2.Entity.LogEntity;
import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Entity.UserEntity;
import com.helpdesk.Helpdesk_v2.Repository.LogRepo;

/**
* @author root {6:44:37 PM}:
 * @version Creation time: Sep 29, 2020 6:44:37 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Service
public class LogService {

	@Autowired
	private LogRepo logRepo;
	
	
	public List<LogEntity> findAll() {
		return logRepo.findAll(Sort.by(Sort.Direction.DESC, "time"));
	}

	
	public LogEntity findOne(String id) {
		return logRepo.findById(id).orElse(new LogEntity());
	}
	
	
	public LogEntity save(LogEntity entity) {
		return logRepo.save(entity);
	}
	
	
	public void delete(String id) {
		logRepo.deleteById(id);
	}
	
	
	
	
	
	
}
