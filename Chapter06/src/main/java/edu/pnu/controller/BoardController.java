package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")  
@Controller
public class BoardController {
	
//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<Board>();
//		
//		for(int i = 1; i <=10; i++) {
//			Board board = new Board();
//			board.setSeq((long) i);
//			board.setTitle("게시판 프로그램 테스트");
//			board.setWriter("도우너");
//			board.setContent("게시판 프로그램 테스트입니다...");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			
//			boardList.add(board);
//		}
//		model.addAttribute("boardList",boardList);
//		return "getBoardList";
//	}
	
	@Autowired
	private BoardService boardService;

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@RequestMapping(value = "/getBoardList", method = { RequestMethod.GET, RequestMethod.POST})
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) { //세션에 member라는 이름으로 저장된 데이터를 매개변수 member에  바인딩하도록 설정
		
		if(member.getId() == null) {	// 매개변수 member에 바인딩된 객체에 id가 저장되어 있지 않으면 로그인 화면으로 이동
			return "redirect:login";
		}
		
		List<Board> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList",boardList);
		return "getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member,Board board) {
		
		if(member.getId()==null) {
			return "redirect:login";
		}
		
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member,Board board, Model model) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		model.addAttribute("board",boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member,Board board) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member,Board board) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
}
