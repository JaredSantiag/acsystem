package com.cja.acsystem.dto;

public class FirmaDTO {

	private Long id;
	private String nombre;
	private boolean activo;
	private int diasPromesa;
	
	public FirmaDTO() {
		super();
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
	
}
