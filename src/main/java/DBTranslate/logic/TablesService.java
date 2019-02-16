package DBTranslate.logic;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import DBTranslate.DTO.ISqlTableDTO;

@Service
public class TablesService implements ITablesService {
	
//	private PutOnCSV export;
	private SqlTableLogic mySql;
	
	public TablesService() {
		
	}
	
	@PostConstruct
	public void init() {
		try {
//			this.export = new PutOnCSV();
			this.mySql = new SqlTableLogic();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ISqlTableDTO[] getMySqlTable(String tableName) {
		return mySql.readCountriesTable();
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
