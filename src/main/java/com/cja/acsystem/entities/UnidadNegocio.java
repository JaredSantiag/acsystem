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

	@JsonBackReference
	@OneToMany(mappedBy = "unidadNegocio", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Accion> accion = new HashSet<>();

	public UnidadNegocio(){
		
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
