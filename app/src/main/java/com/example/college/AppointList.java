package com.example.college;

public class AppointList {
    private String id;
    private String fname;
    private String lname;
    private String age;
    private String purpose;
    private String status;
    private String who;
    private String whom;
    private String date;
    private String dept;
    private String contact;

    public AppointList(String id, String fname, String lname, String age, String purpose, String status, String who, String whom, String date, String dept, String contact) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.purpose = purpose;
        this.status = status;
        this.who = who;
        this.whom = whom;
        this.date = date;
        this.dept = dept;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAge() {
        return age;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getStatus() {
        return status;
    }

    public String getWho() {
        return who;
    }

    public String getWhom() {
        return whom;
    }

    public String getDate() {
        return date;
    }

    public String getDept() {
        return dept;
    }

    public String getContact() {
        return contact;
    }
}