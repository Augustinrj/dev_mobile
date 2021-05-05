package com.example.projetm1.matiere.shared;

import com.example.model.Matiere;

public class Ajoutmatiere implements Runnable {
    public volatile Boolean response;
    private String identifiant;
    private String designation;
    private int nbheures;

    public Ajoutmatiere(String identifiant, String designation, int nbheures) {
        this.identifiant = identifiant;
        this.designation = designation;
        this.nbheures = nbheures;
    }

    @Override
    public void run() {
        ajoutMat();
    }

    public void ajoutMat(){
        String id = identifiant.trim();
        String design = designation;
        int nbh = nbheures;
        Matiere matiere = new Matiere();
        matiere.setNumat(id);
        matiere.setDesignation(designation);
        matiere.setNbheures(nbh);
        response = MatiereShare.AddMatiere(matiere);
    }

    public Boolean getResponse(){
        return response;
    }
}
