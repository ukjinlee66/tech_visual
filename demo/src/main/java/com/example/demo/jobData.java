package com.example.demo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// dept Class 생성 및 getter,setter 자동 생성
@Data
@Document(collection = "dept")
public class jobData 
{
	
	@Id
    private String deptno;
    private String dname;
    private String loc;
    
}