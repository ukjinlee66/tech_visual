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
	
	private static String collName = "job";
	
	
	public String getCollName() {
		return collName;
	}
	
	public void setCollName(String collName) {
		
		this.collName = collName;
	}
	
	
	public Optional<jobData> getJob(String id){
        
		Optional<jobData> get = jRepository.findById(id);
		
        return get;
    }

    public List<jobData> getJobList(){
        List<jobData> jobList = jRepository.findAll();
        return jobList;
    }

}
