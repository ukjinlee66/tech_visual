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
	@Autowired(required=true)
	private jobRepository jcon;

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
			List<TT> list_tt = jcon.findByword("aws");
			System.out.println(list_tt);
		}
		catch(Exception e)
		{
			System.out.println("ListError");
		}
	}
}