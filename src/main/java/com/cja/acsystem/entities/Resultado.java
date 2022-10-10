package com.cja.acsystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "resultados")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private String codigoResultado;

    private boolean activo;

    private boolean contacto;

    private boolean promesa;

    private boolean autoriza;

    @Column(length = 50)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="accion_id", nullable=false)
    private Accion accion;

    @JsonBackReference
    @OneToMany(mappedBy = "resultados", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubResultado> subResultados = new HashSet<>();


    public Resultado() {

    }

    public Resultado(Long id, String codigoResultado, boolean activo, boolean contacto, boolean promesa, boolean autoriza, String descripcion, Accion accion, Set<SubResultado> subResultados) {
        this.id = id;
        this.codigoResultado = codigoResultado;
        this.activo = activo;
        this.contacto = contacto;
        this.promesa = promesa;
        this.autoriza = autoriza;
        this.descripcion = descripcion;
        this.accion = accion;
        this.subResultados = subResultados;
    }

    public Set<SubResultado> getSubResultados() {
        return subResultados;
    }

    public void setSubResultados(Set<SubResultado> subResultados) {
        this.subResultados = subResultados;
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

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }
}
