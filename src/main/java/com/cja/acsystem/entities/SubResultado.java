package com.cja.acsystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "sub_resultados")
public class SubResultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private String codigoSubResultado;

    private boolean activo;

    @Column(length = 50)
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String texto;

    @ManyToOne
    @JoinColumn(name="resultado_id", nullable=false)
    private Resultado resultados;

    public SubResultado() {

    }

    public SubResultado(Long id, String codigoSubResultado, boolean activo, String descripcion, String texto, Resultado resultados) {
        this.id = id;
        this.codigoSubResultado = codigoSubResultado;
        this.activo = activo;
        this.descripcion = descripcion;
        this.texto = texto;
        this.resultados = resultados;
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

    public Resultado getResultado() {
        return resultados;
    }

    public void setResultado(Resultado resultado) {
        this.resultados = resultado;
    }
}
