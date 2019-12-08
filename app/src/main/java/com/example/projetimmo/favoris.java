package com.example.projetimmo;

public class favoris {

    private  String nom;
    private  int thumbnail;

    public favoris(){}

    public favoris(String nomm, int thumbnaill){
        nom = nomm;
        thumbnail = thumbnaill ;
    }
   public String getNom(){
        return nom ;
   }
    public int getThumbnail(){
        return thumbnail ;
    }
    public void setNom(String nomm){
        nom = nomm;
    }
    public void setThumbnail(int thumbnaill){
        thumbnail = thumbnaill ;
    }



}
