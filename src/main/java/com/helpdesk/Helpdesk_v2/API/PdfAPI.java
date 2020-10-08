package com.helpdesk.Helpdesk_v2.API;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Entity.UserEntity;
import com.helpdesk.Helpdesk_v2.Service.TicketService;
import com.helpdesk.Helpdesk_v2.Utils.AbstractPDF;
import com.helpdesk.Helpdesk_v2.Utils.PDFUtils;

/**
* @author root {4:14:19 PM}:
 * @version Creation time: Oct 7, 2020 4:14:19 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@RestController
public class PdfAPI {

	@Autowired
	private TicketService ticketService;
	

	@Autowired
	private PDFUtils<TicketEntity> pdfTicket;
	
	@Autowired
	private AbstractPDF abstractPDF;
	
	@GetMapping(value = "/v1/pdf",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customersReport() throws Exception {
		
//        
//        Field[] field = TicketEntity.class.getDeclaredFields();
//        String[] fieldName = new String[field.length];
//        
//        int i=0;
//        for (Field f : field) {
//			fieldName[i] = f.getName();
//			i++;
//		}
//        
//        System.out.println("List Field Name: ");
//        for (String string : fieldName) {
//			System.out.print("\t"+ string);
//		}
		
		List<TicketEntity> ticketEntities = ticketService.findAll();
        
//      ByteArrayInputStream bis = abstractPDF.customerPDFReport(fieldName, ticketEntities);
        

        String[] fieldName = {"Id", "Title", "startDate", "endDate", "description", "place", "fullName", "status", "technicianName", "modifiedBy"};
        ByteArrayInputStream bis = pdfTicket.customerPDFReport(fieldName ,ticketEntities);
        
        
        
        

        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
}
