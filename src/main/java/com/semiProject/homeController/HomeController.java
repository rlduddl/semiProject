package com.semiProject.homeController;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/favorite")
@Slf4j
public class HomeController {
	
	
	@GetMapping
	public String index(Model model) {
		return "index"; //templates/index.html로 이동
	}
	


}
