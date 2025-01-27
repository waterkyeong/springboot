package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

//	 현재 서버가 꺼지면 메모리가 날아가는 버전으로 하고있기 때문에 만들어 놓은 클래스.
@Component
public class DataInit implements ApplicationRunner { //Applicationrunner => 서버 구동 시 자동 실행 인터페이스(제일 먼저 구동함)
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Member m1 = new Member();
		
		m1.setId("member1");
		m1.setPassword("member111");
		m1.setName("둘리");
		m1.setRole("User");
		memberRepo.save(m1);
		
		Member m2 = new Member();
		
		m2.setId("member2");
		m2.setPassword("member222");
		m2.setName("고길동");
		m2.setRole("Admin");
		memberRepo.save(m2);
		
		for(int i =1; i <= 5; i++) {
			Board board = new Board();		
			board.setTitle("둘리's title"+i);
			board.setContent("둘리's content"+i);
			board.setWriter("둘리");
			boardRepo.save(board);
		}
		
		for(int i =1; i <= 5; i++) {
			Board board = new Board();		
			board.setTitle("길동's title"+i);
			board.setContent("길동's content"+i);
			board.setWriter("고길동");
			boardRepo.save(board);
		}
		
	}
}
