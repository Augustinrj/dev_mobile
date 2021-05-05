package com.example.projetm1.volumehoraire.share;

import com.example.model.Volumehoraire;

public class AjouterVolume implements Runnable {
    private Volumehoraire volumehoraire;
    public volatile Boolean response;

    public AjouterVolume(Volumehoraire volumehoraire) {
        this.volumehoraire = volumehoraire;
    }

    @Override
    public void run() {
       response = VolumehoraireShare.AddVolume(volumehoraire);
    }
    public Boolean getResponse(){
        return response;
    }
}
