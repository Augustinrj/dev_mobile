package com.example.projetm1.professeur.shared;

import com.example.model.Professeur;

public class DeleteProf implements Runnable {
    public volatile Boolean response;
    private String identifiant;

    public DeleteProf( String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public void run() {
        deleteProf();
    }

    public void deleteProf(){
        String id = identifiant.trim();

        Professeur professeur = new Professeur();
        professeur.setNumMat(id);
        response = ProfesseurShare.deleteProf(professeur);
    }

    public Boolean getResponse(){
        return response;
    }
}
