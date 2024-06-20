package com.example.myboot.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myboot.member.service.MemberService;
import com.example.myboot.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("memberController")
public class MemberControllerImpl implements MemberController { // MemberController 인터페이스의 구현클래스 
	
	@Autowired
	private MemberService memberService; 

	@Autowired
	private MemberVO memberVO; // memverVO 객체 주입

	@Override
	@GetMapping("/member/listMembers.do") 
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List membersList = memberService.listMembers();	// DB의 회원 목록을 memberList에 저장		
		ModelAndView mav = new ModelAndView("/member/listMembers"); // ModelAndView 객체 생성

		// 클라이언트(jsp, 뷰)에 값을 보내기 위해 redirect 사용하지 않음
		mav.addObject("membersList", membersList); // memberList를 뷰(jsp)에 전달
		return mav;
	}	

	@Override
	@PostMapping("/member/addMember.do")
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.addMember(member);

		//redirect : 페이지 내의 데이터와 함께 안 감 (페이지만 리로드)
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");	
		return mav;
	}

	@Override
	@GetMapping("/member/removeMember.do")
	public ModelAndView removeMember(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		// request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@Override
	@PostMapping("/member/updateMember.do")
	public ModelAndView updateMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		// request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.updateMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;    
	}
	
	@Override
	@PostMapping("/member/login.do")
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if(memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			
			String action = (String)session.getAttribute("action");
			session.removeAttribute("action");
			if(action != null) {
				mav.setViewName("redirect:"+action);
			}else {
				mav.setViewName("redirect:/member/listMembers.do");
			}
										
		}else {
			rAttr.addAttribute("result","loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}
	
	@Override
	@GetMapping("/member/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.setAttribute("isLogOn", false);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;		
	}
	
	@GetMapping("/member/*Form.do")
	private ModelAndView form(@RequestParam(value="result", required=false) String result, @RequestParam(value="action",required=false)String action, HttpServletRequest request,HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}
	
	@GetMapping({"/","/main.do"})
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String)request.getAttribute("viewname");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}


	@GetMapping("/member/loginchk.do")
	public ModelAndView loginchk(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		MemberVO memverVO = (MemberVO)session.getAttribute("member");
		
		if(memberVO != null) {
			mav.setViewName("/member/modMemberForm");			
													
		}else {
			rAttr.addAttribute("result","loginchkFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;		
	}	
	
	
	

}
