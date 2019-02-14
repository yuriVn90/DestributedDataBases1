package DBTranslate.logic;

import DBTranslate.DTO.ISqlTableDTO;

public interface TablesService {
	
	/**
	 * @param collectionName
	 * @return array of collections' records
	 */
	public ISqlTableDTO[] getMongoCollection(String collectionName);
	
	/**
	 * 
	 * @param collectionName
	 * @param id
	 * @return the deleted record
	 */
	public ISqlTableDTO deleteRecord(String collectionName, int id);
	
	/**
	 * 
	 * @param collectionName
	 * @param record
	 * @return the added record
	 */
	public ISqlTableDTO addRecord(String collectionName, ISqlTableDTO record);
	
	/**
	 * 
	 * @return employees collection sorted by name
	 */
	public ISqlTableDTO[] sortEmployeesCollectionByName();
	

}
