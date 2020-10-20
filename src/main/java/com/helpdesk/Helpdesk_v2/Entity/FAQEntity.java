package com.helpdesk.Helpdesk_v2.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.helpdesk.Helpdesk_v2.Model.MultiLanguageObject;

@Document(collection = "faq")
public class FAQEntity {

	@Id
	private String id;

	private String question;
	private String answer;

	public FAQEntity() {
		// TODO Auto-generated constructor stub
	}

	public FAQEntity(String id, String question, String answer) {
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
	

	
	
	
	
	

}
