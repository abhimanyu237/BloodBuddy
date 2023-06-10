package com.example.bloodbuddy;

public class UserData {

    private  String phone_number=null;
    private  String name=null;
    private  String email=null;
    private  String dob=null;
    private  String blood_grp=null;
//    private  String address = null;
    private String state=null;
    private String city=null;
    private String whatsapp=null;
    private String gender=null;

    public UserData() {
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserData(String phone_number, String name, String email, String dob, String blood_grp, String state, String city, String whatsapp, String gender) {
        this.phone_number = phone_number;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.blood_grp = blood_grp;
        this.state = state;
        this.city=city;
        this.whatsapp=whatsapp;
        this.gender=gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBlood_grp() {
        return blood_grp;
    }

    public void setBlood_grp(String blood_grp) {
        this.blood_grp = blood_grp;
    }

}
