package com.school.students.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;


import java.util.List;

@Entity
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;
    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String prenom;
    @NotBlank(message = "La matière ne peut pas être vide")
    private String matiere;

    @OneToMany(mappedBy = "enseignant")
    private List<Etudiant> etudiants;
    @OneToMany(mappedBy = "enseignant")
    private List<Classe> classes;

    // Constructeur sans paramètres
    public Enseignant() {
    }

    public Enseignant(Long id, String nom, String prenom, String matiere) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matiere = matiere;


    }
// Getters and setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }


    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }


}


