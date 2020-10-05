/**
 * 
 */
package com.helpdesk.Helpdesk_v2.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.Helpdesk_v2.Entity.LogEntity;

/**
* @author root {6:44:05 PM}:
 * @version Creation time: Sep 29, 2020 6:44:05 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@Repository
public interface LogRepo extends MongoRepository<LogEntity, String> {

	
	
}
