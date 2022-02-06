package com.example.demo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("enRepository")
public class enRepositoryCustomimpl implements enService
{
	private static String collectionName = "entire";
	@Autowired
	private enRepositoryCustom jcon2;
	
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
	public List<Notice> getList()
	{
		List<Notice> li = jcon2.findAll();
		return li;
	}
}
