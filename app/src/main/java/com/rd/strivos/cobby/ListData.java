package com.rd.strivos.cobby;

/*
 * ListData class will hold data for displaying in ListView
 * */
public class ListData {

    public int id = -1;
    public String projectID;
    public String personName;
    public String personDesignation;
    public String personMobile;
    public String personEmail;
    public String projectType;
    public String status;

    public int getId(){
     return id;
    }

    public void setId(int Id){
        id = Id;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String ProjectID) {
        projectID = ProjectID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String PersonName) {
        this.personName = PersonName;
    }

    public String getPersonDesignation() {
        return personDesignation;
    }

    public void setPersonDesignation(String PersonDesignation) {
        this.personDesignation = PersonDesignation;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String PersonMobile) {
        this.personMobile = PersonMobile;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String PersonEmail) {
        this.personEmail = PersonEmail;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String ProjectType) {
        this.projectType = ProjectType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

}
