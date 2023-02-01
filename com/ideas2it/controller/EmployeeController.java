package com.ideas2it.controller;

import java.lang.ClassNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Skills;
import com.ideas2it.service.EmployeeService;

/**
* EmployeeController manage EmployeeService, Employee, Trainer and Trainee Class 
*
* @version 1.0 05-09-2022
*
* @author Rohit A P 
*/
public class EmployeeController {
  
    public EmployeeService employeeService = new EmployeeService();  

    private Scanner input = new Scanner(System.in);

    /**
     * 
     * mainMenu method perform CRUD operations
     * 
     * @param 
     * @return void 
     */        
    public void mainMenu()  {
        
        boolean loop  = true;

        do {
            System.out.println("\t\t\t\t\t\t\t\t\t  EMPLOYEE MANAGEMENT");
            System.out.println("\t\t\t\t\t\t\t\t\t  -------------------");
            System.out.println("\t\t\t\t\t\t\t\t\t      MAIN MENU");
            System.out.println("\t\t\t\t\t\t\t\t\t      ---------");      
            System.out.println("\n 1.TRAINER MENU \n 2.TRAINEE MENU \n 3.Get Employee by ID " +
                               "\n 4.Update Employee detail\n 5.EXIT");
            int option = input.nextInt();
            switch(option) { 
                case 1:
                    trainerMenu();
                    break;
                case 2:
                    traineeMenu();
                    break;
                case 3:
                    getEmployeeById();
                    break; 
                case 4:
                    updateEmployeeDetailById();
                    break;   
                case 5:	        	
                    loop = false;			
                    break;        
                default:
                    System.out.println("INVALID OPTION");
                    break; 
            }
        } while(loop == true);   
    }  

    /**
     * 
     * manages trainer CRUD operation
     * 
     * @param 
     * @return void 
     */                                                                                                                                                                   
    private void trainerMenu() { 

        System.out.println("\n 1.Create Trainer details\n 2.View All Trainer \n 3.Delete Trainer By Id ");
        int option = input.nextInt();   
        boolean loop = false;
        String id;
        switch(option) {
            case 1:                       
                insertEmployee(1);
                break;
            case 2:            
                System.out.print("\n----------------------------------------------");
                for(Trainer trainer : employeeService.viewtrainerDetails()) {
                    System.out.print("\n----------------------------------------------");
                    System.out.print(trainer);  
                    int age = employeeService.calculateAge(trainer.getDateOfBirth()); 
                    System.out.println("\nAGE                  : " + age);
                    System.out.print("\n----------------------------------------------");
                }  
                System.out.print("\n----------------------------------------------\n\n");
                break;
            case 3:
                do {
                    System.out.print("Enter Trainer ID to be deleted : ");
                    id = input.next();
                    loop = employeeService.isValidId(id);   
                } while(!loop);
                employeeService.deleteTrainerById(id);  
                break;                
          default:
               System.out.println("Invalid option");
               break;
        } 
    }

    /**
     * 
     * manages trainee CRUD operation
     * 
     * @param 
     * @return void 
     */    
    private void traineeMenu() {

        System.out.println("\n 1.Create Trainee details\n 2.View All Trainee \n 3.Delete Trainee By Id "); 
        int option = input.nextInt();
        boolean loop = false;
        String id;   

        switch(option) {
            case 1:                    
                insertEmployee(3);
                break;
            case 2: 
                System.out.print("\n----------------------------------------------");
                for(Trainee trainee : employeeService.viewtraineeDetails()) {
                    System.out.print("\n----------------------------------------------");
                    System.out.print(trainee);  
                    int age = employeeService.calculateAge(trainee.getDateOfBirth()); 
                    System.out.println("\nAGE                  : " + age);
                    System.out.print("\n----------------------------------------------");
                }
                System.out.print("\n----------------------------------------------\n\n");
                break;
            case 3:
                do {
                    System.out.print("Enter Trainee ID to be deleted : ");
                    id = input.next();
                    loop = employeeService.isValidId(id);   
                    if (loop == false) {
                        System.out.println("Invalid ID");
                    }  
                } while(!loop);
                employeeService.deleteTraineeById(id);  
                break; 
            default:
                System.out.println("Invalid option");
                break;
        }   
    }

    /**
     * 
     * gets common attributes of trainer and trainee
     * 
     * @param option
     * @return void 
     */      
    public void insertEmployee(int option) {
 
        String id = employeeService.genarateEmployeeId();
        System.out.print("ID                                : ");
        System.out.println(id); 

        boolean loop = false;

        String name = ""; 
        do {
            System.out.print("Enter Name                        : ");
            name = input.next();
            loop = employeeService.isValidName(name);
            if (loop == false) {
                System.out.println("Invalid name");
            }
        } while(!loop); 

        System.out.print("Enter Blood Group                 : ");
        String bloodGroup = input.next();
 
        Date dateOfBirth = null;
        do { 
            System.out.print("Enter Date of birth (YYYY-MM-DD)  : ");
            String date = input.next();
            loop = employeeService.isValidDate(date);
            if(loop == true) {
                dateOfBirth = Date.valueOf(date);
            } else {
                System.out.println("Invalid Date");                
            }      
        } while(!loop);  
      
        System.out.print("Enter Designation                 : ");
        String designation = input.next();  

        System.out.println("1.MALE 2.FEMALE 3.OTHERS");  
        System.out.print("Enter Gender number               : ");
        int genderOption = input.nextInt(); 
        String gender = employeeService.selectGender(genderOption); 
 
        long phoneNumber; 
        do {
            System.out.print("Enter Phone  Number               : ");
            phoneNumber = input.nextLong();
            loop = employeeService.isValidPhoneNumber(phoneNumber);
            if (loop == false) {
                System.out.println("Invalid Phone Number");
            }
        } while(!loop);        

        String eMail = "";
        do {
            System.out.print("Enter E-Mail                      : ");
            eMail = input.next();                       
            loop = employeeService.isValidEmail(eMail);
            if (loop == false) {
                System.out.println("Invalid Email");
            }
        } while(!loop);        
 
        if(option == 1) {
            insertTrainer(id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail);         
        } else if (option == 3) {
            insertTrainee(id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail);      
        }
    }

    /**
     * 
     * trainer details to MYSQL database
     * 
     * @param id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, trainingExperience
     * @return void 
     */      
    public void insertTrainer(String id, String name, String bloodGroup, String designation, 
                              Date dateOfBirth, String gender, long phoneNumber, String eMail) { 
           
        String trainerId = "R" + id;

        System.out.print("Training Since                    : ");
        int trainingExperience = input.nextInt();
  
        Trainer trainer = new Trainer(id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, 
                                      trainerId, trainingExperience);
        employeeService.insertTrainer(trainer);    
    }    

    /**
     * 
     * insert trainee details to MYSQL database
     * 
     * @param id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, dateOfJoining, skills
     * @return void 
     */      
    public void insertTrainee(String id, String name, String bloodGroup, String designation, 
                              Date dateOfBirth, String gender, long phoneNumber, String eMail) {
     
        String traineeId = "E" + id;
        
        boolean loop = false;

        Date dateOfJoining = null;       
        do { 
            System.out.print("Enter Date of joining (YYYY-MM-DD): ");
            String date = input.next();
            loop = employeeService.isValidDate(date);
            if(loop == true) {
                dateOfJoining = Date.valueOf(date);
            } else {
                System.out.println("Invalid Date");                
            }      
        } while(!loop);                 

        int skillsTableSize = employeeService.getSikillTableSize();  
        int idSufffix = 22000 + skillsTableSize;
  
        System.out.print("Enter total number of skills        : "); 
        int totalNumberOfSkills = input.nextInt(); 
  
        ArrayList<Skills> skills = new ArrayList<Skills>();

        Skills skill = new Skills();

        for(int index = 0; index < totalNumberOfSkills; index++) {
            
            String insertDetail = "";
                             
            insertDetail = "SI" + idSufffix + index;
  
            System.out.print("Skill id                       : ");
            System.out.println(insertDetail);

            skill.setTraineeId(traineeId);

            System.out.print("skill Name                     : ");
            skill.setSkillName(input.next());

            System.out.print("skill Exprience                : ");
            skill.setSkillExperience(input.nextFloat());

            System.out.print("Skill Version                  : ");
            skill.setSkillVersion(input.next());

            System.out.print("Certified in skill  ( YES OR NO ) : ");
            skill.setSkillCertification(input.next());
             
            skills.add(skill);   
        }

        Trainee trainee = new Trainee(id, name, bloodGroup, designation, dateOfBirth, gender, phoneNumber, eMail, traineeId, dateOfJoining, skills);  

        employeeService.insertTrainee(trainee);
    }     

    /**
     * 
     * update trainee details
     * 
     * @param 
     * @return void 
     */      
    public void updateEmployeeDetailById() {

        boolean loop = false; 
  
        System.out.println("Update Employee Detail By Id");
        System.out.print("Enter Trainee ID: ");
        String id = input.next();

        System.out.println("UPDATE DETAIL");
        System.out.println("WHICH DETAIL YOU WANT TO UPDATE");
        System.out.println("\n 1.NAME \n 2.DESIGNATION \n 3.PHONE NUMBER \n 4.EMAIL");
        System.out.print("Enter option : ");
        int option = input.nextInt();  

        String returnValue = "";
        switch(option) {
            case 1:
                String name = ""; 
                do {
                    System.out.print("Enter Name                        : ");
                    name = input.next();
                    loop = employeeService.isValidName(id);
                    if (loop == false) {
                        System.out.println("Invalid name");
                    }
                } while(!loop);
                returnValue = employeeService.updateEmployeeNameById(id, name);
                System.out.println(returnValue);
                break;
            case 2:
                System.out.print("Enter Designation                 : ");
                String designation = input.next();
                returnValue = employeeService.updateEmployeeDesignationById(id, designation);       
                System.out.println(returnValue);           
                break;
            case 3:
                long phoneNumber;  
                do {
                    System.out.print("Enter Phone Number               : ");
                    phoneNumber = input.nextLong();
                    loop = employeeService.isValidPhoneNumber(phoneNumber);
                    if (loop == false) {
                        System.out.println("Invalid Phone Number");
                    }
                } while(!loop);                                     
                returnValue = employeeService.updateEmployeePhoneNumberById(id, phoneNumber);  
                System.out.println(returnValue); 
                break;
            case 4:
                String eMail = "";
                do {
                    System.out.print("Enter E-Mail                      : ");
                    eMail = input.next();                       
                    loop = employeeService.isValidEmail(eMail);
                    if (loop == false) {
                        System.out.println("Invalid Email");
                    }
                } while(!loop);    
                returnValue = employeeService.updateEmployeeEmailById(id, eMail);
                System.out.println(returnValue);
                break;                    
            }
    }

    public void getEmployeeById() {

        System.out.print(" 1.Get Trainer detail By ID \n 2.Get Trainee detail By ID \n Enter option number:");
        int option = input.nextInt();
        System.out.print("Enter ID :");
        String id = input.next();
     
        switch(option) {
            case 1:  
                Trainer trainer = employeeService.getTrainerById(id);
                System.out.println(trainer); 
                break;
            case 2:
                Trainee trainee = employeeService.getTraineeById(id);
                System.out.println(trainee);
                break;
        }  
    }
}               