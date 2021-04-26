package com.example.projetm1.matiere.shared;
import com.example.model.Matiere;

public class DeleteMat implements Runnable {
    public volatile Boolean response;
    private String identifiant;

    public DeleteMat( String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public void run() {
        deleteMat();
    }

    public void deleteMat(){
        String id = identifiant.trim();

        Matiere matiere = new Matiere();
        matiere.setNumat(id);
        response = MatiereShare.deleteMat(matiere);
    }

    public Boolean getResponse(){
        return response;
    }
}

