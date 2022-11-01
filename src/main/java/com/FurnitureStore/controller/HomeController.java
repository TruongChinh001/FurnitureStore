package com.FurnitureStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("home")
//	@ResponseBody
	public String index() {
		return "test";
	}

}
