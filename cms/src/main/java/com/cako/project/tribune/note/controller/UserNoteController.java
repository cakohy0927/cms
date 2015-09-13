package com.cako.project.tribune.note.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/tribune/user")
public class UserNoteController {

	@RequestMapping(value = "/note/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) {
		return "tribune/usernote/create";
	}
}
