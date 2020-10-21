package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="medico")
public class Medico {
    @Id
    @Column(name="id_medico")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idMedico;

    @Column(name="cedula_medico")
    private String cedulaMedico;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_veterinaria")
    private Veterinaria idVeterinaria;

    @Column(name="nombre_medico")
    private String nombreMedico;

    @Column(name="apellido_medico")
    private String apellidoMedico;

    @Column(name="direccion_residencia")
    private String direccionResidencia;

    @Column(name="telefono_medico")
    private int telefonoMedico;

    @Column(name="matricula_profesional")
    private String matriculaProfesional;

    @Column(name="correo_medico")
    private String correoMedico;

    @Column(name="contrasenia_medico")
    private String contraseniaMedico;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMedico")
    private List<Vacunacion> vacunados;

    public Medico() {
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getCedulaMedico() {
        return cedulaMedico;
    }

    public void setCedulaMedico(String cedulaMedico) {
        this.cedulaMedico = cedulaMedico;
    }

    public Veterinaria getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(Veterinaria idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public List<Vacunacion> getVacunados() {
        return vacunados;
    }

    public void setVacunados(List<Vacunacion> vacunados) {
        this.vacunados = vacunados;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public int getTelefonoMedico() {
        return telefonoMedico;
    }

    public void setTelefonoMedico(int telefonoMedico) {
        this.telefonoMedico = telefonoMedico;
    }

    public String getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public void setMatriculaProfesional(String matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }

    public String getCorreoMedico() {
        return correoMedico;
    }

    public void setCorreoMedico(String correoMedico) {
        this.correoMedico = correoMedico;
    }

    public String getContraseniaMedico() {
        return contraseniaMedico;
    }

    public void setContraseniaMedico(String contraseniaMedico) {
        this.contraseniaMedico = contraseniaMedico;
    }

    @Override
    public String toString() {
        return "Medicos{" +
                "idMedico=" + idMedico +
                ", cedulaMedico='" + cedulaMedico + '\'' +
                ", idVeterinaria=" + idVeterinaria +
                ", nombreMedico='" + nombreMedico + '\'' +
                ", apellidoMedico='" + apellidoMedico + '\'' +
                ", direccionResidencia='" + direccionResidencia + '\'' +
                ", telefonoMedico=" + telefonoMedico +
                ", matriculaProfesional='" + matriculaProfesional + '\'' +
                ", correoMedico='" + correoMedico + '\'' +
                ", contraseniaMedico='" + contraseniaMedico + '\'' +
                ", vacunados=" + vacunados +
                '}';
    }
}
