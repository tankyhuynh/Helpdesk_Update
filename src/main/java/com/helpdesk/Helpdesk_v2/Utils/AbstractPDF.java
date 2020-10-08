package com.helpdesk.Helpdesk_v2.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
* @author root {4:49:19 PM}:
 * @version Creation time: Oct 7, 2020 4:49:19 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Component
public class AbstractPDF<T> {

	public static <T> ByteArrayInputStream customerPDFReport(String[] fieldName, List<T> entity,  Class<T> attribute) throws Exception {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Customer Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(fieldName.length);
			// Add PDF Table Header ->
			Stream.of(fieldName).forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});


				Field[] parameters = attribute.getDeclaredFields();
				
				for (T user : entity) {
					
					try {
						
						 ReflectionUtils.doWithFields(user.getClass(), field -> {
								
							 	PdfPCell idCell;
						        System.out.print("Field name: " + field.getName());
						        
						        field.setAccessible(true);
						        if (field.getType().isArray()) {
									System.out.println(field.get(user).toString() + " is Array");
									  idCell = new PdfPCell(new Phrase("List"));
									 System.out.println("\tField value: "+ field.get(user));
								}
						        else {
						        	idCell = new PdfPCell(new Phrase(field.get(user).toString()));
						        	System.out.println("\tField value: "+ field.get(user));
						        }
						       
						        
								idCell.setPaddingLeft(6);
								idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
								idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
								idCell.setBorderWidth(2);
//								idCell.setUseVariableBorders(true);
//								idCell.setBorderColorTop(BaseColor.GREEN);
								table.addCell(idCell);
						        
						        

						    });
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					
						
				}
				
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			System.out.println(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	public static void analyze(Object obj){
	    ReflectionUtils.doWithFields(obj.getClass(), field -> {

	        System.out.print("Field name: " + field.getName());
	        field.setAccessible(true);
	        System.out.println("\tField value: "+ field.get(obj));

	    });
	}
	
	
	
	
	

	public static void main(String[] args) throws DocumentException, Exception {

		
	}
	
}
