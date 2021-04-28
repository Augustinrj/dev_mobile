package com.example.projetm1.montant;

import com.example.projetm1.BulletinPaieview;

public class GetBulletindepaie implements Runnable {
    public volatile String response;
    private String identifiant;

    public GetBulletindepaie(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public void run() {
        bulletinpaieget();
    }

    public void bulletinpaieget(){
        String id = identifiant.trim();
        response = BulletinpaieShare.getAll(id);
    }

    public String getResponse(){
        return response;
    }
}
