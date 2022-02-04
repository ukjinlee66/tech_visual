package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;


class myComparator implements Comparator<jobData>{
	@Override
	public int compare(jobData first, jobData second) {
		int firstValue = Integer.parseInt(first.getCount());
		int secondValue = Integer.parseInt(second.getCount());
		
		if(firstValue > secondValue) {
			return -1;
		}else if(firstValue < secondValue) {
			return 1;
		}else {
			return 0;
		}
	}
}

//RestController = View로 응답하지 않는 Controller, method의 반환결과를 json형태로 반환
@Controller
//“/main”으로 들어오는 모든 요청에 대한 처리를 해당 클래스에서 한다는 것을 의미
@SessionAttributes("test")
public class mainController {
	
	@Autowired
	private JobService jobService;
	
	
	@GetMapping("/")
	public String mainPage() {
		
		System.out.println("start");
		return "index";
	}
	
	//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(value="/jobListfind")
    public String getJobList(jobData jd, Model model){
    	jobService.setCollName("TT");
    	
    	myComparator comp = new myComparator();
    	List<jobData> li = jobService.getJobList();
    	Collections.sort(li, comp);
    	model.addAttribute("jobList", li);
        return "index";
        
    }
//	@RequestMapping(value="/topTech")
//	public String getTopTech(jobData jd, Model model) {
//		
//		jobService.setCollName("TT");
//		
//		List<jobData> techlist = jobService.getJobList();
////		Arrays.sort(techlist, new Comparator<jobData>() {
////			@Override
////			public int compare(jobData a1, jobData a2) {
////				return a2 - a1;
////			}
////			
////			
////		});
//		System.out.println("techlist");
//		
//		
//	}

    
}


