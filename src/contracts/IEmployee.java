package contracts;

import models.Employee;

import java.util.List;

public interface IEmployee {

	// define the read signatures
	int readEmployeeCount();
	Employee findEmployeeByID(int employeeID);
	List<Employee> findEmployeesByTitle(String title);
	List<Employee> readAllEmployees();
	
	// define Create signatures
	int createEmployee(Employee employee);
	
	// define readXML Signature
	List<Employee> readEmployeesFromXml();
	
	// Define a move method Signature
	int moveEmployeesFromXmlToDB();
	
	
}
