package DBTranslate.logic;

import org.springframework.stereotype.Service;

import DBTranslate.DTO.ISqlTableDTO;

@Service
public class MongoDbService implements IMongoDbService {
	
	public MongoDbService() {
		
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
