package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private final BoardRepository boardRepo;
	
	@GetMapping("/board")
	public List<Board> getBoards() {
//		List<Board> list = boardRepo.findAll();
//		return list;
		return boardRepo.findAll();
	}
	@GetMapping("/board/{seq}")
	public Board getBoard (@PathVariable Long seq) {
//		Board board = boardRepo.findById(seq).get(); =>.findById() 뒤로 .get()이 오류가 날때가 있다. 이때는 ~.orElseThrow();를 붙여주면 된다.
//		return board; 
		return boardRepo.findById(seq).get();
	}
	@PostMapping("/board")
	public Board postBoard(Board board) {
		return boardRepo.save(board);
	}
	@PutMapping("/board")
	public Board putBoard(Board board) {
		Board find = boardRepo.findById(board.getSeq()).get();
		if(board.getTitle() != null) find.setTitle(board.getTitle());
		if(board.getContent() != null) find.setContent(board.getContent());
		return boardRepo.save(find);
	}
	@DeleteMapping("/board/{seq}")
	public Board deleteBoard(@PathVariable Long seq) {
		Board board = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return board;
	}
}
