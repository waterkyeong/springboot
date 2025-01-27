package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration	//이 클래스가 설정 클래스라고 정의 (IoC 컨테이너에 로드)
@EnableWebSecurity //스프링 시큐리티 적용에 필요한 필터 객체들 자동 생성

public class SecurityConfig {
	
	@Bean	//이 메서드가 리턴 하는 객체를 IoC 컨테이너에 등록하라는 지시
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(security -> security		//접근권한 설정
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.csrf(cf->cf.disable());	//CSRF보호 비활성화(사이트간 요청 위조)
		http.formLogin(form->
				form.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess",true));		// ture =>/member를 호출해서 로그인 화면으로 왔을 때 로그인에 성공한 뒤 /loginSuccess로 이동하겠다는 의미
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));	//접근권한 없음 페이지 처리(위의 권한설정으로 권한이 없는 exception이 나면 권한없음 xml로 이동)
		
		http.logout(logout->logout
				.invalidateHttpSession(true)		//현재 브라우저와 연결된 세션 강제종료
				.deleteCookies("JSESSIONID")		//세션 아이디 저장된 쿠키 삭제
				.logoutSuccessUrl("/login"));		//로그아웃 후 이동할 URL 지정
		
		http.headers(hr->hr.frameOptions(fo->fo.disable()));	//h2 db 안떠서 추가

		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {	//db연결 전 임시 멤버 만든 메소드
//		auth.inMemoryAuthentication()
//			.withUser("manager")
//			.password("{noop}abcd")			//{noop} : No Operation ➔ 비밀번호가 암호화되어 있지 않다는 의미
//
//			.roles("MANAGER");
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password("{noop}abcd")
//			.roles("ADMIN");
//	}
}
