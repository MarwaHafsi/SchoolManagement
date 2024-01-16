package com.school.students.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Le nom ne peut pas être vide")

    private String nom;
    @NotBlank(message = "Le prénom ne peut pas être vide")

    private String prenom;
    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être une adresse email valide")
    private String email;
    @NotBlank(message = "L'adresse ne peut pas être vide")
    private String adresse;
    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    private String numTel;
    /*@ManyToOne
    @JoinColumn(name = "status_id")
    private StudentStatus status;*/
    @NotNull(message = "La classe ne peut pas être vide")

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classe classe;
    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;
    @NotNull(message = "Le status ne peut pas être vide")
    private String status;
    private String proprieteNouvelEtudiant;
    private String proprieteEtudiantRedoublant;
    private String imageFileName;

    public Etudiant(String nom, String prenom, String email, String adresse, String numTel, Classe classe,String status,String imageFileName) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.numTel = numTel;
        this.classe=classe;
        this.status=status;
        this.imageFileName=imageFileName;

    }

    public Etudiant() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }



    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getProprieteNouvelEtudiant() {
        return proprieteNouvelEtudiant;
    }

    public void setProprieteNouvelEtudiant(String proprieteNouvelEtudiant) {
        this.proprieteNouvelEtudiant = proprieteNouvelEtudiant;
    }

    public String getProprieteEtudiantRedoublant() {
        return proprieteEtudiantRedoublant;
    }

    public void setProprieteEtudiantRedoublant(String proprieteEtudiantRedoublant) {
        this.proprieteEtudiantRedoublant = proprieteEtudiantRedoublant;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numTel=" + numTel +
                ", classe=" + classe +
                ", enseignant=" + enseignant +
                '}';
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
}
