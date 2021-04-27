package com.example.projetm1.volumehoraire.share;

import com.example.model.Volumehoraire;

public class DeleteVolume implements Runnable {
    public volatile Boolean response;
    private String identifiant,matricule;

    public DeleteVolume( String identifiant,String matricule) {
        this.identifiant = identifiant;
        this.matricule = matricule;
    }

    @Override
    public void run() {
        deleteMat();
    }

    public void deleteMat(){
        String id = identifiant.trim();

        Volumehoraire volumehoraire = new Volumehoraire();
        volumehoraire.setNumat(id);
        volumehoraire.setMatricule(matricule);
        response = VolumehoraireShare.deleteVolume(volumehoraire);
    }

    public Boolean getResponse(){
        return response;
    }
}
