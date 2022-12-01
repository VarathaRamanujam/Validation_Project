package com.loader.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.loader.demo.model.UserModel;
import com.loader.demo.repo.UserService;

@Controller
public class MyController {
	@Autowired
	private UserService obj;
	
	@GetMapping("/register")
	public String register_page(Model model) {
		
		model.addAttribute("registerRequest", new UserModel());
		return "register_page";
	}
	
	@GetMapping("/login")
	public String login_page(Model model) {
		model.addAttribute("loginRequest", new UserModel());
		return "login";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute UserModel usermodel) {
		System.out.println("register request :"+usermodel);
	    UserModel registered=obj.registerUser(usermodel.getLogin(),usermodel.getPassword(),usermodel.getEmail());
	    return registered == null ?"error":"redirect:/login";
	   // return "/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute UserModel usermodel ,Model model) {
		System.out.println("login request :"+usermodel);
	    UserModel auth=obj.authenticate(usermodel.getLogin(),usermodel.getPassword());
	   if(auth !=null) {
		   model.addAttribute("userLogin", auth.getLogin());
		   model.addAttribute("userEmail", auth.getEmail());
		   return "personal";
	   }else
		   return "error";
	}

}
