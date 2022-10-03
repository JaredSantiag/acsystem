package com.cja.acsystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cja.acsystem.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsername (String username);
	
	public Boolean existsByUsername (String username);
	
	public Boolean existsByEmail(String email); 
	
	@Query(value = "SELECT count(id) FROM Usuario")
	public Integer usuariosExistentes(); 

	
}
