package DBTranslate.logic;


import java.sql.SQLException;

import DBTranslate.DTO.ISqlTableDTO;

public interface IMySqlService {
	
	/**
	 * @param tableName
	 * @return array of Tables' data
	 * @throws SQLException 
	 */
	public ISqlTableDTO[] getMySqlTable(String tableName);
	
	/**
	 * export mySQL table to CSV file
	 * @param tableName
	 * @param fileName
	 */
	public void exportTableToCSV(String tableName, String fileName);

}
