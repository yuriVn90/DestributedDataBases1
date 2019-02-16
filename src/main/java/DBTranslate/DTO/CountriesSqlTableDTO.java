package DBTranslate.DTO;

public class CountriesSqlTableDTO implements ISqlTableDTO {
	
	private int country_id;
	private String country_name ;
	private int region_id;
	
	
	
	public CountriesSqlTableDTO() {
		
	}

	public CountriesSqlTableDTO(int country_id, String country_name, int region_id) {
		this.country_id = country_id;
		this.country_name = country_name;
		this.region_id = region_id;
	}

	/**
	 * 
	 * @return country_id
	 */
	public int getCountry_id() {
		return country_id;
	}

	/**
	 * 
	 * @param country_id
	 */
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	/**
	 * 
	 * @return country_name
	 */
	public String getCountry_name() {
		return country_name;
	}

	/**
	 * 
	 * @param country_name
	 */
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	/**
	 * 
	 * @return region_id
	 */
	public int getRegion_id() {
		return region_id;
	}

	/**
	 * 
	 * @param region_id
	 */
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}

	@Override
	public String getStringToCSV() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
