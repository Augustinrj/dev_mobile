package com.example.projetm1.matiere.shared;

import com.example.model.Matiere;

public class ModifyMat implements Runnable {
    private String numat,designation;
    private int nbheures;
    public volatile Boolean response;

    public ModifyMat(String numat, String designation, int nbheures) {
        this.numat = numat;
        this.designation = designation;
        this.nbheures = nbheures;
    }

    public ModifyMat() {
    }

    @Override
    public void run() {
        Matiere matiere = new Matiere();
        matiere.setNumat(numat);
        matiere.setDesignation(designation);
        matiere.setNbheures(nbheures);
        response = MatiereShare.EditMatiere(matiere);
    }

    public Boolean getResponse(){
        return response;
    }

}
