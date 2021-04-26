package com.example.projetm1.professeur.shared;
import com.example.model.Professeur;

public class ModifyProf implements Runnable {
    private   String identifiant;
    private   String nom;
    public volatile Boolean response;
    public ModifyProf(String identifiant,String nom) {
        this.identifiant = identifiant;
        this.nom = nom;
    }

    @Override
    public void run() {
        save_edit_prof();
    }

    public void save_edit_prof(){
        String id = identifiant.trim();
        String nom_prof = nom.trim();

        Professeur professeur = new Professeur();
        professeur.setNumMat(id);
        professeur.setNomProf(nom_prof);
        response = ProfesseurShare.EditProfesseur(professeur);
    }

    public Boolean getResponse(){
        return response;
    }
}
