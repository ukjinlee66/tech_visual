package com.example.demo;
import java.util.List;

public interface JobService 
{
	public String getCollectionName();
	void setCollectionName(String collectionName);
	public List<TT> getList();
}