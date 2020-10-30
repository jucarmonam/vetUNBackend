package com.vetun.apirest.model;

import javax.persistence.*;

@Entity
@Table(name="costo")
public class Costo {

    @Id
    @Column(name="id_costo")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCosto;

    @ManyToOne
    @JoinColumn(name = "id_atencion" )
    private TipoAtencion idAtencion;

    @ManyToOne
    @JoinColumn(name = "id_medico" )
    private Medico idMedico;

    @Column(name = "costo")
    private int costo;

    public Costo() {
    }

    public int getIdCosto() {
        return idCosto;
    }

    public void setIdCosto(int idCosto) {
        this.idCosto = idCosto;
    }

    public TipoAtencion getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(TipoAtencion idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}

