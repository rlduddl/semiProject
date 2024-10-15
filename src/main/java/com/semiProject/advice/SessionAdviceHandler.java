package com.semiProject.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.semiProject.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class SessionAdviceHandler {
	@ModelAttribute
	public void addSessionAttributes(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		// 세션 정보 체크 후 세션이 있으면 model의 attribute 메서드를 이용해서 넣어준다.
		if (session != null) {
			MemberVO userInfo = (MemberVO) session.getAttribute("userInfo");
			model.addAttribute("userInfo", userInfo);
		}
	}

}
