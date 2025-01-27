package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class RelationMappingTest {
	@Autowired
	private MemberRepository memberRepo; 
	
	@Test
	@Order(1)
	public void testManyToOneInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
	
	
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("고길동");
		member2.setRole("Admin");
		
		for(int i =1; i <=3; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle("둘리가 등록한 게시글" +i);
			board.setContent("둘리가 등록한 게시글 내용"+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
		}
		memberRepo.save(member1);
		
		for(int i =1; i <=3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle("고길동이 등록한 게시글" +i);
			board.setContent("고길동이 등록한 게시글 내용"+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
		}
		memberRepo.save(member2);
	}
	
	@Test
	@Order(2)
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("member1").get();
		
		System.out.println("======================================");
		System.out.println(member.getName()+"가(이) 저장한 게시글 목록");
		System.out.println("======================================");
		List<Board> list = member.getBoardlist();
		for(Board board : list)
			System.out.println(board.toString());
	}
	
	@Test
	@Order(3)
	public void TestCascadeDelete() {
		 memberRepo.deleteById("member2");
	 }
	 
}
