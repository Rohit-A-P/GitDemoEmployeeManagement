package com.ideas2it.service;

import java.lang.ClassNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;  
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;  
import java.time.Period;  

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.genderenum.Gender;
import com.ideas2it.model.Skills;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;

/**
*
* EmployeeService class have buisness logics
*  
* @version 1.0 01-09-2022
*
* @author Rohit A P
* 
*/
public class EmployeeService {
 
    EmployeeDao employeeDao = new EmployeeDao();

    /**
     * 
     * pass Trainer Details insertTrainer method 
     * 
     * @param trainer
     * @return void 
     */   
    public String insertTrainer(Trainer trainer) {   
         
        String returnStatus      = "";   
        try {    
            returnStatus  = employeeDao.insertTrainer(trainer);
        } catch(SQLException sQLException) {
            returnStatus = "Database does not exist"; 
        } catch(ClassNotFoundException classNotFoundException) {
            returnStatus = "Check class path";
        } return returnStatus;
    }

    /**
     * 
     * pass Trainer Details insertTrainerDetails method
     * 
     * @param id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, dateOfJoining, skill
     * @return returnStatus 
     */   
    public String insertTrainee(Trainee trainee) {
            
        String returnStatus = "";
        try {          
            returnStatus   = employeeDao.insertTrainee(trainee);

        } catch(SQLException sQLException) {
             returnStatus = "Cannot connect to database";
        } catch(ClassNotFoundException classNotFoundException) {
            returnStatus = "Check class path";
        } return returnStatus;
    }

    /**
     *
     * viewtrainerDetail method print every profile in Trainer 
     *
     * @param
     * @return employeeDao.retrivetrainerDetail()
     */   
    public List<Trainer> viewtrainerDetails() {

        List<Trainer> trainers = new ArrayList<Trainer>();

        try {
            trainers = employeeDao.viewAllTrainer();
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return trainers;    
    }   

    /**
     * 
     * viewtraineeDetail method print every profile in Trainee 
     * 
     * @param
     * @return employeeDao.retrivetraineeDetail() 
     */   
    public List<Trainee> viewtraineeDetails() {

        List<Trainee> trainees = new ArrayList<Trainee>(); 

        try {  
            trainees = employeeDao.viewAllTrainee();
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return trainees;    
    }    
 
    /**
     *
     * deleteTrainer method gets Trainer ID as input and search Id Database, 
     * identifies that Id and delete Id details
     *
     * @param id 
     * @return void
     */   
    public void deleteTrainerById(String id) {

        try {
            employeeDao.deleteTrainerById(id);
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        }
    }
 
    /**
     *
     * deleteTrainee method gets Trainee ID as input and search Id in Database,
     * identifies that Id and delete Id details  
     *
     * @param id
     * @return void
     */   
    public void deleteTraineeById(String id) {

        try {
            employeeDao.deleteTraineeById(id);                
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        }
     }

    /**
     *
     * gets ID of the trainer and update details  
     *
     *
     * @param id 
     * @return void
     */
    public String updateEmployeeNameById(String id, String name) {

       String returnStatus = "";
 
       try {
            returnStatus = employeeDao.updateEmployeeNameById(id, name);
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return returnStatus;      
    }

    public String updateEmployeeDesignationById(String id, String designation) {

       String returnStatus = "";
 
       try {
           returnStatus = employeeDao.updateEmployeeDesignationById(id, designation);            
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return returnStatus;      
    } 

    public String updateEmployeePhoneNumberById(String id, Long phoneNumber) {

       String returnStatus = "";
 
       try {
           returnStatus = employeeDao.updateEmployeePhoneNumberById(id, phoneNumber);
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return returnStatus;      
    }   

    public String updateEmployeeEmailById(String id, String eMail) {

       String returnStatus = "";
 
       try {
           returnStatus = employeeDao.updateEmployeeEmailById(id, eMail); 
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return returnStatus;      
    }

    /**
     * 
     * Get Trainer detail by Id
     * 
     * @param Id
     * @return employeeDao.searchTrainerById(id) 
     */        
    public Trainer getTrainerById(String id) {

        Trainer trainer = null;
        try {
            trainer = employeeDao.getTrainerById(id);
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return trainer; 
    }

    /**
     * 
     * Get Trainer detail by Id
     * 
     * @param Id
     * @return employeeDao.searchTrainerById(id) 
     */        
    public Trainee getTraineeById(String id) {

        Trainee trainee = null;
        try {
            trainee = employeeDao.getTraineeById(id);
        } catch(SQLException sQLException) {
            System.out.println("Database does not exist"); 
        } catch(ClassNotFoundException classNotFoundException) {
            System.out.println("Check class path");
        } return trainee; 
    }
 
    /**
     * 
     * Creates id for employee table
     * 
     * @param option
     * @return uniqeNumber
     */
    public String genarateEmployeeId() { 
        int idSuffix = 22000;
        String uniqeId = "";
        int temp;
        int length = 0; 
            try {
                if(length == 0) { 
                    length = employeeDao.getEmployeeTableLength() + 1 ;
                } else {
                    length = length + 1; 
                }
            } catch(SQLException sQLException) {
                System.out.println("Database does not exist"); 
            } catch(ClassNotFoundException classNotFoundException) {
                System.out.println("Check class path");
            }          
        temp = idSuffix + length;
        uniqeId = String.valueOf(temp);
        uniqeId = "I" + uniqeId;   
        return uniqeId;
    }    

    /**
     * 
     * Creates id 
     * 
     * @param 
     * @return uniqeNumber
     */
    public int getSikillTableSize() { 
        
        int length = 0;
            try {
                length = employeeDao.getSkillTableLength();
            } catch(SQLException sQLException) {
                System.out.println("Database does not exist"); 
            } catch(ClassNotFoundException classNotFoundException) {
                System.out.println("Check class path");
            }
        return length;
    }    
   
    /**
     * 
     * used to select gender 
     * 
     * @param option
     * @return String 
     */      
    public String selectGender(int option) {
   
        String gender = ""; 

        switch (option) {
           case 1:
               return gender = Gender.MALE.toString(); 
           case 2:
               return gender = Gender.FEMALE.toString();
           case 3:
               return gender = Gender.OTHERS.toString();
           default:
               System.out.println("Invalid option");
               break;   
       } 
       return gender;  
    }  

    /**
     * 
     * Calculate age 
     * 
     * @param 
     * @return age
     */
     public int calculateAge(Date date) {
        
         String dateString = date.toString();
         LocalDate dob = LocalDate.parse(dateString);  
         LocalDate currentDate = LocalDate.now();
         int age = Period.between(dob, currentDate).getYears();  
         return age;
     }
        
    /**
     * 
     * Validate name 
     * 
     * @param id
     * @return status
     */
    public boolean isValidName(String id) {

        boolean status = Pattern.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", id);
        return status;
    }

    /**
     * 
     * Validate phone number 
     * 
     * @param phoneNumber
     * @return status
     */
    public boolean isValidPhoneNumber(Long phoneNumber) {

        boolean status = Pattern.matches("^[7-9][0-9]{9}", phoneNumber.toString());
        return status;
    }
    
    /**
     * 
     * Validate date 
     * 
     * @param date
     * @return status
     */
    public boolean isValidDate(String date) {

        boolean status = Pattern.matches("([1-2][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[1][0-9]|[2][0-9]|[3][0-1])", date); 
        return status;
    }

    /**
     * 
     * Validate ID 
     * 
     * @param id
     * @return status
     */
    public boolean isValidId(String id) {
        
        boolean status = Pattern.matches("[I][2][2][0-9][0-9][0-9]", id);
        return status;
    }

    /**
     * 
     * Validate email 
     * 
     * @param eMail
     * @return status
     */
    public boolean isValidEmail(String eMail) {

        boolean status = Pattern.matches("^(.+)@(\\S+)$", eMail); 
        return status;
    }
}