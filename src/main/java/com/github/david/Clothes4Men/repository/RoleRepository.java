package com.github.david.Clothes4Men.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.david.Clothes4Men.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
