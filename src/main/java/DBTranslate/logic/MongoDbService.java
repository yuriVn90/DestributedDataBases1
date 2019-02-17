package DBTranslate.logic;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import com.sun.org.apache.xml.internal.security.encryption.AgreementMethod;

import DBTranslate.DTO.ISqlTableDTO;
import antlr.collections.List;

@Service
public class MongoDbService implements IMongoDbService {
	private MongoClient mongoClient;
	private String mongodbName;
	private MongoDatabase mongoDatabase;
	
	public MongoDbService() {
		this.mongoClient = new MongoClient();
		this.mongodbName = "example";
		this.mongoDatabase = mongoClient.getDatabase(mongodbName);
	}
	
	@Override
	public void mongoImportFromCsv(String tableName, String fileName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISqlTableDTO[] getMongoDbCollection(String collectionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSumOfSalaries() {
			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("EMPLOYEES");
			AggregateIterable<Document> ag = mongoCollection.aggregate(
				      Arrays.asList(Aggregates.group("$salary", Accumulators.sum("count", 1))
				      )
				);
			int sum = 0;
			 for (Document doc : ag) {
			        int value = doc.getInteger("salary", 0);
			        sum = sum +value;
			    }
			 return sum;
	}

	@Override
	public int getNumbersOfEmployeesFromCountry(String countryName) {
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

	private Bson eq(String string, String jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

}
