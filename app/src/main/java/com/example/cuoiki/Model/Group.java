package com.example.cuoiki.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Group {
    private int idgroup;
    private String name_group, code_group;
    private User user;





    public Group() {
    }

    public Group(String name_group) {
        this.name_group = name_group;
    }

    public Group(int idgroup, String name_group, String code_group, User user) {
        this.idgroup = idgroup;
        this.name_group = name_group;
        this.code_group = code_group;
        this.user = user;
    }

    public int getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    public String getCode_group() {
        return code_group;
    }

    public void setCode_group(String code_group) {
        this.code_group = code_group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getIdgroup());
        jsonObject.put("code_group", getCode_group());
        return jsonObject;
    }
}
