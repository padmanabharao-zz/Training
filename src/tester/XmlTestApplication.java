package tester;

import dao.EmployeeDAO;

import java.util.List;

import models.Employee;

public class XmlTestApplication {

	public static void main(String[] args) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employees = employeeDAO.readEmployeesFromXml();
		
		for (Employee item : employees){
			System.out.println(item.getEmployeeId() + "," +
							   item.getFirstName() + "," +
							   item.getLastName() + "," +
							   item.getTitle() + "," +
							   item.getHomePhone() + "," +
							   item.getHireDate());		
		}
	}

}
