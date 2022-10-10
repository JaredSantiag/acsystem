package com.cja.acsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="unidades_negocio")
public class UnidadNegocio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20)
	private String nombre;
	
	private boolean activo;
	
	@Column(columnDefinition = "TEXT")
	private String script;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "firma_id", nullable = false)
	private Firma firma;

	@OneToMany(mappedBy="unidadNegocio")
	private Set<Accion> accion;

	public UnidadNegocio(){
		
	}

	public UnidadNegocio(Long id, String nombre, boolean activo, String script,Firma firma ,Set<Accion> accion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.firma = firma;
		this.accion = accion;
	}

	public Set<Accion> getAccion() {
		return accion;
	}

	public void setAccion(Set<Accion> accion) {
		this.accion = accion;
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

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}


}
