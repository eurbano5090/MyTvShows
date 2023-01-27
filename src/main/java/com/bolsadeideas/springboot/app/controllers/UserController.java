package com.bolsadeideas.springboot.app.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.models.service.UserService;
import com.bolsadeideas.springboot.app.validator.UserValidator;




@Controller
public class UserController {

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model,
			@Valid @ModelAttribute("user") User user, BindingResult result) {
		if (error != null) {
			model.addAttribute("errorMessage", "Invalid credentials, please try again.");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "login";
	}

	@GetMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user") User user) {
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "registration";
		} else {
			userService.createUser(user);
			// userService.saveUserWithAdminRole(user);
			return "redirect:/login";

		}
	}
	
}
