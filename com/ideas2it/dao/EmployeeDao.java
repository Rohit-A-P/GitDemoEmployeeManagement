package com.ideas2it.dao;

import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.ideas2it.database.DataBaseConnection;
import com.ideas2it.model.Skills;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;

/**
* EmployeeDao manage insert, retrive, delete and update operation 
*
* @version 1.0 01-09-2022
*
* @author Rohit A P 
*/
public class EmployeeDao {
 
    Connection connection; 

    /**
     * 
     * getConnection() method gets connction with MYSQL
     * 
     * @param 
     * @return connection 
     */      
    public Connection getConnection()throws SQLException, ClassNotFoundException {

        Connection connection = DataBaseConnection.getInstance().getConnection();
        return connection;         
    }   
        
    /**
     * 
     * add trainer details to MYSQL database
     * 
     * @param id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, trainingSince
     * @return void 
     */       
    public String insertTrainer(Trainer trainer) throws SQLException, ClassNotFoundException {

        String queryEmployee = "INSERT INTO EMPLOYEE(id, name, blood_group, designation, date_of_birth, gender, phone_number, email) VALUES (?,?,?,?,?,?,?,?);";
        String queryTrainer  = "INSERT INTO TRAINER(trainer_id, id, training_since) VALUES(?,?,?);"; 
        String returnStatus  = "";

        try {             
            connection = getConnection();
            PreparedStatement preparedStatementEmployee = connection.prepareStatement(queryEmployee);
            preparedStatementEmployee.setString(1, trainer.getId());
            preparedStatementEmployee.setString(2, trainer.getName());
            preparedStatementEmployee.setString(3, trainer.getBloodGroup());
            preparedStatementEmployee.setString(4, trainer.getDesignation()); 
            preparedStatementEmployee.setDate(5, trainer.getDateOfBirth());
            preparedStatementEmployee.setString(6, trainer.getGender());
            preparedStatementEmployee.setLong(7, trainer.getPhoneNumber());
            preparedStatementEmployee.setString(8, trainer.getEMail());
            preparedStatementEmployee.executeUpdate();

            PreparedStatement preparedStatementTrainer = connection.prepareStatement(queryTrainer);
            preparedStatementTrainer.setString(1, trainer.getTrainerId());
            preparedStatementTrainer.setString(2, trainer.getId());          
            preparedStatementTrainer.setInt(3, trainer.getTrainingSince());
            preparedStatementTrainer.executeUpdate();

            returnStatus = "Created Employee Profile";
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return returnStatus; 
    }

    /**
     * 
     * add trainee details to MYSQL database
     * 
     * @param Trainee trainee, Skills skills
     * @return void 
     */       
    public String insertTrainee(Trainee trainee) throws SQLException, ClassNotFoundException {

        String queryEmployee = "INSERT INTO EMPLOYEE(id, name, blood_group, designation, date_of_birth, gender, phone_number,"
                             + "email) VALUES (?,?,?,?,?,?,?,?);";
        String queryTrainee  = "INSERT INTO TRAINEE(trainee_id, id, date_of_joining) VALUES(?, ?, ?);";
        String querySkills   = "INSERT INTO Skills(skill_id, trainee_id, skill_Name, skill_experience, skill_version, skill_certification) VALUES(?, ?, ?, ?, ?, ?);";
        String returnStatus  = "";

        try {  
            System.out.println(trainee);
            connection = getConnection();
            PreparedStatement preparedStatementEmployee = connection.prepareStatement(queryEmployee);
            preparedStatementEmployee.setString(1, trainee.getId());
            preparedStatementEmployee.setString(2, trainee.getName());
            preparedStatementEmployee.setString(3, trainee.getBloodGroup());
            preparedStatementEmployee.setString(4, trainee.getDesignation()); 
            preparedStatementEmployee.setDate(5, trainee.getDateOfBirth());
            preparedStatementEmployee.setString(6, trainee.getGender());
            preparedStatementEmployee.setLong(7, trainee.getPhoneNumber());
            preparedStatementEmployee.setString(8, trainee.getEMail());
            preparedStatementEmployee.executeUpdate();

            PreparedStatement preparedStatementTrainee = connection.prepareStatement(queryTrainee);
            preparedStatementTrainee.setString(1, trainee.getTraineeId());
            preparedStatementTrainee.setString(2, trainee.getId());
            preparedStatementTrainee.setDate(3, trainee.getDateOfJoining()); 
            preparedStatementTrainee.executeUpdate();
           
            ArrayList<Skills> skills = trainee.getSkills();

            for(Skills skill : skills) {
                PreparedStatement preparedStatementSkills = connection.prepareStatement(querySkills);
                preparedStatementSkills.setString(1, skill.getSkillId());
                preparedStatementSkills.setString(2, skill.getTraineeId());
                preparedStatementSkills.setString(3, skill.getSkillName()); 
                preparedStatementSkills.setFloat(4, skill.getSkillExperience());
                preparedStatementSkills.setString(5, skill.getSkillVersion());
                preparedStatementSkills.setString(6, skill.getSkillCertification());
                preparedStatementSkills.executeUpdate();
            }
            returnStatus = "Created Employee Profile";    
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return returnStatus;
    }
     
    /** 
     * 
     * show every trainer detail
     * 
     * @param 
     * @return trainers 
     */       
    public ArrayList<Trainer> viewAllTrainer() throws SQLException, ClassNotFoundException {

        ArrayList<Trainer> trainers = new ArrayList<Trainer>(); 

        Trainer trainer = new Trainer();
        
	String employeeQuery = "SELECT * FROM EMPLOYEE INNER JOIN TRAINER ON EMPLOYEE.ID = TRAINER.ID ;";

        try{    
           connection = getConnection(); 
	   PreparedStatement preparedStatement = connection.prepareStatement(employeeQuery);
	   ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) { 
               trainer.setId(resultSet.getString("id"));
               trainer.setName(resultSet.getString("name"));
               trainer.setBloodGroup(resultSet.getString("blood_group"));
               trainer.setDesignation(resultSet.getString("designation")); 
               trainer.setDateOfBirth(resultSet.getDate("date_of_birth")); 
               trainer.setGender(resultSet.getString("gender")); 
               trainer.setPhoneNumber(resultSet.getLong("phone_number"));
               trainer.setEMail(resultSet.getString("email"));
               trainer.setTrainerId(resultSet.getString("trainer_id")); 
               trainer.setTrainingSince(resultSet.getInt("training_since"));
               trainers.add(trainer);                
           } 
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return trainers;            
    }    
     
    /**
     * 
     * show every trainee detail
     * 
     * @param 
     * @return trainees 
     */        
    public ArrayList<Trainee> viewAllTrainee() throws SQLException, ClassNotFoundException {

        ArrayList<Trainee> trainees = new ArrayList<Trainee>();  

        Trainee trainee = new Trainee();

        String query = "SELECT * FROM EMPLOYEE INNER JOIN TRAINEE ON EMPLOYEE.id = TRAINEE.id ;";

        try {          
            connection = getConnection();
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                trainee.setId(resultSet.getString("id"));
                trainee.setName(resultSet.getString("name"));
                trainee.setBloodGroup(resultSet.getString("blood_group"));
                trainee.setDesignation(resultSet.getString("designation")); 
                trainee.setDateOfBirth(resultSet.getDate("date_birth_birth")); 
                trainee.setGender(resultSet.getString("gender")); 
                trainee.setPhoneNumber(resultSet.getLong("phone_number"));
                trainee.setEMail(resultSet.getString("email"));
                trainee.setTraineeId(resultSet.getString("trainee_id")); 
                trainee.setDateOfJoining(resultSet.getDate("date_of_joining"));
                trainees.add(trainee);   
            } 
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return trainees;
    }

    /**
     * 
     * delete trainer detail
     * 
     * @param id
     * @return void 
     */        
    public void deleteTrainerById(String id) throws SQLException, ClassNotFoundException {

        String idQuery = "SELECT trainer_id FROM EMPLOYEE INNER JOIN TRAINER ON EMPLOYEE.ID = TRAINER.ID where EMPLOYEE.ID = ?;";
        String trainerQuery  = "DELETE FROM TRAINER WHERE trainer_id = ?;";
        String employeeQuery = "DELETE FROM EMPLOYEE WHERE id = ?;";
        String trainerId     = "";

        try {      
            connection = getConnection();
	    PreparedStatement preparedStatementId = connection.prepareStatement(idQuery);
            preparedStatementId.setString(1, id);
            ResultSet resultSet = preparedStatementId.executeQuery(); 
            resultSet.next();  
            trainerId = resultSet.getString("trainer_id");

            System.out.println(trainerId);

	    PreparedStatement preparedStatementTrainer = connection.prepareStatement(trainerQuery);
            preparedStatementTrainer.setString(1, trainerId);    
            preparedStatementTrainer.executeUpdate();

	    PreparedStatement preparedStatementEmployee = connection.prepareStatement(employeeQuery);
            preparedStatementEmployee.setString(1, id);    
            preparedStatementEmployee.executeUpdate();

        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        }
    }

    /**
     * 
     * delete trainee detail
     * 
     * @param id
     * @return void 
     */        
    public void deleteTraineeById(String id) throws SQLException, ClassNotFoundException {

        String idQuery = "SELECT trainee_id FROM EMPLOYEE INNER JOIN TRAINEE ON EMPLOYEE.id = TRAINEE.id WHERE Trainee.id = ?;";

        String skillQuery    = "DELETE FROM SKILLS WHERE trainee_id = ?;";
        String traineeQuery  = "DELETE FROM TRAINEE WHERE trainee_id = ?;";
        String employeeQuery = "DELETE FROM EMPLOYEE WHERE id = ?;";
        String traineeId     = "";
        String skillId       = "";

        try {      
            connection = getConnection();
	    PreparedStatement preparedStatementId = connection.prepareStatement(idQuery);
            preparedStatementId.setString(1, id);
            ResultSet resultSet = preparedStatementId.executeQuery();
            resultSet.next();  
            traineeId = resultSet.getString("trainee_id");
           
            System.out.println(traineeId);

	    PreparedStatement preparedStatementSkills = connection.prepareStatement(skillQuery);
            preparedStatementSkills.setString(1, traineeId);    
            preparedStatementSkills.executeUpdate();

	    PreparedStatement preparedStatementTrainee = connection.prepareStatement(traineeQuery);
            preparedStatementTrainee.setString(1, traineeId);    
            preparedStatementTrainee.executeUpdate();

	    PreparedStatement preparedStatementEmployee = connection.prepareStatement(employeeQuery);
            preparedStatementEmployee.setString(1, id);    
            preparedStatementEmployee.executeUpdate();

        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        }    
    }
 
    /**
     * 
     * update Employee detail
     * 
     * @param id, name
     * @return returnStatus 
     */        
    public String updateEmployeeNameById(String id, String name) throws SQLException, ClassNotFoundException {

        String query = "UPDATE TRAINEE SET name = ?, WHERE id = ?;";
        String returnStatus = "";
        try {            
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();                    
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return returnStatus;               
    }  

    /**
     * 
     * update Employee detail
     * 
     * @param id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, dateOfJoining, skill
     * @return void 
     */        
    public String updateEmployeeDesignationById(String id, String designation) throws SQLException, ClassNotFoundException {

        String query = "UPDATE TRAINEE SET designation = ?, WHERE id = ?;";
        String returnStatus = "";
        try {            
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, designation);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();                    
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return returnStatus;               
    }   

    /**
     * 
     * update Employee detail
     * 
     * @param id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, dateOfJoining, skill
     * @return void 
     */        
    public String updateEmployeePhoneNumberById(String id, Long phoneNumber) throws SQLException, ClassNotFoundException {

        String query = "UPDATE TRAINEE SET phone_number = ?, WHERE id = ?;";
        String returnStatus = "";
        try {            
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, phoneNumber);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();                    
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return returnStatus;                
    }  

    /**
     * 
     * update Employee detail
     * 
     * @param id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, dateOfJoining, skill
     * @return void 
     */        
    public String updateEmployeeEmailById(String id, String eMail) throws SQLException, ClassNotFoundException {

        String query = "UPDATE TRAINEE SET eMail = ?, WHERE id = ?;";
        String returnStatus = "";
        try {            
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, eMail);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();                    
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return returnStatus;               
    }  
 
    /**
     * 
     * get trainer detail by getting trainee id
     * 
     * @param id
     * @return void 
     */        
    public Trainer getTrainerById(String id) throws SQLException, ClassNotFoundException {

        Trainer trainer = new Trainer();

	String query = "SELECT * FROM EMPLOYEE INNER JOIN TRAINER ON EMPLOYEE.ID = TRAINER.ID WHERE TRAINER.ID = ? ;";

        try {                    
            connection = getConnection();
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();  
            trainer.setId(resultSet.getString("ID"));
            trainer.setName(resultSet.getString("NAME"));
            trainer.setBloodGroup(resultSet.getString("BLOOD_GROUP"));
            trainer.setDesignation(resultSet.getString("DESIGNATION")); 
            trainer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH")); 
            trainer.setGender(resultSet.getString("GENDER")); 
            trainer.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
            trainer.setEMail(resultSet.getString("EMAIL")); 
            trainer.setTrainingSince(resultSet.getInt("TRAINING_SINCE"));            
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return trainer;
    }

    /**
     * 
     * get trainee detail by getting trainee id
     * 
     * @param id
     * @return void 
     */        
    public Trainee getTraineeById(String id) throws SQLException, ClassNotFoundException {

        Trainee trainee = new Trainee(); 

        String query = "SELECT * FROM EMPLOYEE INNER JOIN TRAINER ON EMPLOYEE.ID = TRAINER.ID" 
                     + "INNER JOIN TRAINER.TRAINEE_ID = SKILLS.TRAINEE_ID WHERE TRAINER.ID = ? ;";

        try {          
            connection = getConnection();
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery(); 
            resultSet.next(); 
            trainee.setId(resultSet.getString("ID"));
            trainee.setName(resultSet.getString("NAME"));
            trainee.setBloodGroup(resultSet.getString("BLOOD_GROUP"));
            trainee.setDesignation(resultSet.getString("DESIGNATION")); 
            trainee.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH")); 
            trainee.setGender(resultSet.getString("GENDER")); 
            trainee.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
            trainee.setEMail(resultSet.getString("EMAIL")); 
            trainee.setDateOfJoining(resultSet.getDate("DATE_OF_JOINING"));
        } catch(SQLException sQLException) {
            throw sQLException; 
        } catch(ClassNotFoundException classNotFoundException) {
            throw classNotFoundException;
        } return trainee;
    }

    /**
     * 
     * calculate length of trainer and trainee table
     * 
     * @param id
     * @return void 
     */        
    public int getEmployeeTableLength() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = null;
        int lengthOfTable = 0;
        PreparedStatement preparedStatement;
        String query = "SELECT COUNT(*) FROM EMPLOYEE ;";

        try {   
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            lengthOfTable = resultSet.getInt("count(*)");
  
        } catch(SQLException sQLException) {
            throw sQLException; 
        }
        catch(ClassNotFoundException classNotFoundException) {
           throw classNotFoundException;
        } return lengthOfTable;         
    }

    public int getSkillTableLength() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = null;
        int lengthOfTable = 0;
        PreparedStatement preparedStatement;
        String query = "SELECT COUNT(*) FROM SKILLS ;";

        try {   
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            lengthOfTable = resultSet.getInt("count(*)");
  
        } catch(SQLException sQLException) {
            throw sQLException; 
        }
        catch(ClassNotFoundException classNotFoundException) {
           throw classNotFoundException;
        } return lengthOfTable;         
    }    
}