package com.bolsadeideas.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Object findByName(String string);

}
