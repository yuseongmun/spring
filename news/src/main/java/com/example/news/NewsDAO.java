package com.example.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component	
public class NewsDAO {	
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC 드라이버
		final String JDBC_URL = "jdbc:mysql://localhost:3306/jwbookdb?useUnicode=true&characterEncoding=utf-8"; // DB연결 url

		// DB 연결을 가져오는 메서드
		public Connection open() {
			Connection conn = null;
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, "root", "1234");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}

		public List<News> getAll() throws Exception { // DB에서 모든 뉴스 목록 가져오는 메서드
			Connection conn = open();
			List<News> newsList = new ArrayList<>(); // 뉴스 기사 객체들의 목록 List<News>형의 newsList
			
			String sql = "SELECT aid, title, date as cdate from news"; // SQL문 정의
			PreparedStatement pstmt = conn.prepareStatement(sql); // SQL문을 실행할 PreparedStatement 객체 생성
			ResultSet rs = pstmt.executeQuery(); // SELECT이므로 executeQuery(), 결과를 rs에 저장

			try (conn; pstmt; rs) {
				while (rs.next()) { // DB에 다음에 가져올 정보가 있다면 반복
					// News형 n에 aid,title,cdate를 가져와 저장
					News n = new News();
					n.setAid(rs.getInt("aid"));
					n.setTitle(rs.getString("title"));
					n.setDate(rs.getString("cdate")); 
					// n을 newsList에 추가
					newsList.add(n); 
				}
				return newsList;
			}
		}

		public News getNews(int aid) throws SQLException { // aid에 해당하는 뉴스 정보 가져오기
			Connection conn = open();
			News n = new News(); // News형 n 선언
			
			String sql = "SELECT aid, title, img, date as cdate, content FROM news WHERE aid=?"; // SQL문 정의
			PreparedStatement pstmt = conn.prepareStatement(sql); // SQL문을 실행할 PreparedStatement 객체 생성
			pstmt.setInt(1, aid); // DB의 aid와 매개변수 aid가 일치하는지 확인
			ResultSet rs = pstmt.executeQuery(); // SELECT이므로 executeQuery(), 결과를 rs에 저장

			rs.next(); // 가져온 rs의 다음 행으로 커서 이동하는 메서드
			try (conn; pstmt; rs) {
				// n에 가져온 aid,title,img,cdate,content 저장
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setImg(rs.getString("img"));
				n.setDate(rs.getString("cdate"));
				n.setContent(rs.getString("content"));				
				// pstmt.executeQuery(); 없어도 되는 코드 ?
				return n;
			}
		}

		public void addNews(News n) throws Exception { // 뉴스 기사 DB에 추가하기
			
			Connection conn = open();
			String sql = "INSERT INTO news(title,img,date,content) VALUES(?,?,CURRENT_TIMESTAMP(),?)"; // SQL문 정의
			PreparedStatement pstmt = conn.prepareStatement(sql); // SQL문을 실행할 PreparedStatement 객체 생성

			try (conn; pstmt) {
				// 입력받은 title, img, content 가져와서 SQL문 ?에 대입
				pstmt.setString(1, n.getTitle());
				pstmt.setString(2, n.getImg());
				pstmt.setString(3, n.getContent());
				pstmt.executeUpdate(); // INSERT이므로 executeUpdate(), 뉴스 기사 추가
			}
		}

		public void delNews(int aid) throws SQLException { // 뉴스 기사 DB에서 삭제하기
			
			Connection conn = open();
			String sql = "DELETE FROM news WHERE aid=?"; // SQL문 정의
			PreparedStatement pstmt = conn.prepareStatement(sql); // SQL문을 실행할 PreparedStatement 객체 생성

			try (conn; pstmt) {
				pstmt.setInt(1, aid); // 매개변수 aid와 DB의 aid가 일치한다면 해당 뉴스 삭제
				
				// 삭제된 뉴스 기사가 없을 경우
				if (pstmt.executeUpdate() == 0) {
					throw new SQLException("DB에러");
				}
			}
		}
	}
