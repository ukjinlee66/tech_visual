package com.example.demo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


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
	private JobService jobService2;
	
	@Autowired
	private jobRepository jobrepo;
	
	@Autowired
	private enRepositoryCustom econ;
	
	private myComparator comp = new myComparator();
	
	@GetMapping("/")
	public String mainPage(Model model) 
	{
	jobService.setCollectionName("DE");
	System.out.println("jobgetcoll : " + jobService.getCollectionName());
  	List<jobData> de_list = jobService.getJobList();
  	enumTest db = new enumTest();
  	String str = db.getDBCode(jobService.getCollectionName());
  	System.out.println("str : " + str);
  	List<Notice> li = econ.findBydept(str); // mapping 함수사용.
  	int size = li.size();
  	List<Notice> li2 = econ.findAll(); // 전체 공고.
  	System.out.println(size + " " + li2.size() + " ");
  	model.addAttribute("deptVal", (size*100)/li2.size());
  	model.addAttribute("deptName", str); // 현재 해당 직무 이름.(맵핑이후)
  	Collections.sort(de_list, comp);
  	
  	
  	
  	//해당 직무에서 가장 많이 사용되는 기술 top3.
  	String te1 = de_list.get(0).getWord();
  	int c1 = Integer.parseInt(de_list.get(0).getCount());
  	String te2 = de_list.get(1).getWord();
  	int c2 = Integer.parseInt(de_list.get(1).getCount());
  	String te3 = de_list.get(2).getWord();
  	int c3 = Integer.parseInt(de_list.get(2).getCount());
  	//전체 공고 중 전에 선택한 직무의 top3기술의 비율을 시각화한다.
  	List<jobData> all_list = jobrepo.findByword(te1); //1기술 전체 개수.
  	Collections.sort(all_list, comp);
  	System.out.println(c1);
  	model.addAttribute("tech_one", (c1*100)/Integer.parseInt(all_list.get(0).getCount()));
  	model.addAttribute("tech_two", (c2*100)/Integer.parseInt(all_list.get(0).getCount()));
  	model.addAttribute("tech_three", (c3*100)/Integer.parseInt(all_list.get(0).getCount()));
  	model.addAttribute("tech_one_name", te1);
  	model.addAttribute("tech_two_name", te2);
  	model.addAttribute("tech_three_name", te3);
		
	jobService.setCollName("TT");
			
  	List<jobData> tt_list = jobService.getJobList();
  	//int siz = jobService.getdeptList();
  	Collections.sort(tt_list, comp);
  	//session에 저장
  	model.addAttribute("test",tt_list); // 전체 직무.
  	
  	
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
	
	//요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(value="/search")
	public String getJobList(HttpServletRequest reponse, Model model)
	{
		
		//입력값 받기
		String search = reponse.getParameter("search");
		
		//MongoDB collection선택
		jobService.setCollName(search);	
		System.out.println("건우의 노력 : " + search);
		enumTest db = new enumTest();
		String str = db.getDBCode(jobService.getCollectionName());
		model.addAttribute("deptName", search); // 현재 해당 직무 이름.(맵핑이후)
		System.out.println("name : " + search);
		
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
    	
    	/////////////////////////////////////
    	//해당 직무 기술개수.
    	List<jobData> de_list = jobService.getJobList();
      	Collections.sort(de_list, comp);
      	//해당 직무에서 가장 많이 사용되는 기술 top3.
      	String te1 = de_list.get(0).getWord();
      	int c1 = Integer.parseInt(de_list.get(0).getCount());
      	String te2 = de_list.get(1).getWord();
      	int c2 = Integer.parseInt(de_list.get(1).getCount());
      	String te3 = de_list.get(2).getWord();
      	int c3 = Integer.parseInt(de_list.get(2).getCount());
      	//전체 공고 중 전에 선택한 직무의 top3기술의 비율을 시각화한다.
      	List<jobData> all_list = jobrepo.findAll();
      	int sum =0;
      	for(int i=0;i<all_list.size();i++)
      		sum+= Integer.parseInt(all_list.get(i).getCount());
      	System.out.println(sum);
      	model.addAttribute("tech_one", (c1*100)/sum);
      	model.addAttribute("tech_two", (c2*100)/sum);
      	model.addAttribute("tech_three", (c3*100)/sum);
      	model.addAttribute("tech_one_name", te1);
      	model.addAttribute("tech_two_name", te2);
      	model.addAttribute("tech_three_name", te3);
      	
      	/////////////////////////////////////////
      	jobService2.setCollName("TT");
      	List<Notice> li = econ.findBydept(str); // mapping 함수사용.
      	int size = li.size();
      	List<Notice> li2 = econ.findAll(); // 전체 공고.
      	System.out.println(size + " " + li2.size() + " ");
      	model.addAttribute("deptVal", (size*100)/li2.size());
      	
      	////////////////////////////////////////
        return "index"; 
	}
	
}