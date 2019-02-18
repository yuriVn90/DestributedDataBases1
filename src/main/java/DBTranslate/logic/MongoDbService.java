package DBTranslate.logic;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import DBTranslate.DTO.CountriesSqlTableDTO;
import DBTranslate.DTO.DepartmentsSqlTableDTO;
import DBTranslate.DTO.EmployeesSqlTableDTO;
import DBTranslate.DTO.ISqlTableDTO;
import DBTranslate.DTO.JobHistorySqlTableDTO;
import DBTranslate.DTO.JobsSqlTableDTO;
import DBTranslate.DTO.LocationsSqlTableDTO;
import DBTranslate.DTO.RegionsSqlTableDTO;

@Service
public class MongoDbService implements IMongoDbService {
	
	private final static String TABLE_COUNTRIES = "COUNTRIES";
	private final static String TABLE_DEPARTMENTS = "DEPARTMENTS";
	private final static String TABLE_EMPLOYEES = "EMPLOYEES";
	private final static String TABLE_JOB_HISTORY = "JOB_HISTORY";
	private final static String TABLE_JOBS = "JOBS";
	private final static String TABLE_LOCATIONS = "LOCATIONS";
	private final static String TABLE_REGIONS = "REGIONS";
	
	private MongoClient mongoClient;
	private String mongodbName;
	
	private ICsvService csv;
	private IMySqlService mySql;
	private MongoDatabase mongoDatabase;
	
	public MongoDbService() {
		this.mongoClient = new MongoClient();
		this.mongodbName = "mongo_hr";
		this.mongoDatabase = mongoClient.getDatabase(mongodbName);
	}
	
	@Autowired
	public void init(ICsvService csv, IMySqlService mySql) {
		this.csv = csv;
		this.mySql = mySql;
	}
	
	@Override
	public void mongoImportFromCsv(String tableName, String fileName) throws Exception {
		mySql.exportTableToCSV(tableName, fileName);
		this.csv.importFromCsv(tableName, fileName);
	}

	@Override
	public ISqlTableDTO[] getMongoDbCollection(String collectionName) {
		switch (collectionName.toUpperCase()) {
			case TABLE_COUNTRIES: 	return this.readCountriesCollection();
			case TABLE_DEPARTMENTS: return this.readDepartmentsCollection();
			case TABLE_EMPLOYEES:	return this.readEmployeesCollection();
			case TABLE_JOB_HISTORY:	return this.readJobHistoryCollection();
			case TABLE_JOBS:		return this.readJobsCollection();
			case TABLE_LOCATIONS:	return this.readLocationsCollection();
			case TABLE_REGIONS:		return this.readRegionsCollection();
			default:
		}
		return null;
	}

	@Override
	public int getMaxSalary() {
		try {
			this.mongoImportFromCsv(TABLE_EMPLOYEES, TABLE_EMPLOYEES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_EMPLOYEES);
		AggregateIterable<Document> ag = mongoCollection.aggregate(Arrays.asList(Aggregates.group(null, Accumulators.max("totalSum", "$salary"))));	
		Document first = ag.first();
		if (first != null) {
			return (int)first.get("totalSum");
		} 
		return -1;
	}

	@Override
	public int getNumbersOfEmployeesFromCountry(String countryName) {
		try {
			this.mongoImportFromCsv(TABLE_COUNTRIES, TABLE_COUNTRIES);
			this.mongoImportFromCsv(TABLE_LOCATIONS, TABLE_LOCATIONS);
			this.mongoImportFromCsv(TABLE_DEPARTMENTS, TABLE_DEPARTMENTS);
			this.mongoImportFromCsv(TABLE_EMPLOYEES, TABLE_EMPLOYEES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollectionFirst = mongoDatabase.getCollection(TABLE_COUNTRIES);
		MongoCollection<Document> mongoCollectionSecond = mongoDatabase.getCollection(TABLE_LOCATIONS);
		MongoCollection<Document> mongoCollectionThird = mongoDatabase.getCollection(TABLE_DEPARTMENTS);
		MongoCollection<Document> mongoCollectionFourth = mongoDatabase.getCollection(TABLE_EMPLOYEES);
		FindIterable<Document> doc = mongoCollectionFirst.find(new BasicDBObject("country_name", countryName));
		int countryId = -1;
		if (doc.first() != null) {
			countryId = doc.first().getInteger("country_id");
		} else {
			return 0;
		}

		AggregateIterable<Document> locations = mongoCollectionSecond.aggregate(Arrays.asList(Aggregates.match(new BasicDBObject("country_id", countryId)), Aggregates.group("$location_id")));
		
		AggregateIterable<Document> departments = null;
		AggregateIterable<Document> employees = null;
		List<Document> employeesIds = new ArrayList<>();
		
		if (locations != null) {
			for (Document d : locations) {
				departments = mongoCollectionThird.aggregate(Arrays.asList(Aggregates.match(new BasicDBObject("location_id", d.getInteger("_id"))), Aggregates.group("$department_id")));
				if (departments != null) {
					for(Document l : departments) {
						employees = mongoCollectionFourth.aggregate(Arrays.asList(Aggregates.match(new BasicDBObject("department_id", l.getInteger("_id"))), Aggregates.group("$employee_id")));
						if (employees != null) {
							for(Document e : employees) {
								employeesIds.add(e);
							}
						} else {
							return 0;
						}
					}
				} else {
					return 0;
				}
			}
		} else {
			return 0;
		}
	    return employeesIds.size();
	}

	@Override
	public String[] getEmailsOfJobTitle(String jobTitle) {
		try {
			this.mongoImportFromCsv(TABLE_JOBS, TABLE_JOBS);
			this.mongoImportFromCsv(TABLE_EMPLOYEES, TABLE_EMPLOYEES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollectionFirst = mongoDatabase.getCollection(TABLE_JOBS);
		MongoCollection<Document> mongoCollectionSecond = mongoDatabase.getCollection(TABLE_EMPLOYEES);
		
		AggregateIterable<Document> jobs = mongoCollectionFirst.aggregate(Arrays.asList(Aggregates.match(new BasicDBObject("job_title", jobTitle)), Aggregates.group("$job_id")));
		List<String> employeeEmails = new ArrayList<>();
		for (Document j : jobs) {
			AggregateIterable<Document> employees = mongoCollectionSecond.aggregate(Arrays.asList(Aggregates.match(new BasicDBObject("job_id", j.getInteger("_id"))), Aggregates.group("$email")));
			for (Document e : employees) {
				employeeEmails.add(e.getString("_id"));
			}
		}
	    
		String[] allEmails = new String[employeeEmails.size()];
		return employeeEmails.toArray(allEmails);
	}
	
	private ISqlTableDTO[] readEmployeesCollection(){
		Set<ISqlTableDTO> listOfISqlTableDTO = new HashSet<>();
		try {
			this.mongoImportFromCsv(TABLE_EMPLOYEES, TABLE_EMPLOYEES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_EMPLOYEES);
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			
			 int employee_id =  Doc.getInteger("employee_id", 0);
			 String first_name = Doc.getString("first_name");
			 String last_name= Doc.getString("last_name"); 
			 String email = Doc.getString("email"); 
			 String phone_number = Doc.getInteger("phone_number",0)+"";
			 Date hire_date = changeToSqlDate(Doc.getString("hire_date"));	 
			 int job_id  =  Doc.getInteger("job_id", 0);
			 int manager_id = Doc.getInteger("manager_id", 0);
			 int salary  =   Doc.getInteger("salary", 0);
			 String commission_pct  = Doc.getInteger("commission_pct",0)+""; 
			 int department_id=  Doc.getInteger("department_id", 0);
			 listOfISqlTableDTO.add(new EmployeesSqlTableDTO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		return listOfISqlTableDTO.toArray(data);
	}
    
    
    private ISqlTableDTO[] readLocationsCollection(){
    	Set<ISqlTableDTO> listOfISqlTableDTO = new HashSet<>();
		try {
			this.mongoImportFromCsv(TABLE_LOCATIONS, TABLE_LOCATIONS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_LOCATIONS);
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();
		
		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			 int location_id  = Doc.getInteger("location_id", 0);
			 String street_address =  Doc.getString("street_address"); 
			 int postal_code = Doc.getInteger("postal_code", 0);
			 String city =  Doc.getString("city"); 
			 String state_province =  Doc.getString("state_province"); 
			 int country_id = Doc.getInteger("country_id", 0);
			
		     listOfISqlTableDTO.add(new LocationsSqlTableDTO(location_id, street_address, postal_code, city, state_province, country_id));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		return listOfISqlTableDTO.toArray(data);
	}

    
    private ISqlTableDTO[] readCountriesCollection(){
    	Set<ISqlTableDTO> listOfISqlTableDTO = new HashSet<>();
		try {
			this.mongoImportFromCsv(TABLE_COUNTRIES, TABLE_COUNTRIES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_COUNTRIES);
		
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
		return listOfISqlTableDTO.toArray(data);
	}
    
    
    private ISqlTableDTO[] readRegionsCollection(){
    	Set<ISqlTableDTO> listOfISqlTableDTO = new HashSet<>();
		try {
			this.mongoImportFromCsv(TABLE_REGIONS, TABLE_REGIONS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_REGIONS);
		
		FindIterable<Document> cursor = mongoCollection.find();
		Iterator<Document> iterator = cursor.iterator();

		while (iterator.hasNext()) {
			Document Doc = iterator.next();
			long region_id = Doc.getInteger("region_id", 0);
			String region_name= Doc.getString("region_name");
			
		    listOfISqlTableDTO.add(new RegionsSqlTableDTO(region_id, region_name));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		return listOfISqlTableDTO.toArray(data);
	}
	
    private ISqlTableDTO[] readJobHistoryCollection(){
    	Set<ISqlTableDTO> listOfISqlTableDTO = new HashSet<>();
		try {
			this.mongoImportFromCsv(TABLE_JOB_HISTORY, TABLE_JOB_HISTORY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_JOB_HISTORY);
		
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
		return listOfISqlTableDTO.toArray(data);
	}
    
   
	private ISqlTableDTO[] readJobsCollection(){
		Set<ISqlTableDTO> listOfISqlTableDTO = new HashSet<>();
		try {
			this.mongoImportFromCsv(TABLE_JOBS, TABLE_JOBS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_JOBS);
		
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
		return listOfISqlTableDTO.toArray(data);
	}
	
    
    private ISqlTableDTO[] readDepartmentsCollection(){
    	Set<ISqlTableDTO> listOfISqlTableDTO = new HashSet<>();
		try {
			this.mongoImportFromCsv(TABLE_DEPARTMENTS, TABLE_DEPARTMENTS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(TABLE_DEPARTMENTS);
		
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
		return listOfISqlTableDTO.toArray(data);
	}
    
    private Date changeToSqlDate(String string) {
		try {
	    	if (!"null".equalsIgnoreCase(string)) {
	    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	    java.util.Date parsed = format.parse(string);    
	    	    java.sql.Date sql = new java.sql.Date(parsed.getTime());
	            return sql;
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
		}	
	    return null;
	}

}
