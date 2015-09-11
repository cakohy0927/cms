package com.cako.project.tribune.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/tribune")
public class TribuneController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		System.out.println("tribune 进来了");
		return "tribune/index/tribuneIndex";
	}
}
