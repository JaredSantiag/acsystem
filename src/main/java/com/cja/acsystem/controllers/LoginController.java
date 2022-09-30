package com.cja.acsystem.controllers;

<<<<<<< HEAD
=======
import java.util.Date;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
import com.cja.acsystem.dto.LoginDTO;
import com.cja.acsystem.dto.RegistroDTO;
import com.cja.acsystem.entities.Licencia;
import com.cja.acsystem.entities.Role;
import com.cja.acsystem.entities.Usuario;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.repositories.LicenciaRepository;
import com.cja.acsystem.repositories.RoleRepository;
import com.cja.acsystem.repositories.UsuarioRepository;
import com.cja.acsystem.security.JWTAuthResonseDTO;
import com.cja.acsystem.security.JwtTokenProvider;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
=======

@RestController
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
@RequestMapping("/acsystem/login")
public class LoginController {
	
	@Value("${app.licencia-usuarios}")
	private int cantidadUsuarios;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private LicenciaRepository licenciaRepository;

	@PostMapping("/iniciarsesion")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {

		// Obtnemos la licencia del usuario
		Optional<Usuario> usuario = usuarioRepository.findByUsername(loginDTO.getUsername());
		Licencia licencia = usuario.get().getLicencia();

		long miliseconds = System.currentTimeMillis();
		Date fecha_actual = new Date(miliseconds);

		if (fecha_actual.before(licencia.getFechaFin())) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Obtenemos el token del jwtTokenProvider
			String token = jwtTokenProvider.generarToken(authentication);

			return ResponseEntity.ok(new JWTAuthResonseDTO(token));
		} else {
			return new ResponseEntity<>("Licencia Vencida", HttpStatus.UNAUTHORIZED);
		}

	}

	@PostMapping("/licencias/{licenciaId}/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO,
			@PathVariable(value = "licenciaId") Long licenciaId) {

		Licencia licencia = licenciaRepository.findById(licenciaId)
				.orElseThrow(() -> new ResourceNotFoundException("Licencia", "id", licenciaId));

		if (usuarioRepository.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		usuario.setLicencia(licencia);

		try {
			Role roles = roleRepository.findByNombre(registroDTO.getRole()).get();
			usuario.setRoles(Collections.singleton(roles));
		} catch (Exception error) {
			return new ResponseEntity<>("El Role no es valido", HttpStatus.BAD_REQUEST);
		}

		usuarioRepository.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
	}
}
