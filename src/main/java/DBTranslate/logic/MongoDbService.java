package DBTranslate.logic;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private MongoClient mongoClient;
	private String mongodbName;
	
	private ICsvService csv;
	private IMySqlService mySql;
	
	public MongoDbService() {
		this.mongoClient = new MongoClient();
		this.mongodbName = "mongo_hr";
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
		if("EMPLOYEES".equalsIgnoreCase(collectionName))
		{
			return readEmployeesCollection();
		}
		else if("DEPARTMENTS".equalsIgnoreCase(collectionName))
		{
			return readDepartmentsCollection();
		}
		else if("LOCATIONS".equalsIgnoreCase(collectionName))
		{
			return readLocationsCollection();
		}
		else if("COUNTRIES".equalsIgnoreCase(collectionName))
		{
			return readCountriesCollection();
		}
		else if("REGIONS".equalsIgnoreCase(collectionName))
		{
			return readRegionsCollection();
		}
		else if("JOB_HISTORY".equalsIgnoreCase(collectionName))
		{
			return readJobHistoryCollection();
		}
		else if("JOBS".equalsIgnoreCase(collectionName))
		{
			return readJobsCollection();
		}
		
		return null;
	}

	@Override
	public int getSumOfSalaries() {
		try {
			this.mongoImportFromCsv("EMPLOYEES", "EMPLOYEES");
		} catch (Exception e) {
			e.printStackTrace();
		}
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("EMPLOYEES");
			AggregateIterable<Document> ag = mongoCollection.aggregate(
				      Arrays.asList(Aggregates.group("", Accumulators.sum("totalSum", "$salary"))
				      )
				);
			
			Document first = ag.first();
			int sum = first.getInteger("totalSum");
//			int sum = 0;
//			 for (Document doc : ag) {
//			        int value = doc.getInteger("totalSum");
//			        sum = sum + value;
//			    }
			 System.out.println(sum);
			 return sum;
	}

	@Override
	public int getNumbersOfEmployeesFromCountry(String countryName) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
		MongoCollection<Document> mongoCollectionFirst = mongoDatabase.getCollection("COUNTRIES");
		MongoCollection<Document> mongoCollectionSecond = mongoDatabase.getCollection("LOCATIONS");
		MongoCollection<Document> mongoCollectionThird = mongoDatabase.getCollection("DEPARTMENTS");
		MongoCollection<Document> mongoCollectionFourth = mongoDatabase.getCollection("EMPLOYEES");
		
		AggregateIterable<Document> ag = mongoCollectionFirst.aggregate(Arrays.asList(
                Aggregates.match(eq("country_name", countryName)),
                Aggregates.group("$country_id")));
		
		ArrayList<String> listString = new ArrayList<String>();
	    for (Document doc : ag) {
	        String id = doc.toString();
	        listString.add(id);
	    }
	    
	    AggregateIterable<Document> ag1 = null;
	    ag1 = doAggregatDocForMatch(ag1, listString, mongoCollectionSecond, "country_id", "$Location_id");
	    listString.clear();
	    
	    for (Document doc : ag1) {
	        String id = doc.toString();
	        listString.add(id);
	    }
	    
	    
	    AggregateIterable<Document> ag2 = null;
	    ag2 = doAggregatDocForMatch(ag2,listString,mongoCollectionThird,"location_id","$department_id");
	    listString.clear();
	    for (Document doc : ag1) {
	        String id = doc.toString();
	        listString.add(id);
	    }
	    
	    AggregateIterable<Document> ag3 = null;
	    for (int i = 0 ;i<listString.size();i++) {
	    	 ag1 = mongoCollectionFourth.aggregate(Arrays.asList(
	                 Aggregates.match(eq("department_id", listString.get(i))),
	                 Aggregates.group(Accumulators.sum("count", 1))));
	    }
	    
	    int numOfEmployess = 0;
		 for (Document doc : ag3) {
		        int value = doc.getInteger("count", 0);
		        numOfEmployess = numOfEmployess +value;
		    }
	    
	    
	    
		return numOfEmployess;
	}

	

	private AggregateIterable<Document> doAggregatDocForMatch(AggregateIterable<Document> ag, ArrayList<String> listString,
			MongoCollection<Document> mongoCollection,String nameMatch, String nameGroup) {
		  for (int i = 0 ;i<listString.size();i++) {
		    	 ag = mongoCollection.aggregate(Arrays.asList(
		                 Aggregates.match(eq(nameMatch, listString.get(i))),
		                 Aggregates.group(nameGroup)));
		    }
		return null;
	}

	@Override
	public String[] getEmailsOfJobTitle(String jobTitle) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
		MongoCollection<Document> mongoCollectionFirst = mongoDatabase.getCollection("JOBS");
		MongoCollection<Document> mongoCollectionSecond = mongoDatabase.getCollection("EMPLOYEES");
		AggregateIterable<Document> ag = mongoCollectionFirst.aggregate(Arrays.asList(
                Aggregates.match(eq("jobTitle", jobTitle)),
                Aggregates.group("$employee_id")));
		
		
	    ArrayList<String> listString = new ArrayList<String>();
	    for (Document doc : ag) {
	        String id = doc.toString();
	        listString.add(id);
	    }
	    AggregateIterable<Document> ag1 = null;
	    for (int i = 0 ;i<listString.size();i++) {
	    	 ag1 = mongoCollectionSecond.aggregate(Arrays.asList(
	                 Aggregates.match(eq("job_id", listString.get(i))),
	                 Aggregates.group("$email")));
	    }
	    
	    String [] allEmails = new String [listString.size()];
	    int index = 0;
	    for (Document doc : ag1) {
	        String email = doc.toString();
	        allEmails[index]= email;
	        index++;
	    }
	    
		return allEmails;
	}
	
	private ISqlTableDTO[] readEmployeesCollection(){
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		try {
			this.mongoImportFromCsv("EMPLOYEES", "EMPLOYEES");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//			 int manager_id = Doc.getInteger("manager_id", 0);
			 int salary  =   Doc.getInteger("salary", 0);
			 String commission_pct  = Doc.getInteger("commission_pct",0)+""; 
			 long department_id=  Doc.getInteger("department_id", 0);
			 listOfISqlTableDTO.add(new EmployeesSqlTableDTO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, null, department_id));
		}
		
		ISqlTableDTO[] data = new ISqlTableDTO[listOfISqlTableDTO.size()];
		data = listOfISqlTableDTO.toArray(data);
		
	
		
		return data;
	}
    
    
    private ISqlTableDTO[] readLocationsCollection(){
		ArrayList<ISqlTableDTO> listOfISqlTableDTO = new ArrayList<>();
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
		try {
			this.mongoImportFromCsv("LOCATIONS", "LOCATIONS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
		try {
			this.mongoImportFromCsv("COUNTRIES", "COUNTRIES");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
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
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
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
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
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
		MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbName);
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

	private Bson eq(String string, String jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

}
