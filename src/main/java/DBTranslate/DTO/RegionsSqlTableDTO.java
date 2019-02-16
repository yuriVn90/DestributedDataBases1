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
		return null;
	}

}
