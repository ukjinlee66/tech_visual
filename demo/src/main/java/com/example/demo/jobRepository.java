package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//DAO Class, DataBase에 접근하는 method를 가짐
@Repository
public interface jobRepository extends MongoRepository<jobData,String> {
	
	public List<jobData> findAll();

	public List<jobData> findByWord(String te1);
	
	
	
}


