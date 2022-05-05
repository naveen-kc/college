package com.example.college;

public class VisitList {

    private String id;
    private String name;
    private String lname;
    private String age;
    private String puspose;
    private String statusCode;
    private String whoAreYou;
    private String whomToVisit;
    private String whenToVisit;
    private String dept;
    private String contact;

    public VisitList(String id, String name, String lname, String age, String puspose, String statusCode, String whoAreYou, String whomToVisit, String whenToVisit, String dept, String contact) {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.age = age;
        this.puspose = puspose;
        this.statusCode = statusCode;
        this.whoAreYou = whoAreYou;
        this.whomToVisit = whomToVisit;
        this.whenToVisit = whenToVisit;
        this.dept = dept;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPuspose() {
        return puspose;
    }

    public void setPuspose(String puspose) {
        this.puspose = puspose;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getWhoAreYou() {
        return whoAreYou;
    }

    public void setWhoAreYou(String whoAreYou) {
        this.whoAreYou = whoAreYou;
    }

    public String getWhomToVisit() {
        return whomToVisit;
    }

    public void setWhomToVisit(String whomToVisit) {
        this.whomToVisit = whomToVisit;
    }

    public String getWhenToVisit() {
        return whenToVisit;
    }

    public void setWhenToVisit(String whenToVisit) {
        this.whenToVisit = whenToVisit;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
