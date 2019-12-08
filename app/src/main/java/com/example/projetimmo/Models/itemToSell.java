package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class itemToSell {
      @SerializedName("id")
      int id;
      @SerializedName("entreprise")
      String entreprise;
      @SerializedName("title")
      String title;
      @SerializedName("numberBedrooms")
      int numberBedrooms;
      @SerializedName("numberHouses")
      int numberHouses;
      @SerializedName("location")
      Location location;
      @SerializedName("category")
      Categorie categorie;
      @SerializedName("Notes")
      ArrayList<Notes> notes;
      @SerializedName("atouts")
      String atouts;
      @SerializedName("prix")
      int price;
      @SerializedName("description")
      String description;
      @SerializedName("dateDeLivraison")
      String dateDeLivraison;
      @SerializedName("urlVideo")
      String urlVideo;
      @SerializedName("rating")
      int rating;
      @SerializedName("availableHouses")
      int availableHouses;
      @SerializedName("userId")
      int userId;
      @SerializedName("urlPicture")
      String urlPicture;
      @SerializedName("surface")
      double surface;

      public itemToSell() {
      }

      public itemToSell(int id, String entreprise, String title, int numberBedrooms, int numberHouses, Location location, Categorie categorie, ArrayList<Notes> notes, String atouts, int price, String description, String dateDeLivraison, String urlVideo, int rating, int availableHouses, int userId, String urlPicture, double surface) {
            this.id = id;
            this.entreprise = entreprise;
            this.title = title;
            this.numberBedrooms = numberBedrooms;
            this.numberHouses = numberHouses;
            this.location = location;
            this.categorie = categorie;
            this.notes = notes;
            this.atouts = atouts;
            this.price = price;
            this.description = description;
            this.dateDeLivraison = dateDeLivraison;
            this.urlVideo = urlVideo;
            this.rating = rating;
            this.availableHouses = availableHouses;
            this.userId = userId;
            this.urlPicture = urlPicture;
            this.surface = surface;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getTitle() {
            return title;
      }

      public void setTitle(String title) {
            this.title = title;
      }

      public int getNumberBedrooms() {
            return numberBedrooms;
      }

      public void setNumberBedrooms(int numberBedrooms) {
            this.numberBedrooms = numberBedrooms;
      }

      public int getNumberHouses() {
            return numberHouses;
      }

      public void setNumberHouses(int numberHouses) {
            this.numberHouses = numberHouses;
      }

      public Location getLocation() {
            return location;
      }

      public void setLocation(Location location) {
            this.location = location;
      }

      public Categorie getCategorie() {
            return categorie;
      }

      public void setCategorie(Categorie categorie) {
            this.categorie = categorie;
      }

      public ArrayList<Notes> getNotes() {
            return notes;
      }

      public void setNotes(ArrayList<Notes> notes) {
            this.notes = notes;
      }

      public String getAtouts() {
            return atouts;
      }

      public int getAvailableHouses() {
//            return 5;
            return availableHouses;
      }

      public void setAvailableHouses(int availableHouses) {
            this.availableHouses = availableHouses;
      }

      public void setAtouts(String atouts) {
            this.atouts = atouts;
      }

      public int getPrice() {
            return price;
      }

      public void setPrice(int price) {
            this.price = price;
      }

      public String getDescription() {
            return description;
      }

      public void setDescription(String description) {
            this.description = description;
      }

      public String getDateDeLivraison() {
            return dateDeLivraison;
      }

      public void setDateDeLivraison(String dateDeLivraison) {
            this.dateDeLivraison = dateDeLivraison;
      }

      public String getUrlVideo() {
            return urlVideo;
      }

      public void setUrlVideo(String urlVideo) {
            this.urlVideo = urlVideo;
      }

      public int getRating() {
            return rating;
      }

      public void setRating(int rating) {
            this.rating = rating;
      }

      public int getUserId() {
            return userId;
      }

      public void setUserId(int userId) {
            this.userId = userId;
      }

      public String getUrlPicture() {
            return urlPicture;
      }

      public void setUrlPicture(String urlPicture) {
            this.urlPicture = urlPicture;
      }

      public double getSurface() {
            return surface;
      }

      public void setSurface(double surface) {
            this.surface = surface;
      }

      public String getEntreprise() {
            return entreprise;
      }

      public void setEntreprise(String entreprise) {
            this.entreprise = entreprise;
      }

      @Override
      public String toString() {
            return "itemToSell{" +
                    "id=" + id +
                    ", entreprise='" + entreprise + '\'' +
                    ", title='" + title + '\'' +
                    ", numberBedrooms=" + numberBedrooms +
                    ", numberHouses=" + numberHouses +
                    ", location=" + location +
                    ", categorie=" + categorie +
                    ", notes=" + notes +
                    ", atouts='" + atouts + '\'' +
                    ", price=" + price +
                    ", description='" + description + '\'' +
                    ", dateDeLivraison='" + dateDeLivraison + '\'' +
                    ", urlVideo='" + urlVideo + '\'' +
                    ", rating=" + rating +
                    ", availableHouses=" + availableHouses +
                    ", userId=" + userId +
                    ", urlPicture='" + urlPicture + '\'' +
                    ", surface=" + surface +
                    '}';
      }
}
