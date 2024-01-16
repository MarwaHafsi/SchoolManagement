package com.school.students.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Le nom de la classe ne peut pas être vide")
    private String nom;
    @OneToMany(mappedBy = "classe")
    private List<Etudiant> etudiants;
    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    public Classe() {
    }

    public Classe( String nom) {
        this.nom = nom;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
    /*public Enseignant getEnseignant() {
        return enseignant;*/


    @Override
    public String toString() {
        return "Classe{id=" + id + ", nom='" + nom + "'}";
    }
}

   /* public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }*/

