package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface JobService {
	

	public String getCollectionName();
	void setCollectionName(String collectionName);
	List<jobData> getJobList();
	String getCollName();
	List<Notice> getdeptList(String dept);


}
