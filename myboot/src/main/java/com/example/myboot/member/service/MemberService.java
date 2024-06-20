package com.example.myboot.member.service;

import java.util.List;

import com.example.myboot.member.vo.MemberVO;

public interface MemberService { // MemberService 인터페이스
	
	// 회원 목록 출력
	public List listMembers() throws Exception;
	
	// 회원 추가
	public int addMember(MemberVO memberVO) throws Exception;
	
	// 회원 삭제
	public int removeMember(String id)throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	// 회원 수정
	public int updateMember(MemberVO memberVO) throws Exception;
}
