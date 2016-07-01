package bll;

import java.util.List;
import dao.EmployeeDAO;
import models.Employee;
import contracts.IEmployee;


public class EmployeeBLL implements IEmployee{

	@Override
	public int readEmployeeCount() {
		return 0;
	}

	@Override
	public Employee findEmployeeByID(int employeeID) {
		
		return null;
	}

	@Override
	public List<Employee> findEmployeesByTitle(String title) {
		
		return null;
	}

	@Override
	public List<Employee> readAllEmployees() {
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employees = employeeDAO.readAllEmployees();
		
		// apply the business rule.
		/* If an employee Title contains the word manager then that employees homephone must be set to the string NOT AVAILABLE	 */
		
		for(Employee item : employees){
			
			if (item.getTitle().toLowerCase().contains("manager")){
				
				item.setHomePhone("Not Available");
			}
		}
		
		return employees;
	}

	@Override
	public int createEmployee(Employee employee) {
		
		return 0;
	}

	@Override
	public List<Employee> readEmployeesFromXml() {
		
		return null;
	}

	@Override
	public int moveEmployeesFromXmlToDB() {
		
		return 0;
	}

	
	
}
