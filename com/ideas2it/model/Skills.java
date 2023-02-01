package com.ideas2it.model;

import com.ideas2it.model.Trainee;

/**
*
* model of skills pojo
*  
* @version 1.0 09-09-2022
*
* @Author Rohit A P
*
*/
public class Skills {

    private String skillId;
    private String traineeId;
    private String skillName;
    private Float  skillExperience;
    private String skillVersion;
    private String skillCertification;

    public Skills() {
    }

    public Skills(String skillId, String traineeId, String skillName,
                  Float skillExperience, String skillVersion, String skillCertification) {

        this.skillId            = skillId;
        this.traineeId          = traineeId;
        this.skillName          = skillName;
        this.skillExperience    = skillExperience;
        this.skillVersion       = skillVersion;
        this.skillCertification = skillCertification;
    }

    public String getSkillId() {
        return skillId;   
    }

    public String getTraineeId() {
        return traineeId;   
    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillVersion() {
        return skillVersion;
    }  

    public Float getSkillExperience() {
        return skillExperience;
    }

    public String getSkillCertification() {
        return skillCertification;
    } 

    public void setSkillId(String skillId) {
        this.skillId = skillId; 
    }    

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId; 
    }  

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setSkillVersion(String skillVersion) {
        this.skillVersion = skillVersion;
    } 

    public void setSkillExperience(Float skillExperience) {
        this.skillExperience = skillExperience;
    }   

    public void setSkillCertification(String skillCertification) {
        this.skillCertification = skillCertification;
    }         

    public String toString() {
        return ("\nSKILL NAME            : " + this.skillName + "\nSKILL VERSION          : " 
               + this.skillVersion + "\nSKILL Experience      : " + this.skillExperience
               + "\nSKILL CERTIFICATION    : " + this.skillCertification);
    }
}