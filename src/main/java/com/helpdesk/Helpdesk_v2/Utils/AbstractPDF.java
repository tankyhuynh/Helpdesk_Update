package com.helpdesk.Helpdesk_v2.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.catalina.authenticator.Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
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
public class AbstractPDF {

	
	public static ByteArrayInputStream customerPDFReport(String[] fieldName, List<TicketEntity> entity) throws Exception {
		Document document = new Document();
		document.setPageSize(PageSize.A3);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Ticket Table", font);
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


				
				
				for (TicketEntity ticket : entity) {
					System.out.println(ticket.getId() + " " + ticket.getTitle() + ticket.getStartDate() + " " +ticket.getEndDate() + " " + ticket.getPlace()+ " " + ticket.getDescription() + " " + ticket.getImages() + " " + ticket.getUserId() + " " + ticket.getComment());
					try {
						
						 ReflectionUtils.doWithFields(ticket.getClass(), field -> {
								
							 	PdfPCell idCell;
							 	try {
							        System.out.print("Field name: " + field.getName() + "\t");	
							        
							        field.setAccessible(true);
							        
							        System.out.println("Type: " +field.getType());
							        
							        if (field.get(ticket) == null) {
							        	 System.out.println(field.get(ticket).toString() + " is Array");
										 idCell = new PdfPCell(new Phrase("Null Value here"));
										 System.out.println("\tField value: "+ field.get(ticket));
										 
									}
							        
//							        else if (String.valueOf(field.get(ticket)).isBlank()) {
//										 System.out.println(field.get(ticket).toString() + " is Blanks");
//										 idCell = new PdfPCell(new Phrase("List"));
//										 System.out.println("\tField value: "+ field.get(ticket));
//										 
//									}
							        
							        else if (String.valueOf(field.get(ticket)).isEmpty()) {
										 System.out.println(field.get(ticket).toString() + " is isEmpty");
										 idCell = new PdfPCell(new Phrase("List"));
										 System.out.println("\tField value: "+ field.get(ticket));
										 
									}
							        
							        else if (field.getType().isArray()) {
										 System.out.println(field.get(ticket).toString() + " is Array");
										 idCell = new PdfPCell(new Phrase("List"));
										 System.out.println("\tField value: "+ field.get(ticket));
										 
									}
							      
							        else {
							        	idCell = new PdfPCell(new Phrase(field.get(ticket).toString()));
							        	System.out.println("\tField value: "+ field.get(ticket));
							        	
							        }
							       
									idCell.setPaddingLeft(6);
									idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
									idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
									idCell.setBorderWidth(2);
//									idCell.setUseVariableBorders(true);
//									idCell.setBorderColorTop(BaseColor.GREEN);
									table.addCell(idCell);
							 		
								} catch (Exception e) {
									
									idCell = new PdfPCell(new Phrase("null"));
									idCell.setPaddingLeft(6);
									idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
									idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
									idCell.setBorderWidth(2);
//									idCell.setUseVariableBorders(true);
//									idCell.setBorderColorTop(BaseColor.GREEN);
									table.addCell(idCell);
									
									
								}
						        
						        return ;

						    });
						
						 
						 
						
					} catch (Exception e) {
						
						
						throw new RuntimeException();
						
					}
					 System.out.println();
					 System.out.println();
					 System.out.println("Out of write");
					
					
						
				}
				
			document.add(table);
			System.out.println("Out of add table");
			document.close();
		} catch (DocumentException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("Write to PDF Success...");

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	public static void analyze(Object obj){
//	    ReflectionUtils.doWithFields(obj.getClass(), field -> {
//
//	        System.out.print("Field name: " + field.getName());
//	        field.setAccessible(true);
//	        System.out.println("\tField value: "+ field.get(obj));
//
//	    });
	}
	
	
	
	
	

	public static void main(String[] args) throws DocumentException, Exception {

		
	}
	
}
