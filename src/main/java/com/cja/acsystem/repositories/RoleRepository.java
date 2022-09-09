package com.cja.acsystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cja.acsystem.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Optional<Role> findByNombre(String nombre);

}
