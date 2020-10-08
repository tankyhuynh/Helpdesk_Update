package com.helpdesk.Helpdesk_v2.API;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.Helpdesk_v2.Entity.TicketEntity;
import com.helpdesk.Helpdesk_v2.Service.TicketService;
import com.helpdesk.Helpdesk_v2.Utils.AbstractPDF;

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
	private AbstractPDF<TicketEntity> pdfTicket;
	
	@GetMapping(value = "/customers",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customersReport() throws Exception {
		
//        ByteArrayInputStream bis = pdfUtils.customerPDFReport(customers);

        
        
//      String[] fieldName = {"id", "username", "password", "fullName", "roles", "token"};
        String[] fieldName = {"Id", "Title", "Start Date", "End Date", "Image"};
        
        List<TicketEntity> ticketEntities = ticketService.findAll();
        ByteArrayInputStream bis = pdfTicket.customerPDFReport(fieldName, ticketEntities, TicketEntity.class);
        
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
}
