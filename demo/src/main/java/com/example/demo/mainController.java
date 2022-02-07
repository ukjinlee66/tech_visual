package com.example.demo;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

class myComparator implements Comparator<jobData> {
	@Override
	public int compare(jobData first, jobData second) {
		int firstValue = Integer.parseInt(first.getCount());
		int secondValue = Integer.parseInt(second.getCount());

		if (firstValue > secondValue) {
			return -1;
		} else if (firstValue < secondValue) {
			return 1;
		} else {
			return 0;
		}
	}
}

//RestController = View로 응답하지 않는 Controller, method의 반환결과를 json형태로 반환
@Controller
//“/main”으로 들어오는 모든 요청에 대한 처리를 해당 클래스에서 한다는 것을 의미
@SessionAttributes({ "sessionTT", "sessionTQ", "sessionTP" })
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
	public String mainPage(Model model) {

		// default collection 설정.
		List<Notice> li = econ.findBydeptRegex(".*데이터 엔지니어.*"); // default collection은 데이터 엔지니어 기준.
		int size = li.size();
		List<Notice> li2 = econ.findAll(); // 전체 공고.
		model.addAttribute("deptName", "데이터 엔지니어");
		model.addAttribute("deptVal", (size * 100) / li2.size());

		jobService.setCollectionName("DE");
		List<jobData> de_list = jobService.getJobList();
		Collections.sort(de_list, comp);

		// 해당 직무에서 가장 많이 사용되는 기술 top3.
		String te1 = de_list.get(0).getWord();
		int c1 = Integer.parseInt(de_list.get(0).getCount());
		String te2 = de_list.get(1).getWord();
		int c2 = Integer.parseInt(de_list.get(1).getCount());
		String te3 = de_list.get(2).getWord();
		int c3 = Integer.parseInt(de_list.get(2).getCount());
		// 전체 공고 중 전에 선택한 직무의 top3기술의 비율을 시각화한다.
		List<jobData> all_list = jobrepo.findAll();
		model.addAttribute("tech_one", (c1 * 100) / li.size());
		model.addAttribute("tech_two", (c2 * 100) / li.size());
		model.addAttribute("tech_three", (c3 * 100) / li.size());
		model.addAttribute("tech_one_name", te1);
		model.addAttribute("tech_two_name", te2);
		model.addAttribute("tech_three_name", te3);

		/*
		 * jobService.setCollectionName("TT");
		 * 
		 * List<jobData> tt_list = jobService.getJobList();
		 * 
		 * Collections.sort(tt_list, comp); // session에 저장
		 * model.addAttribute("sessionTT", tt_list); // 전체 직무.
		 */
		
		  //첫화면에 보여줄 DE정보 불러오기
		  jobService.setCollectionName("DEQ"); 
		  List<jobData> qual_list = jobService.getJobList();
		  Collections.sort(qual_list, comp);
		  
		  jobService.setCollectionName("DEP"); 
		  List<jobData> pref_list = jobService.getJobList();
		  Collections.sort(pref_list, comp);
		  
		  model.addAttribute("qualList",qual_list );
		  
		  model.addAttribute("prefList",pref_list );
		  
		  
		  
		  
		  jobService.setCollectionName("DEE"); 
		  List<jobData> ex_list = jobService.getJobList(); 
		  Collections.sort(ex_list, comp);
		  
		  // 경력무관(0) 필수표시 위해 추출 
		  String exep_zero=""; 
		  int n = 0; for (jobData jd :ex_list) 
		  { 
			  if (jd.getWord().equals("0")) 
			  { 	
				  exep_zero = jd.getCount();
			  		ex_list.remove(n); 
			  		break; 
			  } 
			  n++; 
		  } 
		  System.out.println(ex_list);
		  model.addAttribute("exepZero",exep_zero);
		  model.addAttribute("exep",ex_list );
		  
		  
		  //session에 저장할 List 저장 
		  jobService.setCollectionName("TT"); List<jobData> tt_list =
		  jobService.getJobList(); Collections.sort(tt_list, comp);
		  
		  jobService.setCollectionName("TQ"); List<jobData> tq_list =
		  jobService.getJobList(); Collections.sort(tq_list, comp);
		  
		  jobService.setCollectionName("TP"); List<jobData> tp_list =
		  jobService.getJobList(); Collections.sort(tp_list, comp);
		  
		  //초기 화면 출력용 DE List 저장
		  
		  
		  //session에 저장 
		  model.addAttribute("sessionTT",tt_list); //session에 저장
		  model.addAttribute("sessionTQ",tq_list); //session에 저장
		  model.addAttribute("sessionTP",tp_list);
		  
		  String[] deptName = new String[]{"SB", "FR", "PU", "AN", "DE"};
		  String[] deptGr = new String[5];
		  String[] modelName = new String[] {"Sec", "Th", "Fo", "Fi"};
		  
		  String tec1 = de_list.get(0).getWord();
	      	
	      String tec2 = de_list.get(1).getWord();
		  
		  model.addAttribute("firG", de_list);
		  enumTest en = new enumTest();

	      String inName = en.exchangeDBCode("DE");
	      deptGr[4] = inName;	
	      	
	      int tmp_i = 0; 
	      int tmp_j = 0;
			
	      while(true) { 
			if(tmp_j == 4) {
				break;
			}
			else if(!deptName[tmp_i].equals("DE")) {
				inName = en.exchangeDBCode(deptName[tmp_i]);
				deptGr[tmp_j] = inName;
				jobService.setCollectionName(deptName[tmp_i]);
					
				de_list = jobrepo.findByWord(tec1);
				Collections.sort(de_list, comp);
				model.addAttribute(modelName[tmp_j]+"F", de_list);
					
				de_list = jobrepo.findByWord(tec2);
				Collections.sort(de_list, comp);
				model.addAttribute(modelName[tmp_j]+"S", de_list);

				tmp_i++;
				tmp_j++;
			}
			else {
				tmp_i++;
				continue;
			}
		}
		model.addAttribute("dName", deptGr);
		  
		  
		  
		  
		System.out.println("main 호출");
		  
		  
		 
		return "index";
		}

	
	// 요청 URL을 어떤 method가 처리할지 mapping (Get,Post)
	@RequestMapping(value = "/search")
	public String getJobList(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception 
	{
		// 입력값 받기
		String search = request.getParameter("search");
	
		
		
		 
		
		String dept[] = {"서버/백엔드",
				"프론트엔드",
				"웹 풀스택",
				"안드로이드 앱",
				"아이폰 앱",
				"머신러닝",
				"인공지능",
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
				"사물인터넷",
				"응용 프로그램",
				"블록체인"};
		boolean check = false;
		String collName ="";
		enumTest en = new enumTest();
		for(int i=0;i<dept.length;i++)
		{
			if (search.equals(dept[i]))
			{
				//MongoDB collection선택
				collName = en.getDBCode(search);
				jobService.setCollectionName(collName);
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
		model.addAttribute("deptName", search); // 현재 해당 직무 이름.(맵핑이후)
		
		
		
		
		List<Notice> en_list = econ.findBydeptRegex(".*" + search+".*");
    	List<jobData> deptTech_list = jobService.getJobList();
      	Collections.sort(deptTech_list, comp);
      	
      	
      	
		
      	//해당 직무에서 가장 많이 사용되는 기술 top3.
      	String tec1 = deptTech_list.get(0).getWord();
      	int c1 = Integer.parseInt(deptTech_list.get(0).getCount());
      	String tec2 = deptTech_list.get(1).getWord();
      	int c2 = Integer.parseInt(deptTech_list.get(1).getCount());
      	String tec3 = deptTech_list.get(2).getWord();
      	int c3 = Integer.parseInt(deptTech_list.get(2).getCount());
      	
      	//?
      	
      	String[] deptName = new String[]{"SB", "FR", "PU", "AN", "DE"};
		String[] deptGr = new String[5];
		String[] modelName = new String[] {"Sec", "Th", "Fo", "Fi"};
      	
		model.addAttribute("firG", deptTech_list);
      	
      	String inName = en.exchangeDBCode(collName);
	    deptGr[4] = inName;	
      		
      	int tmp_i = 0; 
		int tmp_j = 0;
		
      	while(true) { 
			if(tmp_j == 4) {
				break;
			}
			else if(!deptName[tmp_i].equals(collName)) {
				inName = en.exchangeDBCode(deptName[tmp_i]);
				deptGr[tmp_j] = inName;
				jobService.setCollectionName(deptName[tmp_i]);
				
				deptTech_list = jobrepo.findByWord(tec1);
				Collections.sort(deptTech_list, comp);
				model.addAttribute(modelName[tmp_j]+"F", deptTech_list);
				
				deptTech_list = jobrepo.findByWord(tec2);
				Collections.sort(deptTech_list, comp);
				model.addAttribute(modelName[tmp_j]+"S", deptTech_list);

				tmp_i++;
				tmp_j++;
			}
			else {
				tmp_i++;
				continue;
			}
		}
		model.addAttribute("dName", deptGr);
		
      	
      	
      	
      	//해당직무의 전체 기술을 카운트한 후 비율을 시각화한다.
      	List<jobData> c1_list = jobrepo.findByWord(tec1);
      	List<jobData> c2_list = jobrepo.findByWord(tec2);
      	List<jobData> c3_list = jobrepo.findByWord(tec3);
      	
      	model.addAttribute("tech_one", (c1*100)/en_list.size());
      	model.addAttribute("tech_two", (c2*100)/en_list.size());
      	model.addAttribute("tech_three", (c3*100)/en_list.size());
      	model.addAttribute("tech_one_name", tec1);
      	model.addAttribute("tech_two_name", tec2);
      	model.addAttribute("tech_three_name", tec3);
		
		/////////////////////////////////////////	
		List<Notice> li = econ.findBydept(search); // mapping 함수사용.
		int size = en_list.size();
		List<Notice> li2 = econ.findAll(); // 전체 공고.
		model.addAttribute("deptVal", (size*100)/li2.size());
		////////////////////////////////////////
		
		// Collection List 불러오기 & 정렬
		jobService.setCollectionName(collName);
		List<jobData> techList = jobService.getJobList();
		Collections.sort(techList, comp);
		
		jobService.setCollectionName(collName+ "Q");
		List<jobData> qualList = jobService.getJobList();
		Collections.sort(qualList, comp);
		
		jobService.setCollectionName(collName+ "P");
		List<jobData> prefList = jobService.getJobList();
		Collections.sort(prefList, comp);

		jobService.setCollectionName(collName + "E");
		List<jobData> ex_list = jobService.getJobList();
		Collections.sort(ex_list, comp);

		// 경력무관(0) 필수표시 위해 추출
		String exep_zero = "";
		int n = 0;
		for (jobData jd : ex_list) {
			if (jd.getWord().equals("0")) {
				exep_zero = jd.getCount();
				ex_list.remove(n);
				break;
			}
			n++;
		}
		model.addAttribute("exepZero", exep_zero);
		model.addAttribute("exep", ex_list);

		// Total Tech
		model.addAttribute("searchList", techList);
		// 자격사항
		model.addAttribute("qualList", qualList);
		// 우대사항
		model.addAttribute("prefList", prefList);

		System.out.println(search);
		System.out.println(techList);
		return "index";
	}

}