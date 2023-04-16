package com.UploadCSV.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.UploadCSV.Entity.UserInformation;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> 
	{
	
	
	@Query("SELECT username from UserInformation u where u.username =:username")
	List<String> findList(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query("Update UserInformation u set u.username =:username ,u.email =:email,u.phoneNumber=:phoneNumber where u.id =:id")
	void updateUSer(@Param("username") String username,@Param("email") String email,@Param("phoneNumber") String phoneNumber,@Param("id") int id);
	
	
	}
