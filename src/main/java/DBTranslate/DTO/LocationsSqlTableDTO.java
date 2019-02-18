package DBTranslate.DTO;

public class LocationsSqlTableDTO implements ISqlTableDTO {
	
	private int location_id;
	private String street_address;
	private int  postal_code;
	private String city; 
	private String state_province;
	private int  country_id;
	
	
	public LocationsSqlTableDTO() {
		
	}


	public LocationsSqlTableDTO(int location_id, String street_address, int postal_code, String city,
			String state_province, int country_id) {
		super();
		this.location_id = location_id;
		this.street_address = street_address;
		this.postal_code = postal_code;
		this.city = city;
		this.state_province = state_province;
		this.country_id = country_id;
	}

	/**
	 * 
	 * @return location_id
	 */
	public int getLocation_id() {
		return location_id;
	}

	/**
	 * 
	 * @param location_id
	 */
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	/**
	 * 
	 * @return street_address
	 */
	public String getStreet_address() {
		return street_address;
	}

	/**
	 * 
	 * @param street_address
	 */
	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	/**
	 * 
	 * @return postal_code
	 */
	public int getPostal_code() {
		return postal_code;
	}

	/**
	 * 
	 * @param postal_code
	 */
	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	/**
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return state_province
	 */
	public String getState_province() {
		return state_province;
	}

	/**
	 * 
	 * @param state_province
	 */
	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	/**
	 * 
	 * @return	country_id
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


	@Override
	public String getStringToCSV() {
		// TODO Auto-generated method stub
		return location_id+","+street_address
				+","+postal_code
				+","+city
				+","+state_province
				+","+country_id;
	}
	
	
	
	

}
