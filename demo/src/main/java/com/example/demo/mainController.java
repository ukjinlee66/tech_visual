package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

import lombok.Getter;


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
	@Autowired
	private jobRepository jobR;
	private myComparator comp = new myComparator();
	
	@GetMapping("/")
	public String mainPage(Model model) {
		jobService.setCollName("TT");
		
	
    	List<jobData> tt_list = jobService.getJobList();
    	Collections.sort(tt_list, comp);
    	//session에 저장
    	model.addAttribute("test",tt_list);
    	
    	
		System.out.println("main 호출");
		return "index";
	}
	
	//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(value="/List")
	public String getJobList(HttpServletRequest httpServeltRequest, jobData jd, Model model){
		jobService.setCollName("TT");
    	//HttpServletRequest request = a;
    	List<jobData> li = jobService.getJobList();
    	Collections.sort(li, comp);
    	model.addAttribute("jobList", li);
        return "index"; 
	}
	
	@RequestMapping("/mkGraph")
	public String mkGraph(HttpServletRequest httpServeltRequest ,Model model) {
		String searchKeyword = httpServeltRequest.getParameter("search");
		String[] deptName = new String[]{"SB", "FR", "PU", "AN", "DE"};
		String[] deptGr = new String[5];
		String[] modelName = new String[] {"Sec", "Th", "Fo", "Fi"};
		int i = 0;
		int j = 0;
		
		deptGr[4] = searchKeyword;
		jobService.setCollName(searchKeyword);
		List<jobData> li = jobService.getJobList();
		Collections.sort(li, comp);
		String tec1 = li.get(0).getWord();
		String tec2 = li.get(1).getWord();
		model.addAttribute("firG", li);
		
		while(true) { 
			if(j == 4) {
				break;
			}
			else if(!deptName[i].equals(searchKeyword)) {
				deptGr[j] = deptName[i];
				jobService.setCollName(deptName[i]);
				
				li = jobR.findByWord(tec1);
				Collections.sort(li, comp);
				model.addAttribute(modelName[j]+"F", li);
				
				li = jobR.findByWord(tec2);
				Collections.sort(li, comp);
				model.addAttribute(modelName[j]+"S", li);

				i++;
				j++;
			}
			else {
				i++;
				continue;
			}
		}
		model.addAttribute("dName", deptGr);
		return "index";
	}
	
	@Getter
	public enum SearchEx {
		SB("서버/백엔드"),
		FR("프론트엔드"),
		PU("웹 풀스택"),
		AN("안드로이드 앱"),
		IOS("아이폰 앱"),
		ML("머신러닝"),
		AI("인공지능(AI)"),
		DE("데이터 엔지니어"),
		MG("모바일 게임"),
		GC("게임 클라이언트"),
		GS("게임 서버"),
		SN("시스템/네트워크"),
		SS("시스템 소프트웨어"),
		IS("인터넷 보안"),
		EM("임베디드 소프트웨어"),
		RB("로보틱스 미들웨어"),
		QA("QA"),
		IO("사물인터넷(IoT)"),
		EX("응용 프로그램"),
		BC("블록체인");
		private String DBname;
		SearchEx(String DBname) {
			return;
		}
	}
	public static SearchEx getDBCode(String DBname) {
		for (SearchEx name : SearchEx.values()) {
			if (name.getDBname().equals(DBname)) {
				return name; 
			} 
		} 
		return null; 
	}
}


