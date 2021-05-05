package com.example.projetm1.matiere.shared;

import com.example.model.Matiere;

public class GetdesignMat implements Runnable {
        public volatile String response;
        private String identifiant;

        public GetdesignMat( String identifiant) {
            this.identifiant = identifiant;
        }

        @Override
        public void run() {
            getdesignMat();
        }

        public void getdesignMat(){
            String id = identifiant.trim();
            Matiere matiere = new Matiere();
            matiere.setNumat(id);

            response = MatiereShare.getdesignMat(matiere);
        }

        public String getResponse(){
            return response;
        }


}
