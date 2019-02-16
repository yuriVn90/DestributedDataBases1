package DBTranslate.logic;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import DBTranslate.DTO.ISqlTableDTO;

@Service
public class MySqlService implements IMySqlService {
	
	private final static String TABLE_COUNTRIES = "COUNTRIES";
	private final static String TABLE_DEPARTMENTS = "DEPARTMENTS";
	private final static String TABLE_EMPLOYEES = "EMPLOYEES";
	private final static String TABLE_JOB_HISTORY = "JOB_HISTORY";
	private final static String TABLE_JOBS = "JOBS";
	private final static String TABLE_LOCATIONS = "LOCATIONS";
	private final static String TABLE_REGIONS = "REGIONS";
	
	
	//	private PutOnCSV export;
	private SqlTableLogic mySql;
	
	public MySqlService() {
		
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
		switch (tableName.toUpperCase()) {
			case TABLE_COUNTRIES: 	return this.mySql.readCountriesTable();
			case TABLE_DEPARTMENTS: return this.mySql.readDepartmentsTable();
			case TABLE_EMPLOYEES:	return this.mySql.readEmployeesTable();
			case TABLE_JOB_HISTORY:	return this.mySql.readJobHistoryTable();
			case TABLE_JOBS:		return this.mySql.readJobsTable();
			case TABLE_LOCATIONS:	return this.mySql.readLocationsTable();
			case TABLE_REGIONS:		return this.mySql.readRegionsTable();
			default:
		}
		return null;
	}

}
