package com.cja.acsystem.dto;

public class UnidadNegocioDTO {
	
	private Long id;
	private String nombre;
	private boolean activo;
	private String script;

	private Long firma_id;
	
	public UnidadNegocioDTO() {
		
	}

	public Long getFirma_id() {
		return firma_id;
	}

	public void setFirma_id(Long firma_id) {
		this.firma_id = firma_id;
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
	
}
