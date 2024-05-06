package com.example.cuoiki.Model;



public class CongViec {
    private  int  IdCV;
    private  String TenCV;
    private  String ND;

    private User user;

    public CongViec(int idCV, String tenCV, String ND, User user) {
        IdCV = idCV;
        TenCV = tenCV;
        this.ND = ND;
        this.user = user;
    }

    public CongViec(int idCV, String tenCV, String ND) {
        IdCV = idCV;
        TenCV = tenCV;
        this.ND = ND;
    }

    public CongViec(int idCV, String tenCV) {
        IdCV = idCV;
        TenCV = tenCV;
    }


    public CongViec(String tenCV) {
        TenCV = tenCV;
    }

    public int getIdCV() {
        return IdCV;
    }

    public void setIdCV(int idCV) {
        IdCV = idCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }

    public String getND() {
        return ND;
    }

    public void setND(String ND) {
        this.ND = ND;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
