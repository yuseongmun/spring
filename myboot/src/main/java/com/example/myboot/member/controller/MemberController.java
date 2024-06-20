package com.example.myboot.member.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myboot.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberController { // MemberController 인터페이스
	
	// ModelAndView : 컨트롤러와 뷰 사이 데이터 전달 및 처리 지원	
	// 회원 목록 조회 
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception; // HttpServletRequest : Servlet과 Http 사이의 요청 및 응답을 위해 사용
	
	// 회원 추가 
	// @ModelAttribute : 요청 파라미터를 컨트롤러 메서드의 매개변수로 변환하며, "info"로 설정
	public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO,HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	// 회원 삭제
	// @RequestParam : request.getParameter와 비슷함
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	// 로그인
	// @ModelAttribute : 요청 파라미터를 컨트롤러 메서드의 매개변수로 변환하며, "member"로 설정
	public ModelAndView login(@ModelAttribute("member") MemberVO memberVO, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 로그아웃
	// 요청 파라미터 없이 단순 세션 삭제, 따라서 ModelAttribute 사용 안 함
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회원 정보 수정
	// @ModelAttribute : 요청 파라미터를 컨트롤러 메서드의 매개변수로 변환하며, "member"로 설정
	public ModelAndView updateMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 로그인 상태 확인
	// @ModelAttribute : 요청 파라미터를 컨트롤러 메서드의 매개변수로 변환하며, "member"로 설정
	public ModelAndView loginchk(@ModelAttribute("member") MemberVO memberVO, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
