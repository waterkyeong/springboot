package edu.pnu;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

//	 현재 서버가 꺼지면 메모리가 날아가는 버전으로 하고있기 때문에 만들어 놓은 클래스.
@RequiredArgsConstructor	//autowired를 안쓰고 대신 사용하는것.
//@Component
public class DataInit implements ApplicationRunner { //Applicationrunner => 서버 구동 시 자동 실행 인터페이스(제일 먼저 구동함)
	private final BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		for(int i =1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title"+i)
//					.writer("member1")
					.content("content1"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build()
			);
		}
		
		for(int i =1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title"+i)
//					.writer("member2")
					.content("content2"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build()
					);
		}
		
	}
}
