package edu.pnu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	//localhost:8080/hello?name=홍길동
	//Hello 홍길동
	@GetMapping("/hello1")
	public ModelAndView hello(String name) {
		// /WEB-INF/board/abcd.jsp 호출
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("name", name);
		mv.setViewName("hello");
		
		return mv;
	}
	@GetMapping("/hello")
	public void hello(Model model) {
		// /WEB-INF/board/abcd.jsp 호출
		model.addAttribute("greeting","Hello 타임리프 ㅎㅎ");
	}
	
	@GetMapping("/hello2")
	public String hello(Model model, String name) {
		// /WEB-INF/board/abcd.jsp 호출
		
		model.addAttribute("name", name);
		
		return "hello";
	}
	
	@GetMapping("/hello3")
	public String hello() {
		// /WEB-INF/board/hello.jsp 호출
		return "hello";
	}
	
	
}
