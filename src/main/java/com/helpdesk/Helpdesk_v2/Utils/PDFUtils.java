package com.helpdesk.Helpdesk_v2.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.helpdesk.Helpdesk_v2.Entity.StatusEntity;
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
import com.itextpdf.text.pdf.BaseFont;
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
public class PDFUtils<T> {

//	public static File fontFile = new File("/home/tanky/Downloads/VietFontsWeb1_ttf/vuArial.ttf");
	public static File fontFile = new File("/vuArial.ttf");
	
	public static <T> ByteArrayInputStream customerPDFReport(String[] fieldName, List<TicketEntity> ticketEntity) throws Exception {
		Document document = new Document();
		document.setPageSize(PageSize.A2);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font font = new Font(bf,15);
			Paragraph para = new Paragraph("Customer Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(10);
			table.setWidthPercentage(100);
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

			
			
			for (TicketEntity ticket : ticketEntity) {
				
				PdfPCell idCell = new PdfPCell(new Phrase(ticket.getId().toString()));
				idCell.setPaddingLeft(6);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				idCell.setBorderWidth(2);
				table.addCell(idCell);
				
				PdfPCell titleCell = new PdfPCell(new Phrase(ticket.getTitle(), font));
				titleCell.setPaddingLeft(6);
				titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				titleCell.setBorderWidth(2);
				table.addCell(titleCell);

				PdfPCell startDayCell = new PdfPCell(new Phrase(String.valueOf(ticket.getStartDate())));
				startDayCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				startDayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				startDayCell.setPaddingRight(6);
				startDayCell.setBorderWidth(2);
				table.addCell(startDayCell);

				PdfPCell endDayCell = new PdfPCell(new Phrase(String.valueOf(ticket.getEndDate())));
				endDayCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				endDayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				endDayCell.setPaddingRight(6);
				endDayCell.setBorderWidth(2);
				table.addCell(endDayCell);

				
				
				PdfPCell descriptionCell = new PdfPCell(new Phrase(String.valueOf(ticket.getDescription()), font));
				descriptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setBorderWidth(2);
				descriptionCell.setPaddingRight(6);
				table.addCell(descriptionCell);
				
				PdfPCell placeCell = new PdfPCell(new Phrase(String.valueOf(ticket.getPlace()), font));
				placeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				placeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				placeCell.setBorderWidth(2);
				placeCell.setPaddingRight(6);
				table.addCell(placeCell);
				
				
				
				PdfPCell fullNameCell = new PdfPCell(new Phrase(String.valueOf(ticket.getFullName()), font));
				fullNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				fullNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				fullNameCell.setBorderWidth(2);
				fullNameCell.setPaddingRight(6);
				table.addCell(fullNameCell);
				
				String status = "";
				for (StatusEntity item : ticket.getStatus()) {
					status += item.getName() + " " + (new SimpleDateFormat("dd-MM-yyyy").format(item.getTime())) + "\n\n";
				}
				
				PdfPCell statusCell = new PdfPCell(new Phrase(String.valueOf( status )));
				statusCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				statusCell.setBorderWidth(2);
				statusCell.setPaddingRight(6);
				table.addCell(statusCell);

				
				PdfPCell technicianNameCell = new PdfPCell(new Phrase(String.valueOf(ticket.getTechnicianName()), font));
				technicianNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				technicianNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				technicianNameCell.setBorderWidth(2);
				technicianNameCell.setPaddingRight(6);
				table.addCell(technicianNameCell);
				
				
				PdfPCell modifiedCell = new PdfPCell(new Phrase(String.valueOf(ticket.getModifiedBy()), font));
				modifiedCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				modifiedCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				modifiedCell.setBorderWidth(2);
				modifiedCell.setPaddingRight(6);
				table.addCell(modifiedCell);

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
	
	
	
	 public static PdfPCell newCell(String text, Font font) {
		    PdfPCell pdfCell = null;
		    PdfPCell cell = null;

		    if (text == null) {
		      // create a blank cell
		      cell = new PdfPCell(new Phrase(" ", font));
		    }
		    else {
		      cell = new PdfPCell(new Phrase(text, font));
		    }

		    pdfCell = new PdfPCell(cell);

		    return pdfCell;
		  }


	
	
	
	
	

	public static void main(String[] args) throws DocumentException, Exception {

		
	}
	
}
