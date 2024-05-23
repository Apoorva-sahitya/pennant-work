package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import models.LoginModel;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String display() {
		return "LoginPage";
	}

	@RequestMapping(value = "/LoginSuccess", method = RequestMethod.POST)
	public String displaySucces(@Validated LoginModel userData, Model model) {

		model.addAttribute("user1", userData.getUsername());
		model.addAttribute("passWord", userData.getPassword());
		return "LoginSuccess";
	}

}
