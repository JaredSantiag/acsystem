package com.cja.acsystem.dto;

public class ResultadoDTO {

    private Long id;
    private String descripcion;
    private String accion_id;
    private boolean activo;
    private String texto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAccion_id() {
        return accion_id;
    }

    public void setAccion_id(String accion_id) {
        this.accion_id = accion_id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
