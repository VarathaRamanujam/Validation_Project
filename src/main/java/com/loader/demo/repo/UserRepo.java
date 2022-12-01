package com.loader.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loader.demo.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer>{
	Optional<UserModel> findByLoginAndPassword(String login,String password); 
	
	Optional<UserModel> findBylogin(String login);
}
