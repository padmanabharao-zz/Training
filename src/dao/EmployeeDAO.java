package dao;

import contracts.IEmployee;
import models.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployee {

	// Create jdbc objects that have class scope;
	private Connection northwindConnection = null;
	private PreparedStatement northwindPreparedStatement = null;
	private ResultSet northwindResultSet = null;

	@Override
	public int readEmployeeCount() {
		// create your return count
		int employeeCount = 0;

		// create a try catch finally block
		try {
			// Create a String Object names sql
			String sql = "select count(*) from employees";
			// Create an other String object named path
			String path = "jdbc:mysql://localhost/northwind";
			// Create a Connection object
			northwindConnection = DriverManager.getConnection(path, "root",
					"1234abcd");
			// Create a PreparedStatement Object
			northwindPreparedStatement = northwindConnection
					.prepareStatement(sql);
			// Create a Result Set Object open the connection to the db and
			// execute the SQL query
			// Store the return of the sql query in the result set object
			northwindResultSet = northwindPreparedStatement.executeQuery();
			// loop through the rows of the result set object.
			while (northwindResultSet.next()) {
				// extract the data from the result set and return the object.
				employeeCount = northwindResultSet.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			// Close the jdbc objects in the reverse oder in which we have
			// created.
			try {
				northwindResultSet.close();
				northwindPreparedStatement.close();
				northwindConnection.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		// Return the employeeCount
		return employeeCount;
	}

	@Override
	public Employee findEmployeeByID(int employeeID) {
		// Create the employee object
		Employee employee = new Employee();

		try {
			// Create a String Object names sql
			String sql = "select EmployeeID,LastName,FirstName,Title,HomePhone,Hiredate from Employees where EmployeeID = ?";
			// Create an other String object named path
			String path = "jdbc:mysql://localhost/northwind";
			// Create a Connection object
			northwindConnection = DriverManager.getConnection(path, "root",
					"1234abcd");
			// Create a PreparedStatement Object
			northwindPreparedStatement = northwindConnection
					.prepareStatement(sql);
			// Give the prepared Statement Object the value of the parameter for
			// the employeeID
			northwindPreparedStatement.setInt(1, employeeID);
			// Create a Result Set Object open the connection to the db and
			// execute the SQL query
			// Store the return of the sql query in the result set object
			northwindResultSet = northwindPreparedStatement.executeQuery();
			// loop through the rows of the result set object.
			while (northwindResultSet.next()) {
				// extract the data from the result set and return the object.
				employee.setEmployeeId(northwindResultSet.getInt(1));
				employee.setLastName(northwindResultSet.getString(2));
				employee.setFirstName(northwindResultSet.getString(3));
				employee.setTitle(northwindResultSet.getString(4));
				employee.setHomePhone(northwindResultSet.getString(5));
				employee.setHireDate(northwindResultSet.getString(6));
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			// Close the jdbc objects in the reverse oder in which we have
			// created.
			try {
				northwindResultSet.close();
				northwindPreparedStatement.close();
				northwindConnection.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		// Return the employee Object
		return employee;
	}

	@Override
	public List<Employee> findEmployeesByTitle(String title) {
		// Create the return Object
		List<Employee> employees = new ArrayList<Employee>();

		try {
			// Create a String Object names sql
			String sql = "select EmployeeID,LastName,FirstName,Title,HomePhone,Hiredate from Employees where Title = ?";
			// Create an other String object named path
			String path = "jdbc:mysql://localhost/northwind";
			// Create a Connection object
			northwindConnection = DriverManager.getConnection(path, "root",
					"1234abcd");
			// Create a PreparedStatement Object
			northwindPreparedStatement = northwindConnection
					.prepareStatement(sql);
			// Give the prepared Statement Object the value of the parameter for
			// the employeeID
			northwindPreparedStatement.setString(1, title);
			// Create a Result Set Object open the connection to the db and
			// execute the SQL query
			// Store the return of the sql query in the result set object
			northwindResultSet = northwindPreparedStatement.executeQuery();
			// loop through the rows of the result set object.
			while (northwindResultSet.next()) {
				// extract the data from the result set and return the object.
				Employee employee = new Employee();

				employee.setEmployeeId(northwindResultSet.getInt(1));
				employee.setLastName(northwindResultSet.getString(2));
				employee.setFirstName(northwindResultSet.getString(3));
				employee.setTitle(northwindResultSet.getString(4));
				employee.setHomePhone(northwindResultSet.getString(5));
				employee.setHireDate(northwindResultSet.getString(6));
				employees.add(employee);

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			// Close the jdbc objects in the reverse oder in which we have
			// created.
			try {
				northwindResultSet.close();
				northwindPreparedStatement.close();
				northwindConnection.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		// Return the employee Object
		return employees;
	}

	@Override
	public int createEmployee(Employee employee) {
		// Create the return object
		int rowsAffected = 0;

		// create a try catch finally block
		try {
			String sql = "Insert into employees" +
						  "(EmployeeID,LastName,FirstName,Title,HomePhone,HireDate)" +
						  " Values(?,?,?,?,?,?)";
			String path = "jdbc:mysql://localhost/northwind";
			northwindConnection = DriverManager.getConnection(path,"root","1234abcd");
			northwindPreparedStatement = northwindConnection.prepareStatement(sql);
			// Add the parameters for the Insert Query to the prepareStatement
			northwindPreparedStatement.setInt(1, employee.getEmployeeId());
			northwindPreparedStatement.setString(2, employee.getLastName());
			northwindPreparedStatement.setString(3, employee.getFirstName());
			northwindPreparedStatement.setString(4, employee.getTitle());
			northwindPreparedStatement.setString(5, employee.getHomePhone());
			northwindPreparedStatement.setString(6, employee.getHireDate());
			
			rowsAffected = northwindPreparedStatement.executeUpdate();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
				try{
					northwindPreparedStatement.close();
					northwindConnection.close();
				}catch(SQLException ex){
					ex.printStackTrace();					
				}
		}

		// Return the rows affected as an int
		return rowsAffected;
	}

	@Override
	public List<Employee> readAllEmployees() {

		List<Employee> employees = new ArrayList<Employee>();

		try {
			// Create a String Object names sql and sql statement is NOT case sensitive.
			String sql = "select * from employees";
			// Create an other String object named path
			String path = "jdbc:mysql://localhost/northwind";
			// Create a Connection object
			northwindConnection = DriverManager.getConnection(path, "root","1234abcd");
			// Create a PreparedStatement Object
			northwindPreparedStatement = northwindConnection.prepareStatement(sql);
			// Give the prepared Statement Object the value of the parameter for
			// the employeeID
			
			// Create a Result Set Object open the connection to the db and
			// execute the SQL query
			// Store the return of the sql query in the result set object
			northwindResultSet = northwindPreparedStatement.executeQuery();
			// loop through the rows of the result set object.
			while (northwindResultSet.next()) {
				// extract the data from the result set and return the object.
				Employee employee = new Employee();

				employee.setEmployeeId(northwindResultSet.getInt(1));
				employee.setLastName(northwindResultSet.getString(2));
				employee.setFirstName(northwindResultSet.getString(3));
				employee.setTitle(northwindResultSet.getString(4));
				employee.setHomePhone(northwindResultSet.getString(5));
				employee.setHireDate(northwindResultSet.getString(6));
				employees.add(employee);

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			// Close the jdbc objects in the reverse oder in which we have
			// created.
			try {
				northwindResultSet.close();
				northwindPreparedStatement.close();
				northwindConnection.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return employees;
	}

	
	@Override
	public List<Employee> readEmployeesFromXml() {
	
		List<Employee> employees = new ArrayList<Employee>();
		
		try{
		
			String path = "C:\\Users\\903153\\Desktop\\Java\\Training\\Employees.xml";
			
			File fXmlFile = new File(path);
						
			// Create a Document object to read in the content of xml file 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(fXmlFile);
			
			// take the data out of the Document and load it into a List of Custom Objects.
			// Create a nodel list objects ad tell where to start reading from the xml file.
			NodeList nList = doc.getElementsByTagName("employee");
			
			// Loop through the list of node objects for  counter loop
			for(int temp=0; temp < nList.getLength(); temp++){
				
				// Create an individual node Object which will point to each employee
				Node nNode  = nList.item(temp);
				// Null Check
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
				// Conversion of Node into Element	
					
					Element eElement = (Element)nNode;
					// Create a Employee Object
					Employee employee = new Employee(); 
					// Set the employee objects data to the data in the Element object
					employee.setEmployeeId(Integer.parseInt(eElement.getElementsByTagName("employeeid").item(0).getTextContent()));
					employee.setLastName(eElement.getElementsByTagName("lastname").item(0).getTextContent());
					employee.setFirstName(eElement.getElementsByTagName("firstname").item(0).getTextContent());
					employee.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
					employee.setHomePhone(eElement.getElementsByTagName("homephone").item(0).getTextContent());
					employee.setHireDate(eElement.getElementsByTagName("hiredate").item(0).getTextContent());
					
					// add the employee objects to the list of employees.
					employees.add(employee);
					
					/*
					System.out.println("Employee Id: "+ eElement.getElementsByTagName("employeeid").item(0).getTextContent());
					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Title: "+ eElement.getElementsByTagName("title").item(0).getTextContent());
					System.out.println("Home Phone: "+ eElement.getElementsByTagName("homephone").item(0).getTextContent());
					System.out.println("Hire Date: "+ eElement.getElementsByTagName("hiredate").item(0).getTextContent());
					System.out.println("___________________________" + "\n");
					*/
					
				}				
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return employees;
		
	}

	
	@Override
	public int moveEmployeesFromXmlToDB() {
	
		//createEmployee 
		//readEmploeesFromXml
		
		int rowsAffected = 0;
		List <Employee> employees = readEmployeesFromXml();
		
		// insert the list of employees into Employee table in northwind database (Hint : Use createEmployee method in a loop)
		// add logic here and call this new method from Main.
		
		for(Employee item: employees){			
			rowsAffected += createEmployee(item);						
		}
	
		return rowsAffected;
		
		
	}

	
	
}
