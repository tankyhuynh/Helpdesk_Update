package com.helpdesk.Helpdesk_v2.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.helpdesk.Helpdesk_v2.Model.MultiLanguageObject;

@Document(collection = "faq")
public class FAQEntity {

	@Id
	private String id;

	private MultiLanguageObject question;
	private MultiLanguageObject answer;

	public FAQEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public FAQEntity(MultiLanguageObject question, MultiLanguageObject answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	public FAQEntity(String id, MultiLanguageObject question, MultiLanguageObject answer) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MultiLanguageObject getQuestion() {
		return question;
	}

	public void setQuestion(MultiLanguageObject question) {
		this.question = question;
	}

	public MultiLanguageObject getAnswer() {
		return answer;
	}

	public void setAnswer(MultiLanguageObject answer) {
		this.answer = answer;
	}
	
	
	
	
	

	
	
	
	
	

}
