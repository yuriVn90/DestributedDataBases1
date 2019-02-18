package DBTranslate.logic;

import DBTranslate.DTO.ISqlTableDTO;

public interface IMongoDbService {
	
	/**
	 * mongoImport table from CSV into mongo collection
	 * @param tableName
	 * @param fileName
	 * @throws Exception
	 */
	public void mongoImportFromCsv(String tableName, String fileName) throws Exception;
	
	/**
	 * @param collectionName
	 * @return array of collections' documents
	 */
	public ISqlTableDTO[] getMongoDbCollection(String collectionName);
	
	/**
	 * 
	 * @return max salary
	 */
	public int getMaxSalary();
	
	/**
	 * 
	 * @param countryName
	 * @return number of employees from country
	 */
	public int getNumbersOfEmployeesFromCountry (String countryName);
	
	/**
	 * 
	 * @param jobTitle
	 * @return emails of employees with same job title
	 */
	public String[] getEmailsOfJobTitle(String jobTitle);
	
	

}
