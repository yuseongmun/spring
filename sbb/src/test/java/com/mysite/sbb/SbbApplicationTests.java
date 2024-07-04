package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.QuestionService;

@SpringBootTest /* 스프링부트의 테스트 클래스임을 의미 */
class SbbApplicationTests {

//	@Autowired
//	private QuestionRepository questionRepository;
//	
//	@Autowired
//	AnswerRepository answerRepository;
	
	@Autowired
	private QuestionService questionService;
	
	/* 질문 데이터 저장(삽입) */
//	@Test
//	void testJpa() {
//		Question q1 = new Question();
//		q1.setSubject("subject");
//		q1.setContent("content");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		Question q2 = new Question();
//		q2.setSubject("subject2");
//		q2.setContent("content2");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
//	}
	
	/* 질문 데이터 조회 ALL */
//	@Test
//	void testJpa() {
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(4, all.size());
//		
//		Question q = all.get(0);
//		assertEquals("subject",q.getSubject());
//	}
	
	/* 질문 데이터 조회 */
//	@Test
//	void testJpa() {
//		Optional<Question> _q = this.questionRepository.findById(2);
//		if(_q.isPresent()) {
//			Question q = _q.get();
//			assertEquals("subject",q.getSubject());
//		}
//	}
	
	/* 질문 데이터 조회 */
//	@Test
//	void testJpa() {
//		Question q = this.questionRepository.findBySubject("subject");
//		assertEquals(1,q.getId());
//	}
	
	/* 질문 데이터 조회 AND */
//	@Test
//	void testJpa() {
//		Question q = this.questionRepository.findBySubjectAndContent("subject","content");
//		assertEquals(1, q.getId());
//	}
	
	/* 질문 데이터 조회 Like */
//	@Test
//	void testJpa() {
//		List<Question> qList = this.questionRepository.findBySubjectLike("su%");
//		Question q = qList.get(0);
//		assertEquals("subject", q.getSubject());
//	}
	
	/* 질문 데이터 수정 */
//	@Test 
//	void testJpa() {
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);
//	}
	
	/* 질문 데이터 삭제 */
//	@Test
//	void testJpa() {
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		assertEquals(1, questionRepository.count());
//	}
	
	/* 답변 데이터 저장 */
//	@Test
//	void testJpa() {
//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		
//		Answer a = new Answer();
//		a.setContent("네, 자동으로 생성됩니다.");
//		a.setQuestion(q);
//		a.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a);
//	}
	
	/* 답변 데이터 조회 */
//	@Test
//	void testJpa() {
//		Optional<Answer> oa = answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals(2, a.getQuestion().getId());
//	}
	
	/* 질문 통해 답변 찾기 Transactional */
//	@Transactional
//	@Test
//	void testJpa() {
//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		
//		List<Answer> answerList = q.getAnswerList();
//		
//		assertEquals(1, answerList.size());
//		assertEquals("네, 자동으로 생성됩니다.", answerList.get(0).getContent());
//	}
	
//	@Test
//	void testJpa() {
//		for(int i = 1; i<=300;i++) {
//			String subject = String.format("테스트 데이터 타입입니다:[%03d]", i);
//			String content = "내용 없음";
//			this.questionService.create(subject,content);
//		}
//	}
}
