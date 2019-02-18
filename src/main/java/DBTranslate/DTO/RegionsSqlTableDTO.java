package DBTranslate.DTO;


public class RegionsSqlTableDTO implements ISqlTableDTO {
	
	private Long region_id;
	private String region_name;
	
	public RegionsSqlTableDTO() {
		
	}
	
	public RegionsSqlTableDTO(Long region_id, String region_name) {
		this.region_id = region_id;
		this.region_name = region_name;
	}
	
	/**
	 * @param region_id
	 */
	public void setRegion_id(Long region_id) {
		this.region_id = region_id;
	}
	
	/**
	 * @return region_id
	 */
	public Long getRegion_id() {
		return this.region_id;
	}
	
	/**
	 * @param region_name
	 */
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	
	/**
	 * @return region_name
	 */
	public String getRegion_name() {
		return this.region_name;
	}

	@Override
	public String getStringToCSV() {
		// TODO Auto-generated method stub
		return region_id+","+region_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((region_id == null) ? 0 : region_id.hashCode());
		result = prime * result + ((region_name == null) ? 0 : region_name.hashCode());
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
		RegionsSqlTableDTO other = (RegionsSqlTableDTO) obj;
		if (region_id == null) {
			if (other.region_id != null)
				return false;
		} else if (!region_id.equals(other.region_id))
			return false;
		if (region_name == null) {
			if (other.region_name != null)
				return false;
		} else if (!region_name.equals(other.region_name))
			return false;
		return true;
	}
	
	

}
