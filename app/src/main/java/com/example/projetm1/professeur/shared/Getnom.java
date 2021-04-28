package com.example.projetm1.professeur.shared;

import com.example.model.Professeur;

public class Getnom implements Runnable {
    public volatile String response;
    private String identifiant;

    public Getnom( String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public void run() {
        getnomProf();
    }

    public void getnomProf(){
        String id = identifiant.trim();

        Professeur professeur = new Professeur();
        professeur.setNumMat(id);
        response = ProfesseurShare.getnomProf(professeur);
    }

    public String getResponse(){
        return response;
    }
}
