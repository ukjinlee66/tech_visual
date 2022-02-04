package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("jobRepository")
public class jobRepositoryCustomimpl implements JobService
{
	private static String collectionName = "AI";
	
	@Autowired
	private jobRepositoryCustom jcon2;
	
	@Override
	public String getCollectionName()
	{
		return collectionName;
	}
	
	@Override
	public void setCollectionName(String collectionName)
	{
		this.collectionName = collectionName;
	}
	
	@Override
	public List<TT> getList()
	{
		List<TT> li = jcon2.findAll();
		return li;
	}
}
