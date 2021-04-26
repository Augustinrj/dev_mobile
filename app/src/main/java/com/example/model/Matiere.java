package com.example.model;

public class Matiere {
    private String numat,designation;
    private int nbheures;

    public Matiere() {
    }

    public Matiere(String numat, String designation, int nbheures) {
        this.numat = numat;
        this.designation = designation;
        this.nbheures = nbheures;
    }

    public String getNumat() {
        return numat;
    }

    public void setNumat(String numat) {
        this.numat = numat;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNbheures() {
        return nbheures;
    }

    public void setNbheures(int nbheures) {
        this.nbheures = nbheures;
    }
}
