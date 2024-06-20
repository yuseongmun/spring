package com.example.myboot.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.myboot.member.vo.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO { // MyBatis 이용, SQL쿼리 실행하기 위한 DAO(Data Access Object) 인터페이스
	
	// member.xml의 SQL문 실행
	public List<MemberVO> selectAllMemberList() throws DataAccessException;	
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	public int updateMember(MemberVO memberVO) throws DataAccessException;
}
