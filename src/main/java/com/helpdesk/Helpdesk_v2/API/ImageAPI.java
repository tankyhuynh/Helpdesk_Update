package com.helpdesk.Helpdesk_v2.API;
/**
* @author root {1:52:49 PM}:
 * @version Creation time: Oct 29, 2020 1:52:49 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.Helpdesk_v2.Utils.FileUtils;

@RestController
@RequestMapping("/v1/image")
public class ImageAPI {

	@Autowired
	private FileUtils fileUtils;
	
	@PostMapping
	public String upload(@RequestBody String base64Image) {
		return fileUtils.decoder(base64Image, "ImageAPI");
	}
	
}
