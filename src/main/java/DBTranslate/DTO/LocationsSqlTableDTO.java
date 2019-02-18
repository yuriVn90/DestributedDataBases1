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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + country_id;
		result = prime * result + location_id;
		result = prime * result + postal_code;
		result = prime * result + ((state_province == null) ? 0 : state_province.hashCode());
		result = prime * result + ((street_address == null) ? 0 : street_address.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationsSqlTableDTO other = (LocationsSqlTableDTO) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country_id != other.country_id)
			return false;
		if (location_id != other.location_id)
			return false;
		if (postal_code != other.postal_code)
			return false;
		if (state_province == null) {
			if (other.state_province != null)
				return false;
		} else if (!state_province.equals(other.state_province))
			return false;
		if (street_address == null) {
			if (other.street_address != null)
				return false;
		} else if (!street_address.equals(other.street_address))
			return false;
		return true;
	}
	
	
	
	

}
