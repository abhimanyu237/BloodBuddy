package com.example.bloodbuddy.ui.request;

public class RequestData {


    private String patient_first_name,patient_last_name, attendee_first_name,attendee_last_name, state,city,local_address,
            attendee_mobile_number, patient_age,  units,select_blood_grp,donate_date,gender,whatsapp;


    public RequestData() {
    }

    public String getDonate_date() {
        return donate_date;
    }

    public void setDonate_date(String donate_date) {
        this.donate_date = donate_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public RequestData(String patient_first_name, String patient_last_name, String attendee_first_name, String attendee_last_name, String state, String city, String local_address, String attendee_mobile_number, String patient_age, String units, String select_blood_grp, String donate_date, String gender, String whatsapp) {
        this.patient_first_name = patient_first_name;
        this.patient_last_name = patient_last_name;
        this.attendee_first_name = attendee_first_name;
        this.attendee_last_name = attendee_last_name;
        this.state = state;
        this.city = city;
        this.local_address = local_address;
        this.attendee_mobile_number = attendee_mobile_number;
        this.patient_age = patient_age;
        this.units = units;
        this.select_blood_grp = select_blood_grp;
        this.donate_date = donate_date;
        this.gender = gender;
        this.whatsapp = whatsapp;
    }

    public String getPatient_first_name() {
        return patient_first_name;
    }

    public void setPatient_first_name(String patient_first_name) {
        this.patient_first_name = patient_first_name;
    }

    public String getPatient_last_name() {
        return patient_last_name;
    }

    public void setPatient_last_name(String patient_last_name) {
        this.patient_last_name = patient_last_name;
    }

    public String getAttendee_first_name() {
        return attendee_first_name;
    }

    public void setAttendee_first_name(String attendee_first_name) {
        this.attendee_first_name = attendee_first_name;
    }

    public String getAttendee_last_name() {
        return attendee_last_name;
    }

    public void setAttendee_last_name(String attendee_last_name) {
        this.attendee_last_name = attendee_last_name;
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

    public String getLocal_address() {
        return local_address;
    }

    public void setLocal_address(String local_address) {
        this.local_address = local_address;
    }

    public String getAttendee_mobile_number() {
        return attendee_mobile_number;
    }

    public void setAttendee_mobile_number(String attendee_mobile_number) {
        this.attendee_mobile_number = attendee_mobile_number;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSelect_blood_grp() {
        return select_blood_grp;
    }

    public void setSelect_blood_grp(String select_blood_grp) {
        this.select_blood_grp = select_blood_grp;
    }


}
