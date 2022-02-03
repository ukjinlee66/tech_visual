package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




//RestController = View로 응답하지 않는 Controller, method의 반환결과를 json형태로 반환
@RestController
//“/main”으로 들어오는 모든 요청에 대한 처리를 해당 클래스에서 한다는 것을 의미
@RequestMapping("/main")
@Primary
public class mainController {
	
	@Autowired
	private JobService jobService;
	
	
	//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(method = RequestMethod.GET)
    List<jobData> getJobList(){
        return jobService.getJobList();
    }
}