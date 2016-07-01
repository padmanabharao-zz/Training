package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.City;
import contracts.ICity;

public class CityDAO implements ICity {

	@Override
	public int readCityCount() {

		Connection worldConnection = null;
		PreparedStatement worldPreparedStatement = null;
		ResultSet worldResultSet = null;


		int cityCount = 0;

		
		try {
			String sql = "select count(*) from city";

			String path = "jdbc:mySql://localhost/world";
			worldConnection = DriverManager.getConnection(path,"root","1234abcd");
			worldPreparedStatement = worldConnection.prepareStatement(sql);
			worldResultSet = worldPreparedStatement.executeQuery();

			while (worldResultSet.next()) {
				cityCount = worldResultSet.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			try {
				worldResultSet.close();
				worldPreparedStatement.close();
				worldConnection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return cityCount;

	}

	@Override
	public int createCity(City city) {
		
		int rowsInserted = 0;
		Connection worldConnection = null;
		PreparedStatement worldPreparedStatement = null;
		
		
		try {

		String sql = "Insert into city" +
					  "(ID,Name,CountryCode,District,Population)" +
					  " Values(?,?,?,?,?)";
		String path = "jdbc:mysql://localhost/world";
		worldConnection = DriverManager.getConnection(path,"root","1234abcd");
		worldPreparedStatement = worldConnection.prepareStatement(sql);
		// Add the parameters for the Insert Query to the prepareStatement
		worldPreparedStatement.setInt(1, city.getId());
		worldPreparedStatement.setString(2, city.getName());
		worldPreparedStatement.setString(3, city.getCountryName());
		worldPreparedStatement.setString(4, city.getDistrict());
		worldPreparedStatement.setInt(5, city.getPopulation());
		
		rowsInserted = worldPreparedStatement.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				worldPreparedStatement.close();
				worldConnection.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
		return rowsInserted;
	}

}
