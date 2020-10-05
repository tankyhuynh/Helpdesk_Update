package com.helpdesk.Helpdesk_v2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.Helpdesk_v2.Entity.FAQEntity;
import com.helpdesk.Helpdesk_v2.Repository.FAQRepo;

@Service
public class FAQService {

	@Autowired
	private FAQRepo faqRepo;
	
	
	public List<FAQEntity> findAll() {
		return faqRepo.findAll();
	}
	
	public FAQEntity findOne(String id) {
		return faqRepo.findById(id).orElse(new FAQEntity());
	}
	
	public FAQEntity save(FAQEntity entity) {
		return faqRepo.save(entity);
	}
	
	public void saveAll(List<FAQEntity> faqs) {
		for (FAQEntity faq : faqs) {
			faqRepo.save(faq);
		}

	}
	
	public void delete(String id) {
		faqRepo.deleteById(id);
	}
	
	
	
}
