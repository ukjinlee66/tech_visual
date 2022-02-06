package com.example.demo;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//default collection 설정.
	  	List<Notice> li = econ.findBydeptRegex(".*데이터 엔지니어.*"); // default collection은 데이터 엔지니어 기준.
	  	int size = li.size();
	  	List<Notice> li2 = econ.findAll(); // 전체 공고.
	  	model.addAttribute("deptName","데이터 엔지니어");
	  	model.addAttribute("deptVal", (size*100)/li2.size());
	  	
	  	
	  	jobService.setCollectionName("DE");
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
      	model.addAttribute("tech_one", (c1*100)/li.size());
      	model.addAttribute("tech_two", (c2*100)/li.size());
      	model.addAttribute("tech_three", (c3*100)/li.size());
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
	@RequestMapping(value="/search")
	public String getJobList(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception
	{
		//입력값 받기
		String search = request.getParameter("search");
		System.out.println("Search : " + search);
		//mapping
		String dept[] = {"서버/백엔드",
				"프론트엔드",
				"웹 풀스택",
				"안드로이드 앱",
				"아이폰 앱",
				"머신러닝",
				"인공지능(AI)",
				"데이터 엔지니어",
				"모바일 게임",
				"게임 클라이언트",
				"게임 서버",
				"시스템/네트워크",
				"시스템 소프트웨어",
				"인터넷 보안",
				"임베디드 소프트웨어",
				"로보틱스 미들웨어",
				"QA",
				"IoT",
				"응용 프로그램",
				"블록체인"};
		boolean check = false;
		String str ="";
		enumTest en = new enumTest();
		for(int i=0;i<dept.length;i++)
		{
			if (search.equals(dept[i]))
			{
				//MongoDB collection선택
				str = en.getDBCode(search);
				jobService.setCollName(str);
				check = true;
				break;
			}
		}
		if (!check)
		{
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 형식입니다 해당 직무의 완전한 형식으로 작성해주세요.');location.href='/';</script>");
				out.flush();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		model.addAttribute("deptName", jobService.getCollName()); // 현재 해당 직무 이름.(맵핑이후)
		
		//Collection List 불러오기 & 정렬
//    	List<jobData> techList = jobService.getJobList();
//    	Collections.sort(techList, comp);
//    	
//    	
//    	List<jobData> qualList = jobService.getJobList();
//    	Collections.sort(qualList, comp);
//    	
//    	//
//    	model.addAttribute("searchList", techList);
//    	model.addAttribute("qualList",qualList );
//    	
//    	System.out.println(search);
//    	System.out.println(techList);
    	
    	/////////////////////////////////////
    	//해당 직무 기술개수.
		List<Notice> en_list = econ.findBydeptRegex(".*" + search+".*");
    	List<jobData> de_list = jobService.getJobList();
      	Collections.sort(de_list, comp);
      	//해당 직무에서 가장 많이 사용되는 기술 top3.
      	String te1 = de_list.get(0).getWord();
      	int c1 = Integer.parseInt(de_list.get(0).getCount());
      	String te2 = de_list.get(1).getWord();
      	int c2 = Integer.parseInt(de_list.get(1).getCount());
      	String te3 = de_list.get(2).getWord();
      	int c3 = Integer.parseInt(de_list.get(2).getCount());
      	//해당직무의 전체 기술을 카운트한 후 비율을 시각화한다.
      	List<jobData> c1_list = jobrepo.findByword(te1);
      	List<jobData> c2_list = jobrepo.findByword(te2);
      	List<jobData> c3_list = jobrepo.findByword(te3);
      	model.addAttribute("tech_one", (c1*100)/en_list.size());
      	model.addAttribute("tech_two", (c2*100)/en_list.size());
      	model.addAttribute("tech_three", (c3*100)/en_list.size());
      	model.addAttribute("tech_one_name", te1);
      	model.addAttribute("tech_two_name", te2);
      	model.addAttribute("tech_three_name", te3);
      	
      	/////////////////////////////////////////	
      	List<Notice> li = econ.findBydept(search); // mapping 함수사용.
      	int size = en_list.size();
      	List<Notice> li2 = econ.findAll(); // 전체 공고.
      	model.addAttribute("deptVal", (size*100)/li2.size());
      	////////////////////////////////////////
        return "index"; 
	}
	
}