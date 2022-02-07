package com.example.demo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
@Data
@Document(collection = "#{@jobRepository.getCollectionName()}")
//전체 데이터 클래스
public class TT
{
	@Id
	private String _id;
	private String word;
	private int count;
}