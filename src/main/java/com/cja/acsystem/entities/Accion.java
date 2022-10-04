package com.cja.acsystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "accion")
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String descripcion;

    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_id", nullable = false)
    private Firma firma;

    @JsonBackReference
    @OneToMany(mappedBy = "accion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resultado> resultados = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_negocio_id", nullable = false)
    private UnidadNegocio unidadNegocio;


    public Accion() {

    }

    public Accion(Long id, String descripccion, boolean activo, Set<Resultado> resultados) {
        super();
        this.id = id;
        this.descripcion = descripccion;
        this.activo = activo;
        this.resultados = resultados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(Set<Resultado> resultados) {
        this.resultados = resultados;
    }
}
