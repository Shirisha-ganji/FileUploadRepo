package com.UploadCSV.Helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.UploadCSV.Entity.UserInformation;



public class CSVHelper {
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Usename", "email", "phonenumber" };

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }
	  
	  public static List<UserInformation> csvToUserInformation(InputStream is) {
		  
		  System.out.println("3");
		  List<UserInformation> userInformations = new ArrayList<UserInformation>();
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		    		
		    	
		   
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		    

		     System.out.println("4");

		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		      
		      System.out.println("5");
		 

		      for (CSVRecord csvRecord : csvRecords) {
		    	  UserInformation userInfromation = new UserInformation(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2));
		           
		           System.out.println("6");

		        userInformations.add(userInfromation);
		    
		      }
		      System.out.println(userInformations);

		     
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    return userInformations;
		  }
}