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

	private String vi;
	private String en;
	
	public MultiLanguageObject() {
		
	}
	
	public MultiLanguageObject(String en) {
		this.en = en;
	}

	public MultiLanguageObject(String en, String vi) {
		super();
		this.en = en;
		this.vi = vi;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getVi() {
		return vi;
	}

	public void setVi(String vi) {
		this.vi = vi;
	}

	
	
	
	
	
}
