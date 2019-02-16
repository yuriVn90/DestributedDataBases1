package DBTranslate.logic;


import DBTranslate.DTO.ISqlTableDTO;

public interface TablesService {
	
	/**
	 * @param tableName
	 * @return array of Tables' data
	 */
	public ISqlTableDTO[] getMySqlTable(String tableName);
	
	/**
	 * @param collectionName
	 * @return array of collections' documents
	 */
	public ISqlTableDTO[] getMongoDbCollection(String collectionName);
	
	/**
	 * 
	 * @return sum of all employees' salaries
	 */
	public int getSumOfSalaries();
	
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
