package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testFindByTitle() {
		List<Board> list = boardRepo.findByTitle("title10");
		System.out.println("--> testFindByTitle");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testByContentContaining() {
		
		Pageable paging = PageRequest.of(0, 5);
		List<Board> boardlist = boardRepo.findByContentContaining("5", paging);
		System.out.println("content 검색결과");
		for(Board b : boardlist) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testFindByTitleContainingOrContentContaining() {
		
		List<Board> boardlist = boardRepo.findByTitleContainingOrContentContaining("5","7");
		System.out.println("Or 검색결과");
		for(Board b : boardlist) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testFindByTitleContainingAndContentContaining() {
		
		List<Board> boardlist = boardRepo.findByTitleContainingAndContentContaining("5","7");
		System.out.println("And 검색결과");
		for(Board b : boardlist) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testQueryAnnotationTest() {
		List<Board> boardlist = boardRepo.queryAnnotationTest1("title10");
		System.out.println("--> testQueryAnnotation");
		for(Board b : boardlist) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testQueryAnnotationTest2() {
		List<Object[]> boardlist = boardRepo.queryAnnotationTest2("title10");
		System.out.println("--> testQueryAnnotation2");
		for(Object[] b : boardlist) {
			System.out.println(b);
		}
	}
}
