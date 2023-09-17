package com.blog;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/test")
	public ModelAndView getstaticdata() {
		logger.info("enter in static data {}" ,new Date());
		logger.warn("enter in static data");
		logger.trace("enter in static data");
		return new ModelAndView("home.html");
		
	}
	
	@GetMapping("/test/t")
	public ModelAndView getDyanimicdata() {
		ModelAndView modelandview = new ModelAndView("product.html");
		modelandview.getModelMap().addAttribute("name","ayush");
		return modelandview;
	}

}
