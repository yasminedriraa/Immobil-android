package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class User {
      @SerializedName("id")
      int id;
      @SerializedName("password")
      String password;
      @SerializedName("username")
      String username;
      @SerializedName("firstName")
      String firstName;
      @SerializedName("lastName")
      String lastName;
      @SerializedName("adresse")
      String addresse;
      @SerializedName("email")
      String email;
      @SerializedName("entreprise")
      String company;
      @SerializedName("emailEntreprise")
      String compagnieEmail;
      @SerializedName("telEntreprise")
      String compagniePhone;
      @SerializedName("matricule")
      String matricule;
      @SerializedName("adresseEntreprise")
      String companyAddress;
      @SerializedName("pack")
      String pack;
      @SerializedName("phoneNumber")
      String phoneNumber;
      @SerializedName("type")
      String type;
      @SerializedName("enabled")
      Boolean enabled;
      @SerializedName("accountNonLocked")
      Boolean accountNonLocked;
      @SerializedName("credentialsNonExpired")
      Boolean credentialsNonExpired;
      @SerializedName("accountNonExpired")
      Boolean accountNonExpired;
      @SerializedName("urlprofilepicture")
      String urlProfilePicture;

      public User() {
      }

      public User(int id, String password, String username, String firstName, String lastName, String addresse, String email, String company, String compagnieEmail, String compagniePhone, String matricule, String companyAddress, String pack, String phoneNumber, String type, Boolean enabled, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean accountNonExpired, String urlProfilePicture) {
            this.id = id;
            this.password = password;
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.addresse = addresse;
            this.email = email;
            this.company = company;
            this.compagnieEmail = compagnieEmail;
            this.compagniePhone = compagniePhone;
            this.matricule = matricule;
            this.companyAddress = companyAddress;
            this.pack = pack;
            this.phoneNumber = phoneNumber;
            this.type = type;
            this.enabled = enabled;
            this.accountNonLocked = accountNonLocked;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonExpired = accountNonExpired;
            this.urlProfilePicture = urlProfilePicture;
      }

      public String getUrlProfilePicture() {
            return urlProfilePicture;
      }

      public void setUrlProfilePicture(String urlProfilePicture) {
            this.urlProfilePicture = urlProfilePicture;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getUsername() {
            return username;
      }

      public void setUsername(String username) {
            this.username = username;
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

      public String getAddresse() {
            return addresse;
      }

      public void setAddresse(String addresse) {
            this.addresse = addresse;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getCompany() {
            return company;
      }

      public void setCompany(String company) {
            this.company = company;
      }

      public String getCompagnieEmail() {
            return compagnieEmail;
      }

      public void setCompagnieEmail(String compagnieEmail) {
            this.compagnieEmail = compagnieEmail;
      }

      public String getCompagniePhone() {
            return compagniePhone;
      }

      public void setCompagniePhone(String compagniePhone) {
            this.compagniePhone = compagniePhone;
      }

      public String getMatricule() {
            return matricule;
      }

      public void setMatricule(String matricule) {
            this.matricule = matricule;
      }

      public String getCompanyAddress() {
            return companyAddress;
      }

      public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
      }

      public String getPack() {
            return pack;
      }

      public void setPack(String pack) {
            this.pack = pack;
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

      public Boolean getEnabled() {
            return enabled;
      }

      public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
      }

      public Boolean getAccountNonLocked() {
            return accountNonLocked;
      }

      public void setAccountNonLocked(Boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
      }

      public Boolean getCredentialsNonExpired() {
            return credentialsNonExpired;
      }

      public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
      }

      public Boolean getAccountNonExpired() {
            return accountNonExpired;
      }

      public void setAccountNonExpired(Boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
      }

      @Override
      public String toString() {
            return "User{" +
                    "id=" + id +
                    ", password='" + password + '\'' +
                    ", username='" + username + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", addresse='" + addresse + '\'' +
                    ", email='" + email + '\'' +
                    ", company='" + company + '\'' +
                    ", compagnieEmail='" + compagnieEmail + '\'' +
                    ", compagniePhone='" + compagniePhone + '\'' +
                    ", matricule='" + matricule + '\'' +
                    ", companyAddress='" + companyAddress + '\'' +
                    ", pack='" + pack + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", type='" + type + '\'' +
                    ", enabled=" + enabled +
                    ", accountNonLocked=" + accountNonLocked +
                    ", credentialsNonExpired=" + credentialsNonExpired +
                    ", accountNonExpired=" + accountNonExpired +
                    ", urlProfilePicture='" + urlProfilePicture + '\'' +
                    '}';
      }
}
