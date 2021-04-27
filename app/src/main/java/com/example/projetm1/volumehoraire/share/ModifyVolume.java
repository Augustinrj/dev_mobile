package com.example.projetm1.volumehoraire.share;


import com.example.model.Volumehoraire;

public class ModifyVolume implements Runnable {
        private String numat,matricule;
        private int tauxhoraire,id;
        public volatile Boolean response;

    public ModifyVolume(String numat, String matricule, int tauxhoraire,int id) {
        this.numat = numat;
        this.matricule = matricule;
        this.tauxhoraire = tauxhoraire;
        this.id = id;
    }

    public ModifyVolume() {
        }

        @Override
        public void run() {
            Volumehoraire volumehoraire = new Volumehoraire();
            volumehoraire.setId(id);
            volumehoraire.setNumat(numat);
            volumehoraire.setMatricule(matricule);
           volumehoraire.setTauxhoraire(tauxhoraire);
            response = VolumehoraireShare.EditVolume(volumehoraire);
        }

        public Boolean getResponse(){
            return response;
        }

    }



