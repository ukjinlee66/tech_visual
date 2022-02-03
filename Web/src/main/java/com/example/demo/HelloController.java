package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController 
{
	@GetMapping("/java")
	public static void main(String[] args) 
	{
		System.out.println("Hello SP Boot Console ^^");
	}
	@GetMapping(value="/")
	public String Hello() 
	{
		System.out.println("Hello SP Boot JSP ^^)");
		return "chart";
	}
}
