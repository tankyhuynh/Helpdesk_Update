/**
 * 
 */
package com.helpdesk.Helpdesk_v2.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.Helpdesk_v2.Entity.LogEntity;
import com.helpdesk.Helpdesk_v2.Service.LogService;

/**
* @author root {6:51:02 PM}:
 * @version Creation time: Sep 29, 2020 6:51:02 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
 
@RestController
@RequestMapping("/v1/log")
public class LogAPI {

	@Autowired
	private LogService logService;
	
	
	
	@GetMapping
	public ResponseEntity<List<LogEntity>> getAll() throws Exception {
		return ResponseEntity.ok(logService.findAll());
	}

	@GetMapping("/{id}")
	public LogEntity getOneById(@PathVariable String id) throws Exception {
		return logService.findOne(id);
	}

	@PostMapping
	public ResponseEntity<LogEntity> save(@RequestBody LogEntity log) {
		return ResponseEntity.ok(logService.save(log));
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<LogEntity> updateById(@PathVariable String id, @RequestBody LogEntity logModel) {
		logModel.setId(id);
		return ResponseEntity.ok(logService.save(logModel));
	}

	@DeleteMapping("/{id}")
	public void deteleById(@PathVariable String id) {
		logService.delete(id);
	}
	
	
	
	
	
	
	
	
}
