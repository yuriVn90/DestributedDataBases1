 package DBTranslate.logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DBTranslate.DTO.ISqlTableDTO;


public class MongoTableLogic {
	
	private MongoClient mongoClient;
	private String mongodbName;
	private MongoDatabase mongoDatabase;
	
	public MongoTableLogic() {
		this.mongoClient = new MongoClient();
		this.mongodbName = "example";
		this.mongoDatabase = mongoClient.getDatabase(mongodbName);
	}
	
	


}
