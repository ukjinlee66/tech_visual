package com.example.demo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
public interface JobService 
{
	public String getCollectionName();
	void setCollectionName(String collectionName);
	public List<TT> getList();
	List<jobData> getJobList();
	Optional<jobData> getJob(String id);
	String getCollName();
	void setCollName(String collName);
	List<Notice> getdeptList(String dept);
}