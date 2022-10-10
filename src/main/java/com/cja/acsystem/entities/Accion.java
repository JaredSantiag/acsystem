package com.cja.acsystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "acciones")
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private String codigoAccion;

    @Column(length = 50)
    private String descripcion;

    private boolean activo;

    @ManyToOne
    @JoinColumn(name="unidad_negocio_id", nullable=false)
    private UnidadNegocio unidadNegocio;

    @OneToOne
    @JoinColumn(name="firma_id", nullable=false)
    private Firma firma;

    @JsonBackReference
    @OneToMany(mappedBy = "accion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resultado> resultados = new HashSet<>();


    public Accion() {

    }

    public Accion(Long id, String codigoAccion, String descripcion, boolean activo, UnidadNegocio unidadNegocio, Firma firma, Set<Resultado> resultados) {
        this.id = id;
        this.codigoAccion = codigoAccion;
        this.descripcion = descripcion;
        this.activo = activo;
        this.unidadNegocio = unidadNegocio;
        this.firma = firma;
        this.resultados = resultados;
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

    public String getCodigo_accion() {
        return codigoAccion;
    }

    public void setCodigo_acceso(String codigo_accion) {
        this.codigoAccion = codigo_accion;
    }
}
