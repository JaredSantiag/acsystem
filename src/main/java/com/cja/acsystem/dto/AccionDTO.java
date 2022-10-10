package com.cja.acsystem.dto;

public class AccionDTO {

    private Long id;

    private String codigoAccion;
    private String descripcion;
    private boolean activo;
    private Long firma_id;
    private Long unidad_negocio_id;

    public AccionDTO() {

    }

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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Long getFirma_id() {
        return firma_id;
    }

    public void setFirma_id(Long firma_id) {
        this.firma_id = firma_id;
    }

    public Long getUnidad_negocio_id() {
        return unidad_negocio_id;
    }

    public void setUnidad_negocio_id(Long unidad_negocio_id) {
        this.unidad_negocio_id = unidad_negocio_id;
    }

    public String getCodigo_accion() {
        return codigoAccion;
    }

    public void setCodigo_accion(String codigoAcceso) {
        this.codigoAccion = codigoAccion;
    }
}
