package br.com.klein.denis.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String irParaHome() {
		return "index";
	}
	
	@RequestMapping("/home2")
	public String irParaHomeDois() {
		return "index2";
	}
}
