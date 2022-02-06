package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@SessionAttributes("sessionTT")
public class mainController {
	
	@Autowired
	private JobService jobService;
	
	private myComparator comp = new myComparator();
	
	@GetMapping("/")
	public String mainPage(Model model) {
		
		//첫화면에 보여줄 DE정보 불러오기
		List<jobData> de_list = jobService.getJobList();
		model.addAttribute("qualList",de_list );
		
		//session에 저장할 TT 불러오기
		jobService.setCollName("TT");	
    	List<jobData> tt_list = jobService.getJobList();
    	Collections.sort(tt_list, comp);
    	//session에 저장
    	model.addAttribute("sessionTT",tt_list);
    	
    	
		System.out.println("main 호출");
		return "index";
	}
	
	//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(value="/search")
	public String getJobList(HttpServletRequest reponse, Model model){
    	
		//입력값 받기
		String search = reponse.getParameter("search");
		
		//MongoDB collection선택
		jobService.setCollName(search);	
		
		//Collection List 불러오기 & 정렬
    	List<jobData> techList = jobService.getJobList();
    	Collections.sort(techList, comp);
    	
    	
    	List<jobData> qualList = jobService.getJobList();
    	Collections.sort(qualList, comp);
    	
    	//
    	model.addAttribute("searchList", techList);
    	model.addAttribute("qualList",qualList );
    	
    	System.out.println(search);
    	System.out.println(techList);
        return "index"; 
	}
	
}


