package com.helpdesk.Helpdesk_v2.Model;
/**
* @author root {8:29:42 AM}:
 * @version Creation time: Oct 20, 2020 8:29:42 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
public class MultiLanguageObject {

	private String eng;
	private String vie;
	
	public MultiLanguageObject() {
		// TODO Auto-generated constructor stub
	}

	
	
	public MultiLanguageObject(String eng) {
		this.eng = eng;
	}
	
	
	public MultiLanguageObject(String eng, String vie) {
		super();
		this.eng = eng;
		this.vie = vie;
	}




	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getVie() {
		return vie;
	}

	public void setVie(String vie) {
		this.vie = vie;
	}
	
	
	
}
