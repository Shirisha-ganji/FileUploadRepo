package com.UploadCSV.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.UploadCSV.Entity.UserInformation;
import com.UploadCSV.Helper.CSVHelper;
import com.UploadCSV.Repository.UserInformationRepository;
import com.opencsv.CSVWriter;

@Service
public class CSVService {
  @Autowired
  UserInformationRepository repository;
  


  public void save(MultipartFile file) {
    try {
      List<UserInformation> userInformations = CSVHelper.csvToUserInformation(file.getInputStream());
      System.out.println("2"+userInformations);
      int invalidRecordCounter=0;
      int duplicateRecordCounter=0;
      int successConuter=0;
      boolean duplicateflag=false;
      boolean flag=false;
      int id=0;
     
      for(UserInformation user :userInformations ) {
    	  if((user.getPhoneNumber().isEmpty() || user.getEmail().isEmpty())||( user.getPhoneNumber().length()!=10 || user.getEmail().contains("@")==false) ) {
    		  invalidRecordCounter++;
    		  System.out.println(invalidRecordCounter);
    	  		}
    	  		else{
    		  
				  List<UserInformation> l=getAllUser();
				  if(l.size()>0)
				  {
				  for(int i=0;i<l.size();i++) {
				  if(l.get(i).getUsername().equals(user.getUsername())&&
				  l.get(i).getEmail().equals(user.getEmail())&&
				  l.get(i).getPhoneNumber().equals(user.getPhoneNumber())) 
				  {
				  System.out.println("match"); 
				  duplicateRecordCounter++; 
				  duplicateflag=true;
				  break; } 
				  else 
				  { 
					  if(l.get(i).getUsername().equals(user.getUsername()) ||
						 l.get(i).getEmail().equals(user.getEmail()) ||
						 l.get(i).getPhoneNumber().equals(user.getPhoneNumber()))
					  {
						  flag=true;
						  duplicateflag=false; 
						  id=l.get(i).getId();
						  break; 
					  } 
					  else { 
					  flag=false; 
					  duplicateflag=false;
					  } 
					 } 
				  }
				  }
				  
				  if(flag)
				  {
					 repository.updateUSer(user.getUsername(),user.getEmail(),user.getPhoneNumber(),id);
					 System.out.println("b::**********");
				  }
				  if(!flag && !duplicateflag)
     	 		 {
					  repository.save(user);
	    			  successConuter++;
     	 		 }
    		  System.out.println("duplicateRecordCounter::"+duplicateRecordCounter);
    		  System.out.println("invalidRecordCounter::"+invalidRecordCounter);
    		  System.out.println("successConuter::"+successConuter);
    		 
				  
    	  }
      }
      	createCSVfile(invalidRecordCounter,duplicateRecordCounter,successConuter);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



public List<UserInformation> getAllUser() {
    return repository.findAll();
  }
public  void createCSVfile(int validationcount,int duplicatecount,int successcount) throws IOException
{
	File file = new File("C:\\Documents\\Files\\UserInfoReport.csv");
	 	FileWriter outputfile=null;
	try {
		outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);
        String[] header = { "Sr.No.", "ValidationError Count", "Duplicate Count","Success Count" };
        writer.writeNext(header);
        String[] data1 = { "1", validationcount+"", duplicatecount+"",successcount+"" };
        writer.writeNext(data1);
        writer.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		outputfile.close();
	}
}



}
