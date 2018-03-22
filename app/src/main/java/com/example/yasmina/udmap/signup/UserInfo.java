package com.example.yasmina.udmap.signup;

/**
 * Created by cyrano on 21/03/18.
 */

public class UserInfo {

    private String email;
    private String password;
    private String prenom;
    private String nom;

    public UserInfo(String email){
        this.email = email;
    }

    public UserInfo(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
