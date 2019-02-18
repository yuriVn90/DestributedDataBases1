package DBTranslate.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import DBTranslate.DTO.ISqlTableDTO;
import DBTranslate.logic.IMongoDbService;
import DBTranslate.logic.IMySqlService;

@RestController
public class DBTranslateAPI {
	
	private IMySqlService mySql;
	private IMongoDbService mongo;
	
	@Autowired
	public void setTables(IMySqlService mySql, IMongoDbService mongo) {
		this.mySql = mySql;
		this.mongo = mongo;
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getSQLTable/{tableName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:54925")
	public ISqlTableDTO[] getMySqlTable (@PathVariable("tableName") String tableName) {
		return this.mySql.getMySqlTable(tableName);
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getMongoCollection/{collectionName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:54925")
	public ISqlTableDTO[] getMongoDbCollection (@PathVariable("collectionName") String collectionName) {
		return this.mongo.getMongoDbCollection(collectionName);
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getMaxSalary",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:54925")
	public int getSumOfSalaries () {
		return this.mongo.getMaxSalary();
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getNumberOfEmployeesFromCountry/{countryName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:54925")
	public int getNumbersOfEmployeesFromCountry (@PathVariable("countryName") String countryName) {
		return this.mongo.getNumbersOfEmployeesFromCountry(countryName);
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getEmailsOfJobTitle/{jobTitle}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:54925")
	public String[] getEmailsOfJobTitle (@PathVariable("jobTitle") String jobTitle) {
		return this.mongo.getEmailsOfJobTitle(jobTitle);
	}
	
}
