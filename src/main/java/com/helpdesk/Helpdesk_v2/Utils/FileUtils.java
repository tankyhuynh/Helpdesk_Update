package com.helpdesk.Helpdesk_v2.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.tinify.Tinify;

@Component
public class FileUtils {
	
	
	static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
			"cloud_name", "dbqzpdgpi",
			"api_key", "463648723862277",
			"api_secret", "Bo1MvViceCLF9E0HB6hOz9MBC10"
			));
	
	public static String encoder(String imagePath) {
		  String base64Image = "";
		  File file = new File(imagePath);
		  try (FileInputStream imageInFile = new FileInputStream(file)) {
		    // Reading a Image file from file system
		    byte imageData[] = new byte[(int) file.length()];
		    imageInFile.read(imageData);
		    base64Image = Base64.getEncoder().encodeToString(imageData);
		  } catch (FileNotFoundException e) {
		    System.out.println("Image not found" + e);
		  } catch (IOException ioe) {
		    System.out.println("Exception while reading the Image " + ioe);
		  }
		  return base64Image;
		}
	
	
	public static String decoder(String base64Image, String pathFile) {
		
		String imageDataBytes;
		if (base64Image.contains(",")) {
			System.out.println("Constains ',' !!!!!!! ");
			imageDataBytes =base64Image.substring(base64Image.indexOf(",")+1);
		}
		else {
			 imageDataBytes = base64Image;
			 System.out.println("Not constains ',' !!!!!!! ");
		}
		
			
		try{
			  
		    byte[] imageByteArray = Base64.getDecoder().decode(imageDataBytes);

	        Map<String, String> map = cloudinary.uploader().upload(imageByteArray,
	                    ObjectUtils.asMap("resource_type", "auto", "eager", Arrays.asList(
	                            new Transformation().quality(30))) );
	        String URL = map.getOrDefault("url", "null URL");  
			  return URL;
		    
		  } catch (FileNotFoundException e) {
		    System.out.println("Image not found" + e);
		  } catch (IOException ioe) {
		    System.out.println("Exception while reading the Image " + ioe);
		  }
		  
		 return "https://res.cloudinary.com/dbqzpdgpi/image/upload/v1596088950/MrVans/MrVans_Image_df9p6s.png";
		  
		
		}



}
