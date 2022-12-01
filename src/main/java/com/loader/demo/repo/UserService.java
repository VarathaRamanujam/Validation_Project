package com.loader.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loader.demo.model.UserModel;

@Service
public class UserService {
	
	@Autowired
	private UserRepo obj;

	public UserModel  registerUser(String login,String password,String email) {
		if(login==null || password==null) {
			return null;
		}else {
			if(obj.findBylogin(login).isPresent()) {
				System.out.println("Duplicate Login");
				return null;
			}
			UserModel usermodel = new UserModel();
			usermodel.setLogin(login);
			usermodel.setEmail(email);
			usermodel.setPassword(password);
		   return	obj.save(usermodel);		
		}	
	}
	
	
	public UserModel authenticate(String login,String password) {
		return 	obj.findByLoginAndPassword(login, password).orElse(null);
	}
	
	
	
	
	
	
	
	
	
}
