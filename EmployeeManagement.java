import com.ideas2it.controller.EmployeeController;

/**
* EmployeeManagement is used to access EmployeeController class 
*
* @version 1.0 06-09-2022
*
* @author Rohit A P 
*/
public class EmployeeManagement {

    public static void main(String[] args) { 
 
        EmployeeController employeeController = new EmployeeController();
                  
        employeeController.mainMenu();       
    }        
}