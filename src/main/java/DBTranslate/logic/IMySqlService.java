package DBTranslate.logic;


import DBTranslate.DTO.ISqlTableDTO;

public interface IMySqlService {
	
	/**
	 * @param tableName
	 * @return array of Tables' data
	 */
	public ISqlTableDTO[] getMySqlTable(String tableName);		

}
