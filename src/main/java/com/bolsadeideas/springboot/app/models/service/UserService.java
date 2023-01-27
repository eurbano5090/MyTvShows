package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.User;



public interface UserService {

//	public void saveWithUserRole(User user);
//	public void saveUserWithAdminRole(User user);
	public User findUserByEmail(String email);
	public User findByUsername(String username);
	public User findUserById(Long id) ;
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(Long id);
	public List<User> findAllUsers();
	
}
