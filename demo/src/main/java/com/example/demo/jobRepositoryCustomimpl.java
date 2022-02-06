package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("jobRepository")
public abstract class jobRepositoryCustomimpl implements JobService
{
	private static String collectionName = "total";
	@Autowired
	private jobRepositoryCustom jcon2;
	
	public String getCollectionName()
	{
		return collectionName;
	}
	
	public void setCollectionName(String collectionName)
	{
		this.collectionName = collectionName;
	}
	
	public List<TT> getList()
	{
		List<TT> li = jcon2.findAll();
		//List<TT> li = jcon2.findByFirstnameRegex(collectionName);
		return li;
	}
}
