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
    @JoinColumn(name="id_veterinaria")
    private Veterinaria idVeterinaria;

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota idMascota;

    @Column(name="fecha_cita")
    private Date fechaCita;

    @Column(name="tipo_cita")
    private String tipoCita;

    public Cita() {

    }

    public Cita(int idCita, Date fechaCita, String tipoCita) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.tipoCita = tipoCita;
    }
}
