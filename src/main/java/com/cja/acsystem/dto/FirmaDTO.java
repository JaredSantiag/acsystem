package com.cja.acsystem.dto;

public class FirmaDTO {
	
	private String nombre;
	private boolean activo;
	private int diasPromesa;
	
	public FirmaDTO() {
		super();
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
