package com.bolsadeideas.springboot.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.repositories.RoleRepository;
import com.bolsadeideas.springboot.app.repositories.UserRepository;

//import com.example.exampleimdb.models.Role;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

/*	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public void saveWithUserRole(User user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles((List<Role>) roleRepository.findByName("ROLE_USER"));
		userRepository.save(user);
	}

	public void saveUserWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles((List<Role>) roleRepository.findByName("ROLE_ADMIN"));
		userRepository.save(user);
	}  */

	//  
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}

	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	// Buscar todos 
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	// Crear 
/*	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);

	} */
	
	public void createUser(User user) {
		userRepository.save(user);
	}

	// Actualizar 
	public void updateUser(User user) {
		userRepository.save(user);
	}

	// Eliminar 
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}


}
