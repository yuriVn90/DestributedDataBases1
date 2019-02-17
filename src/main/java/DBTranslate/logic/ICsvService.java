package DBTranslate.logic;

import DBTranslate.DTO.ISqlTableDTO;

public interface ICsvService {
	
	/**
	 * export mySQL table to CSV file
	 * @param tableName
	 * @param fileName
	 * @param tableData
	 * @throws Exception
	 */
	public void export(String tableName, String fileName, ISqlTableDTO[] tableData) throws Exception;
	
	/**
	 * import data from CSV file to mongoDB collection
	 * @param tableName
	 * @param fileName
	 * @return
	 */
	public void importFromCsv(String tableName, String fileName);
	
	

}
