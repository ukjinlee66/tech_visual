package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;


// job Class 생성 및 getter,setter 자동 생성
@Data
@Document(collection ="#{@job.getCollName()}")
public class jobData {
	
	@Id
	private String id;
	//private String SS;
	private String word;
	private String count;
	

	public jobData(String word, String count) {
		this.word = word;
		this.count = count;
	}
}