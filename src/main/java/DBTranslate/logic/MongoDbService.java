package DBTranslate.logic;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import DBTranslate.DTO.ISqlTableDTO;

@Service
public class MongoDbService implements IMongoDbService {
	
	private ICsvService csv;
	private IMySqlService mySql;
	
	public MongoDbService() {
		
	}
	
	@PostConstruct
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSumOfSalaries() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumbersOfEmployeesFromCountry(String countryName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getEmailsOfJobTitle(String jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

}
