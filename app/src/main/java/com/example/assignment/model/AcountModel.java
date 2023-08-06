package com.example.assignment.model;

import com.google.gson.annotations.SerializedName;

public class AcountModel {
    private  String username ;
    private  String password ;
    private int id_user ;
    @SerializedName("_id")
    private String id;

    @SerializedName("token")
    private String token;
    private String name_user ;

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AcountModel(String username, String password, int id_user, String id, String token, String name_user) {
        this.username = username;
        this.password = password;
        this.id_user = id_user;
        this.id = id;
        this.token = token;
        this.name_user = name_user;
    }

    public AcountModel(String username, String password, int id_user, String id, String name_user) {
        this.username = username;
        this.password = password;
        this.id_user = id_user;
        this.id = id;
        this.name_user = name_user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public AcountModel() {
    }

    public AcountModel(String username, String password, int id_user, String name_user) {
        this.username = username;
        this.password = password;
        this.id_user = id_user;
        this.name_user = name_user;
    }

    public AcountModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
