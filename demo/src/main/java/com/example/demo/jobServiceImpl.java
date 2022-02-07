package com.example.demo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


//비지니스 로직 수행하는 Class
@Service
@Component("job")
public class jobServiceImpl implements JobService {
	
	
	
	
	@Autowired
    private jobRepository jRepository;
	
	@Autowired
	private enRepositoryCustom econ;
	
	
	
	private static String collectionName = "DE";


    public List<jobData> getJobList(){
        List<jobData> jobList = jRepository.findAll();
        return jobList;
    }
    
    public String getCollName(){
    	
    	return collectionName;
    }
    
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
	public List<Notice> getdeptList(String dept)
	{
		List<Notice> deptList = econ.findBydept(dept);
		return deptList;
	}
}