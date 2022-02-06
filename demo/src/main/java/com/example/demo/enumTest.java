package com.example.demo;

import com.example.demo.enumTest.SearchEx1;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class enumTest 
{
	@Getter
	@AllArgsConstructor
	public enum SearchEx1 
	{
		DE("데이터 엔지니어"),
		SB("서버/백엔드"),
		FR("프론트엔드"),
		PU("웹 풀스택"),
		AN("안드로이드 앱"),
		IOS("아이폰 앱"),
		ML("머신러닝"),
		AI("인공지능(AI)"),
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
	}
	public static String getDBCode(String search_key) 
	{
		for (SearchEx1 name : SearchEx1.values()) 
		{
			System.out.println(name.getDBname());
			if(name.getDBname().equals(search_key)) 
			{
				return name.toString();
			}
		} 
		return null; 
	}
}