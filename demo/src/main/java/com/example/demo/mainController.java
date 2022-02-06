package com.example.demo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.enumTest.SearchEx1;


//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)

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

@Controller
//“/main”으로 들어오는 모든 요청에 대한 처리를 해당 클래스에서 한다는 것을 의미
@SessionAttributes("test")
public class mainController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private enRepositoryCustom econ;
	
	private myComparator comp = new myComparator();
	
	@GetMapping("/")
	public String mainPage(Model model) {
		jobService.setCollName("TT");
		
	
  	List<jobData> tt_list = jobService.getJobList();
  	//int siz = jobService.getdeptList();
  	Collections.sort(tt_list, comp);
  	//session에 저장
  	model.addAttribute("test",tt_list);
  	//model.addAttribute("dsize",);
  	enumTest db = null;
  	String str = db.getDBCode(jobService.getCollectionName());
  	List<Notice> li = econ.findBydept(str); // mapping 함수사용.
  	int size = li.size();
  	List<Notice> li2 = econ.findAll(); // 전체 공고.
  	System.out.println(size + " " + li2.size() + " ");
  	model.addAttribute("deptVal", (size*100)/li2.size());
  	model.addAttribute("deptName", str); // 현재 해당 직무 이름.(맵핑이후)
  	
  	return "index";
	}
	
	//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(value="/List")
	public String getJobList(jobData jd, Model model){
  	
	jobService.setCollName("TT");
  	List<jobData> li = jobService.getJobList();
  	Collections.sort(li, comp);
  	model.addAttribute("jobList", li);
  	System.out.println(li);
      return "index"; 
	}
	
	//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(value="/List2")
	public String getdeptList(jobData jd, Model model)
	{
		String str = jobService.getCollectionName();
		System.out.println("str : " + str);
	  	List<Notice> li = econ.findBydept("프론트엔드");
	  	//Collections.sort(li, comp);
	  	int size = li.size();
	  	//model.addAttribute("deptVal", size);
	  	System.out.println("dept size : " + size);
	    return "index"; 
	}
	
}