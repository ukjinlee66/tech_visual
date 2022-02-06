package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface JobService {
	
	
	
	List<jobData> getJobList();
	
	//List<jobData> getJobListSort();
	
	Optional<jobData> getJob(String id);
	
	String getCollName();

	void setCollName(String collName);
	
	



}
