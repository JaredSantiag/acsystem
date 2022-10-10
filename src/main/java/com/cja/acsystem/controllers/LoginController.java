package com.cja.acsystem.controllers;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

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
import org.springframework.web.client.RestTemplate;

import com.cja.acsystem.dto.LoginDTO;
import com.cja.acsystem.dto.RegistroDTO;
import com.cja.acsystem.entities.Licencia;
import com.cja.acsystem.entities.Role;
import com.cja.acsystem.entities.Usuario;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.exceptions.ResourceNotFoundExceptionString;
import com.cja.acsystem.repositories.LicenciaRepository;
import com.cja.acsystem.repositories.RoleRepository;
import com.cja.acsystem.repositories.UsuarioRepository;
import com.cja.acsystem.security.JWTAuthResonseDTO;
import com.cja.acsystem.security.JwtTokenProvider;

@RestController
@CrossOrigin
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

		Optional<Usuario> usuario = usuarioRepository.findByUsername(loginDTO.getUsername());
		Licencia licencia = usuario.get().getLicencia();

		// Consumiendo la api externa de licenciamiento
		RestTemplate plantilla = new RestTemplate();
		String resultado = plantilla.getForObject(
				"http://192.168.3.11:8080/licenciamiento/licencias/validacion/" + licencia.getNumeroLicencia(),
				String.class);

		// Validamos licencia
		if (resultado.equals("Licencia valida")) {

			//Validamos cambio de contraseña
			Date fechaActual = new Date();
			Date fechaActualizar = new Date(usuario.get().getUltimaActualizacion().getTime() + (90L * 86400000L) + 3600000L );
			Date fechaaux=new Date(usuario.get().getUltimaActualizacion().getTime());
			System.out.println(fechaaux);
			System.out.println(fechaActualizar);
			if(fechaActualizar.before(fechaActual)) {
				return new ResponseEntity<>("Actualizar contraseña", HttpStatus.BAD_REQUEST);
			}

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

		// Consumiendo la api externa de licenciamiento
		RestTemplate plantilla = new RestTemplate();
		Integer resultado = plantilla.getForObject("http://192.168.3.11:8080/licenciamiento/licencias/cantidadusuarios/" + licencia.getNumeroLicencia(),Integer.class);
		Integer cantUsuarios=usuarioRepository.usuariosExistentes();

		if(resultado <= cantUsuarios){
			return new ResponseEntity<>("Limite de usuarios superados", HttpStatus.BAD_REQUEST);
		}

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
		usuario.setUltimaActualizacion(registroDTO.getUltimaActualizacion());
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


	@PutMapping("/cambiarpassword/{username}")
	public ResponseEntity<?> actualizarUsuario(@RequestBody RegistroDTO registroDTO,
											   @PathVariable(value = "username") String username){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundExceptionString("Usuario", "username",username));

		if(!passwordEncoder.matches(registroDTO.getPassword(), usuario.getPassword())) {
			usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
			usuario.setUltimaActualizacion(registroDTO.getUltimaActualizacion());

			usuarioRepository.save(usuario);
			return new ResponseEntity<>("Contraseña actualizada", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("La contrseña no puede ser la misma", HttpStatus.BAD_REQUEST);
		}

	}

}

