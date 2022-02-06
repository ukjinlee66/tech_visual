package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{
//	@Autowired
//	jobServiceImpl jcon;
	@Autowired
	enRepositoryCustomimpl econ;
	@Autowired
	enRepositoryCustom econ2;
	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("run start");
		try 
		{
			//List<Notice> list_tt = econ.findAll();
			System.out.println(econ2.findBycompany("큐픽스"));
			//System.out.println(econ.getList());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}