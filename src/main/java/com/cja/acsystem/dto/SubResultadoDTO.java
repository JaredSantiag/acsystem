package com.cja.acsystem.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SubResultadoDTO {

    private Long id;
    private String codigoSubResultado;
    private boolean activo;
    private String descripcion;
    private String texto;
    private long resultado_id;

    public SubResultadoDTO() {

    }

    public long getResultado_id() {
        return resultado_id;
    }

    public void setResultado_id(long resultado_id) {
        this.resultado_id = resultado_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoSubResultado() {
        return codigoSubResultado;
    }

    public void setCodigoSubResultado(String codigoSubResultado) {
        this.codigoSubResultado = codigoSubResultado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
