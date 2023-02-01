package com.ideas2it.model;

import java.sql.Date;
import java.util.ArrayList;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Skills;

/**
*
* Trainee class is used to get Trainee details
*  
* @version 1.0 01-09-2022
*
* @Author Rohit A P
*
*/
public class Trainee extends Employee {
    
     private String            traineeId; 
     private Date              dateOfJoining; 
     private ArrayList<Skills> skills = new ArrayList<Skills>();

     public Trainee() {
     }
  
     public Trainee(String id, String name, String bloodGroup, String designation, Date dateOfBirth, String gender,
                    Long phoneNumber, String eMail, String traineeId, Date dateOfJoining, ArrayList<Skills> skills) {
        super(id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail);

        this.traineeId     = traineeId; 
        this.dateOfJoining = dateOfJoining;
        this.skills        = skills;
     }
 
    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
    } 

    public Date getDateOfJoining() {
        return dateOfJoining;
    } 
   
    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public ArrayList<Skills> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skills> skills) {
        this.skills = skills;
    }

    public String toString() {
        return (super.toString() 
                + "\nDate Of Joining      : " + this.dateOfJoining +
                this.skills);
    }
}    