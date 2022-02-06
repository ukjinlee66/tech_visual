package com.example.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface enRepositoryCustom extends MongoRepository<Notice, String>
{
	public List<Notice> findBydept(String dept);
	public List<Notice> findBycompany(String company);
	public List<Notice> findBydeptRegex(String query);
}
