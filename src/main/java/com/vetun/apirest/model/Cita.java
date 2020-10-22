package com.vetun.apirest.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cita")
public class Cita {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_cita")
    private int idCita;

    @ManyToOne
    @JoinColumn(name="id_medico")
    private Medico idMedico;

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota idMascota;

    @Column(name="fecha_cita")
    private Date fechaCita;

    @Column(name="tipo_cita")
    private String tipoCita;

    @Column(name="modalidad_cita")
    private String modalidadCita;

    public Cita() {

    }

    public Cita(int idCita, Date fechaCita, String tipoCita, String modalidadCita) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.tipoCita = tipoCita;
        this.modalidadCita = modalidadCita;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public Mascota getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascota idMascota) {
        this.idMascota = idMascota;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getModalidadCita() {
        return modalidadCita;
    }

    public void setModalidadCita(String modalidadCita) {
        this.modalidadCita = modalidadCita;
    }
}
