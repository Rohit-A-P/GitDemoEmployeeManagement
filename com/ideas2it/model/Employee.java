package com.ideas2it.model;

import java.sql.Date;

/**
*
* Employee class is used to get Empolyee details
*  
* @version 1.0 01-09-2022
*
* @author Rohit A P
*
*/
public class Employee {
    
    private String id;
    private String name;
    private String bloodGroup;
    private Date   dateOfBirth; 
    private String designation;
    private String gender;
    private Long   phoneNumber;
    private String eMail;

    public Employee() {
    }
   
    public Employee(String id, String name, String bloodGroup, String designation, Date dateOfBirth,
                    String gender, Long phoneNumber, String eMail) {
        this.id           = id;
        this.name         = name; 
        this.bloodGroup   = bloodGroup;
        this.dateOfBirth  = dateOfBirth;
        this.designation  = designation;
        this.gender       = gender;
        this.phoneNumber  = phoneNumber;
        this.eMail        = eMail;
    }  

    public String getId() {
        return id;
    }
   
    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getDesignation() {
        return designation;
    }
    public String getEMail() {
        return eMail;
    }
 
    public String getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
 
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(String id) {
       this.id = id;
    }
    
    public void setName(String name) {
        this.name =name;
    }
 
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
   
    public String toString() {
        return "\nID                   : " + this.id            + "\nName                 : " + this.name 
             + "\nBlood Group          : " + this.bloodGroup    + "\nEMail                : " + this.eMail
             + "\nDate of Birth(Y-M-D) : " + this.dateOfBirth   + "\nGender               : " + this.gender
             + "\nPhone Number         : "  + this.phoneNumber  + "\nDesignation          : " + this.designation;
    }
}    