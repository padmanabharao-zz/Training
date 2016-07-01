package tester;

import dao.CityDAO;
import dao.EmployeeDAO;
import models.Employee;
import models.City;

import java.util.List;
import java.util.ArrayList;

public class TestApplication {

	public static void main(String[] args) {
		
		//EmployeeDAO employeeDAO = new EmployeeDAO();
		CityDAO cityDAO = new CityDAO();
		
		// Step 1 : Create an Employee Object
		//Employee testemployee = new Employee();
		
		City insertCity = new City();
		
		// Step 2 : set the testemployees data to mocak data
		
		insertCity.setId(4081);
		insertCity.setName("Jersey City");
		insertCity.setCountryName("USA");
		insertCity.setDistrict("NorthEast");
		insertCity.setPopulation(300300);
		
		// Step 3: call the method pass it to9 employee object and store the return in an int names rowsAffected
		
		int rowsCity = cityDAO.createCity(insertCity);
		
		System.out.println("Number of rows Inserted = " + rowsCity);
	}

}
