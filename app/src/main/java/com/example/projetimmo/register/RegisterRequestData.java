package com.example.projetimmo.register;

import com.example.projetimmo.Models.Pack;
import com.example.projetimmo.Models.Role;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterRequestData {

    @SerializedName("adresse")
    private String adresse;
    @SerializedName("adresseEntreprise")
    private String adresseCompany;
    @SerializedName("email")
    private String email;
    @SerializedName("emailEntreprise")
    private String emailCompany;
    @SerializedName("entreprise")
    private String entreprise;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("matricule")
    private String matricule;
    @SerializedName("password")
    private String password;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("type")
    private String type;
    @SerializedName("telEntreprise")
    private String phoneNumberCompany;
    @SerializedName("username")
    private String username;
    @SerializedName("pack")
    private String pack;
    @SerializedName("urlprofilepicture")
    private String urlprofilepicture;
    @SerializedName("role")
    private Role role;

    public RegisterRequestData() {
    }

    public RegisterRequestData(String adresse, String adresseCompany, String email, String emailCompany, String entreprise, String firstName, String lastName, String matricule, String password, String phoneNumber, String type, String phoneNumberCompany, String username, String pack, String urlprofilepicture, Role role) {
        this.adresse = adresse;
        this.adresseCompany = adresseCompany;
        this.email = email;
        this.emailCompany = emailCompany;
        this.entreprise = entreprise;
        this.firstName = firstName;
        this.lastName = lastName;
        this.matricule = matricule;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.phoneNumberCompany = phoneNumberCompany;
        this.username = username;
        this.pack = pack;
        this.urlprofilepicture = urlprofilepicture;
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresseCompany() {
        return adresseCompany;
    }

    public void setAdresseCompany(String adresseCompany) {
        this.adresseCompany = adresseCompany;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumberCompany() {
        return phoneNumberCompany;
    }

    public void setPhoneNumberCompany(String phoneNumberCompany) {
        this.phoneNumberCompany = phoneNumberCompany;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getUrlprofilepicture() {
        return urlprofilepicture;
    }

    public void setUrlprofilepicture(String urlprofilepicture) {
        this.urlprofilepicture = urlprofilepicture;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegisterRequestData{" +
                "adresse='" + adresse + '\'' +
                ", adresseCompany='" + adresseCompany + '\'' +
                ", email='" + email + '\'' +
                ", emailCompany='" + emailCompany + '\'' +
                ", entreprise='" + entreprise + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matricule='" + matricule + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type='" + type + '\'' +
                ", phoneNumberCompany='" + phoneNumberCompany + '\'' +
                ", username='" + username + '\'' +
                ", pack='" + pack + '\'' +
                ", urlprofilepicture='" + urlprofilepicture + '\'' +
                ", role=" + role +
                '}';
    }
}
