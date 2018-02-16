package com.interlem.rzuccotti.zukrud.database.model;

/**
 * Created by rzuccotti on 09/02/2018.
 */

public class ObjectStudent {

    int id;
    String firstName;
    String email;

    public ObjectStudent(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}