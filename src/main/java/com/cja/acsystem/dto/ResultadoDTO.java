package com.cja.acsystem.dto;

public class ResultadoDTO {

    private Long id;

    private String codigoResultado;
    private boolean activo;

    private boolean contacto;

    private boolean promesa;

    private boolean autoriza;

    private String descripcion;

    private Long accion_id;

    public ResultadoDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isContacto() {
        return contacto;
    }

    public void setContacto(boolean contacto) {
        this.contacto = contacto;
    }

    public boolean isPromesa() {
        return promesa;
    }

    public void setPromesa(boolean promesa) {
        this.promesa = promesa;
    }

    public boolean isAutoriza() {
        return autoriza;
    }

    public void setAutoriza(boolean autoriza) {
        this.autoriza = autoriza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getAccion_id() {
        return accion_id;
    }

    public void setAccion_id(Long accion_id) {
        this.accion_id = accion_id;
    }
}
