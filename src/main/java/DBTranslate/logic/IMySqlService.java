package DBTranslate.logic;


import DBTranslate.DTO.ISqlTableDTO;

public interface IMySqlService {
	
	/**
	 * @param tableName
	 * @return array of Tables' data
	 */
	public ISqlTableDTO[] getMySqlTable(String tableName);
	
	/**
	 * export mySQL table to CSV file
	 * @param tableName
	 * @param fileName
	 * @param tableData
	 */
	public void exportTableToCSV(String tableName, String fileName, ISqlTableDTO[] tableData);

}
