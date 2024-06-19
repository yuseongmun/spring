package com.example.news;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller

//요청 매핑 - 요청이 왔을 때 어떤 컨트롤러가 호출되어야 하는지 알려줌
//이 컨트롤러의 경로 /news
@RequestMapping("/news")
public class NewsWebController {
	final NewsDAO dao; // NewsDAO형 dao 선언
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); // 콘솔창에 log를 남김
	
	@Value("${news.imgdir}") // 프로퍼티에 저장된 경로를
	String fdir; // fdir에 저장
	
	@Autowired // 의존성주입 (생성자)
	public NewsWebController(NewsDAO dao) { // NewsDAO형의 dao 객체 생성
		this.dao = dao; // 주입받은 NewsDAO 객체로 dao 초기화
	}
	
	@PostMapping("/add") // Post 방식으로 매핑 (url에 데이터 노출x)	
	public String addNews(@ModelAttribute News news, Model m, @RequestParam("file") MultipartFile file) { 
		try {			
			File dest = new File(fdir+"/"+file.getOriginalFilename()); // 경로+/+파일명(문자열)을 File형 dest에 저장			
 						 
			file.transferTo(dest);	// 문자열을 file로 변환
			
			news.setImg("/img/"+dest.getName()); // News 객체에 파일 이름 저장
			dao.addNews(news); // dao 객체 사용, news를 DB에 추가
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("뉴스 추가 과정에서 문제 발생!!"); // 콘솔에 log 출력
			m.addAttribute("error","뉴스가 정상적으로 등록되지 않았습니다!!"); // 데이터를 Model 객체 통해 뷰페이지에 전달, ${error} 형태로 사용 가능, list.jsp에서 확인 가능			
		}
		return "redirect:/news/list"; 
	}

	//뉴스 삭제
	@GetMapping("/delete/{aid}") // Get 방식, 경로 (/delete{aid})로 매핑
	public String deleteNews(@PathVariable int aid, Model m) { // @PathVariable 경로 변수{aid}를 표시하기 위해 메서드의 매개변수에 사용됨
		try {
			dao.delNews(aid); // dao 객체 사용, aid에 해당하는 뉴스 삭제		
		}catch(SQLException e) {
			e.printStackTrace();
			logger.warn("뉴스 삭제 과정에서 문제 발생!!"); 
			m.addAttribute("error","뉴스가 정상적으로 삭제되지 않았습니다!!"); 
		}
		return "redirect:/news/list"; // 뉴스 목록으로 리다이렉트
	}
	
	@GetMapping("/list") // Get 방식, 경로(/list)로 매핑
	public String listNews(Model m) {
		List<News> list;
		try {
			list = dao.getAll(); // dao 객체 사용, 모든 뉴스 조회
			m.addAttribute("newslist",list); // 조회한 뉴스목록을 모델 m에 저장
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("뉴스 목록 생성 과정에서 문제 발생!!"); // 콘솔에 log 출력
			m.addAttribute("error","뉴스 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "newsList";
	}
	
	@GetMapping("/{aid}") // Get 방식, 경로(/{aid})로 매핑, 
	public String getNews(@PathVariable int aid, Model m) { // @PathVariable 경로 변수{aid}를 표시하기 위해 메서드의 매개변수에 사용됨
		try {
			News n = dao.getNews(aid); // dao 객체 사용, aid에 해당하는 뉴스 조회, n에 저장 
			m.addAttribute("news",n); // 조회한 뉴스를 모델 m에 저장
		}catch(SQLException e) {
			e.printStackTrace();
			logger.warn("뉴스를 가져오는 과정에서 문제 발생!!"); 
			m.addAttribute("error","뉴스를 정상적으로 가져오지 못했습니다.");
		}
		return "newsView";
		
	}
}
