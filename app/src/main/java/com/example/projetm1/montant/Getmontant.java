package com.example.projetm1.montant;

public class Getmontant implements Runnable{
    public volatile String response;
    private String identifiant;

    public Getmontant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public void run() {
        montantget();
    }

    public void montantget(){
        String id = identifiant.trim();
        response = BulletinpaieShare.getMontant(id);
    }

    public String getResponse(){
        return response;
    }
}
