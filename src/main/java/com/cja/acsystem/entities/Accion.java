package com.cja.acsystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "accion")
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String codigoAcceso;

    @Column(length = 60)
    private String descripcion;

    private boolean activo;

    @ManyToOne
    @JoinColumn(name="unidad_negocio_id", nullable=false)
    private UnidadNegocio unidadNegocio;

    @OneToOne
    @JoinColumn(name="firma_id", nullable=false)
    private Firma firma;


    public Accion() {

    }

    public Accion(Long id, String codigoAcceso ,String descripccion, boolean activo,Firma firma ) {
        super();
        this.id = id;
        this.codigoAcceso=codigoAcceso;
        this.descripcion = descripccion;
        this.activo = activo;
        this.firma= firma;
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


    public UnidadNegocio getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public String getCodigo_acceso() {
        return codigoAcceso;
    }

    public void setCodigo_acceso(String codigo_acceso) {
        this.codigoAcceso = codigo_acceso;
    }
}
