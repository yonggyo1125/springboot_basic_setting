package org.tour.commons;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.tour..controllers")
public class CommonErrorHandler {
	
	@ExceptionHandler(Exception.class)
	public String errorHandling(Exception e, Model model) {
		e.printStackTrace();
		String message = e.getMessage();
		model.addAttribute("message", message);
		return "common/errors";
	}
}

