package com.cja.acsystem.dto;

<<<<<<< HEAD
import java.util.Date;

=======
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
public class RegistroDTO {

	private String nombre;
	private String username;
	private String email;
	private String password;
	private String role;
<<<<<<< HEAD

	private Date ultimaActualizacion;


	public Date getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(Date ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

=======
	
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
	public RegistroDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}  

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}