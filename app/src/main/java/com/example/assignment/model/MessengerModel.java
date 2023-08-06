package com.example.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessengerModel  {
    private  String messenger    ;
    private String Id_User ;
    @SerializedName("_id")
    private String id;

    public String getId_User() {
        return Id_User;
    }

    public void setId_User(String id_User) {
        Id_User = id_User;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessengerModel(String messenger, String id_User, String id) {
        this.messenger = messenger;
        Id_User = id_User;
        this.id = id;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public MessengerModel() {
    }

    public MessengerModel(String messenger) {
        this.messenger = messenger;
    }
}
