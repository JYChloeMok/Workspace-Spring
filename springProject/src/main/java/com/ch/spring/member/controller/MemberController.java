package com.ch.spring.member.controller;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ch.spring.member.model.service.MemberService;
import com.ch.spring.member.model.vo.CertVO;
import com.ch.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final JavaMailSender sender;
	
	
	@PostMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser != null && bCryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("errorMsg", "로그인 실패")
			  .setViewName("common/errorPage");
		}
		return mv;
	}
	// 매개변수 m과 loginUser의 값
	/* 매개변수 m의 userId필드 : 사용자가 입력한 아이디
	 * 매개변수 m의 userPwd필드 : 사용자가 입력한 비밀번호(평문)
	 * ----- DB에 다녀온 후 -----
	 * loginUser의 userId필드 : 조회된 아이디
	 * loginUser의 userPwd필드 : 암호화되어 DB에 저장된 비밀번호
	 */
	
	// 암호화 Encrypt
	// DB의 암호문은 복호화 불가능
	/* bCryptPasswordEncoder 암호화 규칙 존재
	 * => 같은 평문을 같은 방법 / 횟수로 돌리면 같은 값이 만들어짐
	 * matches() : 암호문에 있는 salt값을 판단, 평문에 이 salt값을 더해 암호화 진행, 두 값이 같은지 비교
	 */
	
	// 인코딩은 필터(web.xml) / Member의 age는 String형 선언(값 없을 때 빈문자열, int일 경우 자료형이 맞지 않음)

	
	@GetMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}
	
	@PostMapping("insert.me")
	public String insertMember(Member m, Model model) {
		// 암호화
		m.setUserPwd(bCryptPasswordEncoder.encode(m.getUserPwd()));
		if(memberService.insertMember(m) > 0) {
			return "redirect:/";
		}
		model.addAttribute("errorMsg", "회원가입 실패");
		return "common/errorPage";
	}
	
	@GetMapping("mypage.me")
	public String myPage() {
		return "member/myPage";
	}
	
	@PostMapping("update.me")
	public String UpdateMember(Member m, Model model, HttpSession session) {
		if(memberService.updateMember(m) > 0) {
			session.setAttribute("loginUser", memberService.loginMember(m));
			session.setAttribute("alertMsg", "정보 수정에 성공하였습니다!");
			return "redirect:myPage.me";
		}
		model.addAttribute("erorrMsg", "정보 수정에 실패하였습니다.");
		return "common/errorPage";
	}
	
	@PostMapping("delete.me")
	public String deleteMember(String userPwd, HttpSession session) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(!(bCryptPasswordEncoder.matches(userPwd, loginUser.getUserPwd()))) {
			session.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다");
			return "redirect:myPage.me";
		}
		
		if(memberService.deleteMember(loginUser.getUserId()) > 0) {
			session.removeAttribute("loginUser");
			session.setAttribute("alertMsg", "탈퇴가 완료되었습니다.");
			return "redirect:/";
		} else {
			session.setAttribute("errorMsg", "탈퇴처리 실패");
			return "redirect:errorPage.me";
		}
		/*
		if(bCryptPasswordEncoder.matches(userPwd, loginUser.getUserPwd())) {
			if(memberService.deleteMember(loginUser.getUserId()) > 0) {
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "탈퇴가 완료되었습니다.");
				return "redirect:/";
			} else {
				session.setAttribute("errorMsg", "탈퇴처리 실패");
				return "redirect:errorPage.me";
			}
		} else {
			session.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다");
			return "redirect:myPage.me";
		}
		*/
	}
	
	@GetMapping("inputmail")
	public String inputMail() {
		return "member/input";
	}
	
	@GetMapping("checkPage")
	public String checkPage() {
		return "member/check";
	}
	
	@PostMapping("mail")
	public String mail(String email, HttpServletRequest request) throws MessagingException {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		String ip = request.getRemoteAddr();
		
		Random r = new Random();
		int i = r.nextInt(100000);
		Format f = new DecimalFormat("000000");
		String secret = f.format(i);
		
		CertVO certVo = CertVO.builder().who(ip).secret(secret).build();
		memberService.insertSecret(certVo);
		
		helper.setTo(email);
		helper.setSubject("인증번호입니다");
		helper.setText("인증번호 : " + secret);
		sender.send(message);
		
		return "redirect:checkPage";
	}
	
	@ResponseBody
	@PostMapping("check")
	public String checkCode(String secret, HttpServletRequest request) {
		CertVO certVo = CertVO.builder()
							  .who(request.getRemoteAddr())
							  .secret(secret)
							  .build();
		boolean result = memberService.validate(certVo);
		return "result : " + result;
	}


}
