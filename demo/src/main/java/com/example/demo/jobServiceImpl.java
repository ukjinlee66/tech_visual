package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//비지니스 로직 수행하는 Class
@Service
public class jobServiceImpl implements JobService {
	
	@Autowired
    private jobRepository jRepository;

    public List<jobData> getJobList(){
        List<jobData> jobList = jRepository.findAll();
        return jobList;
    }

}
