package com.example.model;

public class Professeur {
    private String imageProf;
    private String numMat;
    private  String nomProf;

    public Professeur(){

    }

    public Professeur(String imageProf, String numMat, String nomProf) {
        this.imageProf = imageProf;
        this.numMat = numMat;
        this.nomProf = nomProf;
    }

    public String getImageProf() {
        return imageProf;
    }

    public void setImageProf(String imageProf) {
        this.imageProf = imageProf;
    }

    public String getNumMat() {
        return numMat;
    }

    public void setNumMat(String numMat) {
        this.numMat = numMat;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }
}
