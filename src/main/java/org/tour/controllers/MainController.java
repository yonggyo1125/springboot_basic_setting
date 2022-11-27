package org.tour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String mainPage(Model model) {
		
		String[] addScript = new String[] { "tour/search" };
		model.addAttribute("addScript", addScript);
		model.addAttribute("key", "ecb744762773804f037af730d26d0ff4");
		
		return "main/index";
	}
}