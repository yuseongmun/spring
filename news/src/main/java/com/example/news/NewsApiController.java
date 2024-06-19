package com.example.news;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody

// 요청 매핑 - 요청이 왔을 때 어떤 컨트롤러가 호출되어야 하는지 알려줌
// 이 컨트롤러의 경로 /api/news
@RequestMapping("/api/news") 

public class NewsApiController {
	final NewsDAO dao; // NewsDAO형의 dao 필드 선언
	
	@Autowired // 의존성주입
	public NewsApiController(NewsDAO dao) { // NewsDAO형의 dao 객체 생성
		this.dao = dao; // 주입받은 NewsDAO 객체로 dao 초기화
	}
	
	// 뉴스 등록
	@PostMapping // Post방식으로 Mapping (url에 데이터 노출x)
	public String addNews(@RequestBody News news) { // @RequestBody : 본문 데이터를 자바 객체로 변환
		try {
			dao.addNews(news); // dao 객체 사용, 뉴스를 DB에 추가
		}catch(Exception e) {
			e.printStackTrace();
			return "News API: 뉴스 등록 실패!!";
		}
		return "News API: 뉴스 등록됨!!";
	}
	
	// 뉴스 삭제
	@DeleteMapping("{aid}")  // /api/news/{aid}로 매핑
	public String delNews(@PathVariable("aid")int aid){ // @PathVariable 경로 변수{aid}를 표시하기 위해 메서드의 매개변수에 사용됨
		try {
			dao.delNews(aid); // dao객체 사용, aid에 해당하는 뉴스를 DB에서 삭제
		}catch(SQLException e) {
			e.printStackTrace();
			return"News API: 뉴스 삭제 실패!! -"+ aid;
		}
		return "News API: 뉴스 삭제됨!! - "+ aid;
	}
	
	// 뉴스 목록
	@GetMapping // Get 방식으로 매핑(url에 데이터 노출o)
	public List<News> getNewsList(){ 
		List<News> newsList = null; // 리스트 초기화
		
		try {
			newsList = dao.getAll(); // dao 객체 사용, DB에서 모든 뉴스 목록 가져옴			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}
	
	// 뉴스 상세 정보
	@GetMapping("{aid}") // Get 방식으로 매핑(url에 데이터 노출o)
	public News getNews(@PathVariable("aid")int aid) { // @PathVariable 경로 변수{aid}를 표시하기 위해 메서드의 매개변수에 사용됨
		News news = null; // 뉴스 상세 정보 초기화
		try {
			news = dao.getNews(aid); // dao 객체 사용, DB에서 해당 뉴스 정보 가져옴
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return news;
	}
}
