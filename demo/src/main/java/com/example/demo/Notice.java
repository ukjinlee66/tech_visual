package com.example.demo;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "#{@enRepository.getCollectionName()}")
//전체 데이터 클래스
public class Notice
{
	@Id
	private String _id;
	private String qual;
	private String dept;
	private String prefer;
	private List<String> tech;
	private String company;
}