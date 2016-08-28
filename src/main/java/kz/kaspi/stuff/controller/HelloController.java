package kz.kaspi.stuff.controller;


import kz.kaspi.stuff.dao.*;
import kz.kaspi.stuff.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/test")
public class HelloController {

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AnswerDAO answerDAO;

	@Autowired
	private RoleDAO roleDao;

    @Autowired
    private CategoryDAO categoryDAO;

	@RequestMapping(value = "users", method = RequestMethod.GET)
		 public String showUsers(ModelMap model) {
		List<User> us = userDAO.getList();
		for (User u : us) {
			System.out.println(u.getUsername());
			System.out.println(u.getRole().getRole());
		}
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "quests", method = RequestMethod.GET)
		 public String showQuests(ModelMap model) {
		List<Question> us = questionDAO.getList();
		for (Question u : us) {
			System.out.println(u.getDescription());
//			Hibernate.initialize(u.getCategory());
			System.out.println(u.getCategory().getName());
		}
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "answers", method = RequestMethod.GET)
	public String showAnswers(ModelMap model) {
		List<Answer> us = answerDAO.getList();
		for (Answer u : us) {
			System.out.println(u.getInformation());
//			Hibernate.initialize(u.getCategory());
			System.out.println(u.getQuestion().getDescription());
		}
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}