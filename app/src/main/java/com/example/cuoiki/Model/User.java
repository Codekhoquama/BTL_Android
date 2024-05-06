package com.example.cuoiki.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username, password, name, sdt, email, diachi;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(int id, String username, String password, String name, String sdt, String email, String diachi) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
    }

    public User(int id, String name, String sdt, String email, String diachi) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
    }

    public User(int id, String password) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("name", getName());
            jsonObject.put("username", getUsername());
            jsonObject.put("password", getPassword());
            jsonObject.put("sdt", getSdt());
            jsonObject.put("email", getEmail());
            jsonObject.put("diachi",getDiachi());
            return jsonObject;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }


}
