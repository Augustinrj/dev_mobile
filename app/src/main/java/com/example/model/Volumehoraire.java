package com.example.model;

public class Volumehoraire {
    private String matricule,numat;
    private  int tauxhoraire;
    private int id;
    public Volumehoraire() {
    }

    public Volumehoraire(String matricule, String numat, int tauxhoraire) {
        this.matricule = matricule;
        this.numat = numat;
        this.tauxhoraire = tauxhoraire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNumat() {
        return numat;
    }

    public void setNumat(String numat) {
        this.numat = numat;
    }

    public int getTauxhoraire() {
        return tauxhoraire;
    }

    public void setTauxhoraire(int tauxhoraire) {
        this.tauxhoraire = tauxhoraire;
    }
}
