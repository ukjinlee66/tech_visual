package com.example.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//DAO Class, DataBase에 접근하는 method를 가짐
@Repository
public interface jobRepository extends MongoRepository<jobData,String> {
	
	List<jobData> findAll();

}
