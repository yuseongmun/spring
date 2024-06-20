package com.example.myboot.member.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.myboot.member.dao.MemberDAO;
import com.example.myboot.member.vo.MemberVO;


@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED) // @Transactional : 트랜잭션의 시작과 종료 자동으로 처리
public class MemberServiceImpl implements MemberService{ // MemberService 인터페이스의 구현클래스
	@Autowired
	private MemberDAO memberDAO; // memberDAO 객체 주입받음

	@Override
	public List listMembers() throws Exception {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList(); // 모든 회원 목록 DB에서 조회 후 리스트에 저장
		return membersList;
	}

	@Override
	public int addMember(MemberVO member) throws Exception {
		return memberDAO.insertMember(member);	// 회원 DB에 추가
	}

	@Override
	public int removeMember(String id) throws Exception {
		return memberDAO.deleteMember(id); // 해당 id 회원 DB에서 삭제
	}

	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		return memberDAO.loginById(memberVO); // 아이디와 비밀번호로 로그인 시도
	}

	@Override
	public int updateMember(MemberVO memberVO) throws Exception {
		return memberDAO.updateMember(memberVO); // 회원 정보 DB에서 수정
	}
	
	
}
