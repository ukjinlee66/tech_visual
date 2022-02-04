package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{
	@Autowired
	jobRepositoryCustomimpl jcon;
	
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
//			List<TT> list_tt = jcon.findAll();
			System.out.println(jcon.getList());
		}
		catch(Exception e)
		{
			System.out.println("ListError");
		}
	}
}