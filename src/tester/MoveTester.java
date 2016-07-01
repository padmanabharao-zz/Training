package tester;

import dao.EmployeeDAO;
import bll.EmployeeBLL;
import models.Employee;
import java.util.List;


public class MoveTester {

	public static void main(String[] args) {

		EmployeeBLL employeeBLL = new EmployeeBLL();
		
		//int rowsAffected = employeeDAO.moveEmployeesFromXmlToDB();		
		//System.out.println("The Number of Rows Affected: " + rowsAffected);
	
		List<Employee> employees = employeeBLL.readAllEmployees();
		
		for(Employee item: employees){
		
			System.out.println(item.getTitle() + "," + item.getHomePhone());
		}
		
		}
}
