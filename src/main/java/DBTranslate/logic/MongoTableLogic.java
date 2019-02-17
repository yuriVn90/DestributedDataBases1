 package DBTranslate.logic;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DBTranslate.DTO.CountriesSqlTableDTO;
import DBTranslate.DTO.DepartmentsSqlTableDTO;
import DBTranslate.DTO.EmployeesSqlTableDTO;
import DBTranslate.DTO.ISqlTableDTO;
import DBTranslate.DTO.JobHistorySqlTableDTO;
import DBTranslate.DTO.JobsSqlTableDTO;
import DBTranslate.DTO.LocationsSqlTableDTO;
import DBTranslate.DTO.RegionsSqlTableDTO;


public class MongoTableLogic {
	
	private MongoClient mongoClient;
	private String mongodbName;
	private MongoDatabase mongoDatabase;
	
	public MongoTableLogic() {
		this.mongoClient = new MongoClient();
		this.mongodbName = "example";
		this.mongoDatabase = mongoClient.getDatabase(mongodbName);
	}
	
	
	
	public ISqlTableDTO[] getTableFromMongo(String tableName)
	{
		if("EMPLOYEES".equalsIgnoreCase(tableName))
		{
			return readEmployeesCollection();
		}
		else if("DEPARTMENTS".equalsIgnoreCase(tableName))
		{
			return readDepartmentsCollection();
		}
		else if("LOCATIONS".equalsIgnoreCase(tableName))
		{
			return readLocationsCollection();
		}
		else if("COUNTRIES".equalsIgnoreCase(tableName))
		{
			return readCountriesCollection();
		}
		else if("REGIONS".equalsIgnoreCase(tableName))
		{
			return readRegionsCollection();
		}
		else if("JOB_HISTORY".equalsIgnoreCase(tableName))
		{
			return readJobHistoryCollection();
		}
		else if("JOBS".equalsIgnoreCase(tableName))
		{
			return readJobsCollection();
		}
		
		return null;
	}
	

	private ISqlTableDTO[] readEmployeesCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("EMPLOYEES");
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			
			 long employee_id =  Doc.getInteger("employee_id", 0);
			 String first_name = Doc.getString("first_name");
			 String last_name= Doc.getString("last_name"); 
			 String email = Doc.getString("email"); 
			 String phone_number = Doc.getInteger("phone_number",0)+"";
			 Date hire_date = changeToSqlDate(Doc.getString("hire_date"));	 
			 long job_id  =  Doc.getInteger("job_id", 0);
			 int salary  =   Doc.getInteger("salary", 0);
			 String commission_pct  = Doc.getInteger("commission_pct",0)+""; 
			 long manager_id = Doc.getInteger("manager_id", 0);
			 long department_id=  Doc.getInteger("department_id", 0);
			 
			    listOfISqlTableDTO.add(new EmployeesSqlTableDTO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
	
		
		return data;
	}
    
    
    private ISqlTableDTO[] readLocationsCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("LOCATIONS");
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			
			 long location_id  = Doc.getInteger("location_id", 0);
			 String street_address =  Doc.getString("street_address"); 
			 int postal_code = Doc.getInteger("postal_code", 0);
			 String city =  Doc.getString("city"); 
			 String state_province =  Doc.getInteger("state_province",0)+""; 
			 long country_id = Doc.getInteger("country_id", 0);
			
		     listOfISqlTableDTO.add(new LocationsSqlTableDTO(location_id, street_address, postal_code, city, state_province, country_id));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
		return data;
	}

    
    private ISqlTableDTO[] readCountriesCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("COUNTRIES");
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			
			 int country_id = Doc.getInteger("country_id", 0);
			 String country_name = Doc.getString("country_name");
			 int region_id = Doc.getInteger("region_id", 0);
			
		     listOfISqlTableDTO.add(new CountriesSqlTableDTO(country_id, country_name, region_id));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
		return data;
	}
    
    
    private ISqlTableDTO[] readRegionsCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("REGIONS");
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			
			 long region_id = Doc.getInteger("region_id", 0);
			 String region_name= Doc.getString("region_name");
			
		     listOfISqlTableDTO.add(new RegionsSqlTableDTO(region_id, region_name));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
		return data;
	}
	
    private ISqlTableDTO[] readJobHistoryCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("JOB_HISTORY");
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			 
			 long employee_id = Doc.getInteger("employee_id", 0);
			 Date start_date =  changeToSqlDate(Doc.getString("start_date"));
			 Date end_date = changeToSqlDate(Doc.getString("end_date"));	 
			 int job_id =Doc.getInteger("job_id", 0);
			 int department_id = Doc.getInteger("department_id", 0);
			
		     listOfISqlTableDTO.add(new JobHistorySqlTableDTO(employee_id, start_date, end_date, job_id, department_id));

		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
		return data;
	}
    
    
    private Date changeToSqlDate(String string) {
		// TODO Auto-generated method stub
	try {
    		
    		if(!"null".equalsIgnoreCase(string)){
    			
    	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    	        java.util.Date parsed = format.parse(string);
    	        
    	        java.sql.Date sql = new java.sql.Date(parsed.getTime());

            return sql;
    		}

    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    	return null;
	}



	private ISqlTableDTO[] readJobsCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();		
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("JOBS");
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next(); 
			 
			 long job_id = Doc.getInteger("job_id", 0);
			 String job_title = Doc.getString("job_title");
			 int min_salary= Doc.getInteger("min_salary", 0);
			 int max_salary= Doc.getInteger("max_salary", 0);
			
			
		     listOfISqlTableDTO.add(new JobsSqlTableDTO(job_id, job_title, min_salary, max_salary));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
		return data;
	}
	
    
    private ISqlTableDTO[] readDepartmentsCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("DEPARTMENTS");
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next(); 

			 long department_id = Doc.getInteger("department_id", 0);
		     String department_name = Doc.getString("department_name");
		 	 long manager_id = Doc.getInteger("manager_id", 0);
			 long location_id = Doc.getInteger("location_id", 0);
			
		     listOfISqlTableDTO.add(new DepartmentsSqlTableDTO(department_id,department_name,manager_id,location_id));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
		return data;
	}

}
