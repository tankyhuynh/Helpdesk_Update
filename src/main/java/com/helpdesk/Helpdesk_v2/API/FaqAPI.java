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

import com.helpdesk.Helpdesk_v2.Entity.FAQEntity;
import com.helpdesk.Helpdesk_v2.Service.FAQService;

@RestController
@RequestMapping("/v1/faq")
public class FaqAPI {

	@Autowired
	private FAQService faqService;

	@GetMapping
	public ResponseEntity<List<FAQEntity>> getAll() throws Exception {
		return ResponseEntity.ok(faqService.findAll());
	}

	@GetMapping("/{id}")
	public FAQEntity getOneById(@PathVariable String id) throws Exception {
		return faqService.findOne(id);
	}

	@PostMapping
	public ResponseEntity<FAQEntity> save(@RequestBody FAQEntity faq) {
		return ResponseEntity.ok(faqService.save(faq));
	}

	@PostMapping("/all")
	public void saveAll(@RequestBody List<FAQEntity> lisFaqModels) {
		for (FAQEntity faqModel : lisFaqModels) {
			faqService.save(faqModel);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<FAQEntity> updateById(@PathVariable String id, @RequestBody FAQEntity faqModel) {
		faqModel.setId(id);
		return ResponseEntity.ok(faqService.save(faqModel));
	}

	@DeleteMapping("/{id}")
	public void deteleById(@PathVariable String id) {
		faqService.delete(id);
	}
}
