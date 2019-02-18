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
	public String toString() {
		return country_id + ","+country_name + ","+region_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + country_id;
		result = prime * result + ((country_name == null) ? 0 : country_name.hashCode());
		result = prime * result + region_id;
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
		CountriesSqlTableDTO other = (CountriesSqlTableDTO) obj;
		if (country_id != other.country_id)
			return false;
		if (country_name == null) {
			if (other.country_name != null)
				return false;
		} else if (!country_name.equals(other.country_name))
			return false;
		if (region_id != other.region_id)
			return false;
		return true;
	}
	
	
	
	
	
}
