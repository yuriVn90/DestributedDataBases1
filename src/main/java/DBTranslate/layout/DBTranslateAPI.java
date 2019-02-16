package DBTranslate.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import DBTranslate.DTO.ISqlTableDTO;
import DBTranslate.logic.TablesService;

@RestController
public class DBTranslateAPI {
	
	private TablesService tables;
	
	@Autowired
	public void setTables(TablesService tables) {
		this.tables = tables;
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getSQLTable/{tableName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:64756")
	public ISqlTableDTO[] getMySqlTable (@PathVariable("tableName") String tableName) {
		return this.tables.getMySqlTable(tableName);
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getMongoCollection/{collectionName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:61477")
	public ISqlTableDTO[] getMongoDbCollection (@PathVariable("collectionName") String collectionName) {
		return this.tables.getMongoDbCollection(collectionName);
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getSumSalaries",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:61477")
	public int getSumOfSalaries () {
		return this.tables.getSumOfSalaries();
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getNumberOfEmployeesFromCountry/{countryName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:61477")
	public int getNumbersOfEmployeesFromCountry (@PathVariable("countryName") String countryName) {
		return this.tables.getNumbersOfEmployeesFromCountry(countryName);
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/getEmailsOfJobTitle/{jobTitle}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://127.0.0.1:61477")
	public String[] getEmailsOfJobTitle (@PathVariable("jobTitle") String jobTitle) {
		return this.tables.getEmailsOfJobTitle(jobTitle);
	}
	
	
	
	
	
}
