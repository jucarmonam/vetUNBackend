package com.vetun.apirest.model;

import javax.persistence.*;

@Entity
@Table(name="costo_atencion")
public class CostoAtencion {

    @Id
    @Column(name="id_costo")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCosto;

    @ManyToOne
    @JoinColumn(name = "id_medico" )
    private Medico idMedico;

    @Column(name = "descripcion_costo")
    private String descripcionCosto;

    @Column(name = "costo")
    private int costo;

    public CostoAtencion() {
    }

    public CostoAtencion(Medico idMedico, String descripcionCosto, int costo) {
        this.idMedico = idMedico;
        this.descripcionCosto = descripcionCosto;
        this.costo = costo;
    }

    public int getIdCosto() {
        return idCosto;
    }

    public void setIdCosto(int idCosto) {
        this.idCosto = idCosto;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public String getDescripcionCosto() {
        return descripcionCosto;
    }

    public void setDescripcionCosto(String descripcionCosto) {
        this.descripcionCosto = descripcionCosto;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}

