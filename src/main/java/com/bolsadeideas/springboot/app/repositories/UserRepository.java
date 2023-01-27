package com.bolsadeideas.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findUserByEmail(String email);

	User findByUsername(String username);

}
