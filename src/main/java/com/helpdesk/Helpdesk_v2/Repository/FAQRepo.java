package com.helpdesk.Helpdesk_v2.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.Helpdesk_v2.Entity.FAQEntity;

@Repository
public interface FAQRepo extends MongoRepository<FAQEntity, String> {



}
