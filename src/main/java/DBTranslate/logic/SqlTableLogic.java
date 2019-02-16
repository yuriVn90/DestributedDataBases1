package DBTranslate.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBTranslate.DTO.CountriesSqlTableDTO;
import DBTranslate.DTO.DepartmentsSqlTableDTO;
import DBTranslate.DTO.EmployeesSqlTableDTO;
import DBTranslate.DTO.ISqlTableDTO;
import DBTranslate.DTO.JobHistorySqlTableDTO;
import DBTranslate.DTO.JobsSqlTableDTO;
import DBTranslate.DTO.LocationsSqlTableDTO;
import DBTranslate.DTO.RegionsSqlTableDTO;


public class SqlTableLogic {
	
	public static final String URL= "jdbc:mysql://localhost:3306;";
	public static final String DB = "databaseName=hr";
	public static final String URLCon = "jdbc:mysql://localhost:3306/hr?useSSL=true";

    private String user;
    private String password;
    private Connection connection;
	
	public SqlTableLogic() throws SQLException {
		
	     
	     this.user = "root";
	     this.password = "root";
	     this.connection = DriverManager.getConnection(URLCon, user, password);
	     
	}
	
	public ISqlTableDTO[] readLocationsTable(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		
	    try {
	        Statement statment = connection.createStatement();
	        String sql = "Select * from LOCATIONS";
	        ResultSet resultSet = statment.executeQuery(sql);
	        
			while(resultSet.next()){
				 long location_id  = resultSet.getLong(resultSet.getMetaData().getColumnName(1));
				 String street_address = resultSet.getString(resultSet.getMetaData().getColumnName(2));
				 int  postal_code = resultSet.getInt(resultSet.getMetaData().getColumnName(3));
				 String city = resultSet.getString(resultSet.getMetaData().getColumnName(4)); 
				 String state_province = resultSet.getString(resultSet.getMetaData().getColumnName(5));
				 long  country_id = resultSet.getLong(resultSet.getMetaData().getColumnName(6));
				
			     listOfISqlTableDTO.add(new LocationsSqlTableDTO(location_id, street_address, postal_code, city, state_province, country_id));
			}
			ISqlTableDTO[] allData = new ISqlTableDTO[listOfISqlTableDTO.size()];
			allData = listOfISqlTableDTO.toArray(allData);
			
			return allData;
	        
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		return null;
	}
	
	
	public ISqlTableDTO[] readCountriesTable(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
	    try {
	        Statement statment = connection.createStatement();
	        String sql = "Select * from COUNTRIES";
	        ResultSet resultSet = statment.executeQuery(sql);
	        
			while(resultSet.next()){
				
				 int country_id = resultSet.getInt(resultSet.getMetaData().getColumnName(1));
				 String country_name = resultSet.getString(resultSet.getMetaData().getColumnName(2)); ;
				 int region_id = resultSet.getInt(resultSet.getMetaData().getColumnName(3));
				
				 listOfISqlTableDTO.add(new CountriesSqlTableDTO(country_id, country_name, region_id));
			}
			
			ISqlTableDTO[] allData = new ISqlTableDTO[listOfISqlTableDTO.size()];
			allData = listOfISqlTableDTO.toArray(allData);
			
			return allData;
	        
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return null;
	}
	
	public ISqlTableDTO[] readRegionsTable(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		
	    try {
	        Statement statement = connection.createStatement();
	        String sql = "Select * from REGIONS";
	        ResultSet resultSet = statement.executeQuery(sql);
	        
			while(resultSet.next()){
				 long region_id = resultSet.getLong(resultSet.getMetaData().getColumnName(1)) ;
				 String region_name= resultSet.getString(resultSet.getMetaData().getColumnName(2)) ;
				
				 listOfISqlTableDTO.add(new RegionsSqlTableDTO(region_id, region_name));
			}
			
			ISqlTableDTO[] allData = new ISqlTableDTO[listOfISqlTableDTO.size()];
			allData = listOfISqlTableDTO.toArray(allData);
			
			return allData;
	        
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return null;
	}
	
	public ISqlTableDTO[] readJobHistoryTable(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		
	    try {
	        Statement stmt = connection.createStatement();
	        String sql = "Select * from JOB_HISTORY";
	        ResultSet resultSet = stmt.executeQuery(sql);
	        
			while(resultSet.next()){
				 long employee_id = resultSet.getLong(resultSet.getMetaData().getColumnName(1))  ;
				 Date  start_date = resultSet.getDate(resultSet.getMetaData().getColumnName(2));
				 Date  end_date = resultSet.getDate(resultSet.getMetaData().getColumnName(3));
				 int  job_id = resultSet.getInt(resultSet.getMetaData().getColumnName(4)) ;
				 int  department_id = resultSet.getInt(resultSet.getMetaData().getColumnName(5))  ;
				
				 listOfISqlTableDTO.add(new JobHistorySqlTableDTO(employee_id, start_date,end_date, job_id, department_id));
			}
			
			ISqlTableDTO[] allData = new ISqlTableDTO[listOfISqlTableDTO.size()];
			allData = listOfISqlTableDTO.toArray(allData);
			
			return allData;
	        
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		return null;
	}
	
	public ISqlTableDTO[] readJobsTable(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		
	    try {
	        Statement stmt = connection.createStatement();
	        String sql = "Select * from JOBS";
	        ResultSet res = stmt.executeQuery(sql);
	        
			while(res.next())
			{
				
				 long job_id = res.getLong(res.getMetaData().getColumnName(1)) ;
				 String job_title = res.getString(res.getMetaData().getColumnName(2)) ;
				 int min_salary= res.getInt(res.getMetaData().getColumnName(3)) ;
				 int max_salary= res.getInt(res.getMetaData().getColumnName(4)) ;
				
				
				 listOfISqlTableDTO.add(new JobsSqlTableDTO(job_id, job_title, min_salary, max_salary));
			}
			
			ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
			data = listOfISqlTableDTO.toArray(data);
			
			return data;
	        
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return null;
	}
	
	public ISqlTableDTO[] readEmployeesTable()
	{
		ArrayList<ISqlTableDTO>listOfISqlTableDTO = new ArrayList<>();
		
	    try {
	        Statement stmt = connection.createStatement();
	        String sql = "Select * from EMPLOYEES";
	        ResultSet res = stmt.executeQuery(sql);
	        
			while(res.next())
			{
				 long employee_id =  res.getLong(res.getMetaData().getColumnName(1));
				 String first_name = res.getString(res.getMetaData().getColumnName(2));
				 String last_name= res.getString(res.getMetaData().getColumnName(3));
				 String email = res.getString(res.getMetaData().getColumnName(4));
				 String phone_number = res.getString(res.getMetaData().getColumnName(5));
				 Date hire_date  = res.getDate(res.getMetaData().getColumnName(6));
				 long job_id  =  res.getLong(res.getMetaData().getColumnName(7));
				 int salary  =  res.getInt(res.getMetaData().getColumnName(8));
				 String commission_pct  = res.getString(res.getMetaData().getColumnName(9));
				 long manager_id =  res.getLong(res.getMetaData().getColumnName(10));
				 long department_id=  res.getLong(res.getMetaData().getColumnName(11));
			
				
				
				 listOfISqlTableDTO.add(new EmployeesSqlTableDTO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id));
			}
			
			ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
			data = listOfISqlTableDTO.toArray(data);
			
			return data;
	        
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return null;
	}
	
	
	public ISqlTableDTO[] readDepartmentsTable(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		
	    try {
	        Statement stmt = connection.createStatement();
	        String SQL = "Select * from DEPARTMENTS";
	        ResultSet res = stmt.executeQuery(SQL);
	        
			while(res.next())
			{
				 long department_id = res.getLong(res.getMetaData().getColumnName(1));
			     String department_name = res.getString(res.getMetaData().getColumnName(2));
			 	 long manager_id = res.getLong(res.getMetaData().getColumnName(3));
				 long location_id = res.getLong(res.getMetaData().getColumnName(4));
				
				 listOfISqlTableDTO.add(new DepartmentsSqlTableDTO(department_id,department_name,manager_id,location_id));
			}
			
			ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
			data = listOfISqlTableDTO.toArray(data);
			
			return data;
	        
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return null;
	}
}
