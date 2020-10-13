package com.helpdesk.Helpdesk_v2.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.Helpdesk_v2.Entity.CommentEntity;

/**
* @author root {7:58:57 PM}:
 * @version Creation time: Oct 13, 2020 7:58:57 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@Repository
public interface CommentRepo extends MongoRepository<CommentEntity, String> {

	CommentEntity findOneById(String id);
	
}
