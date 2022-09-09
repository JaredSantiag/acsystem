package com.cja.acsystem.controllers;

import java.util.Date;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/acsystem/login")
public class LoginController {
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
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO, long licenciaId) {

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

		Licencia licencia = licenciaRepository.findById(licenciaId)
				.orElseThrow(() -> new ResourceNotFoundException("Licencia", "id", licenciaId));

		usuario.setLicencia(licencia);

		Role roles = roleRepository.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));

		usuarioRepository.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
	}
}
