package com.cja.acsystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "firmas")
public class Firma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 60)
	private String nombre;

	private boolean activo;

	private int diasPromesa;

	@JsonBackReference
	@OneToMany(mappedBy = "firma", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UnidadNegocio> unidadesNegocio = new HashSet<>();

	@JsonBackReference
	@OneToMany(mappedBy = "firma", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Accion> acciones = new HashSet<>();

	public Firma() {

	}

	public Firma(Long id, String nombre, boolean activo, int diasPromesa, Set<UnidadNegocio> unidadesNegocio, Set<Accion> acciones) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.diasPromesa = diasPromesa;
		this.unidadesNegocio = unidadesNegocio;
		this.acciones = acciones;
	}

	public Set<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(Set<Accion> acciones) {
		this.acciones = acciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getDiasPromesa() {
		return diasPromesa;
	}

	public void setDiasPromesa(int diasPromesa) {
		this.diasPromesa = diasPromesa;
	}

	public Set<UnidadNegocio> getUnidadesNegocio() {
		return unidadesNegocio;
	}

	public void setUnidadesNegocio(Set<UnidadNegocio> unidadesNegocio) {
		this.unidadesNegocio = unidadesNegocio;
	}

}
