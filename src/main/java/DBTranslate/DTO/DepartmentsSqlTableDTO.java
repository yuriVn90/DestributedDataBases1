package DBTranslate.DTO;

public class DepartmentsSqlTableDTO implements ISqlTableDTO {

	private Long department_id;
	private String department_name;
	private Long manager_id;
	private Long location_id;
	
	public DepartmentsSqlTableDTO() {
		
	}

	public DepartmentsSqlTableDTO(Long department_id, String department_name, Long manager_id, Long location_id) {
		this.department_id = department_id;
		this.department_name = department_name;
		this.manager_id = manager_id;
		this.location_id = location_id;
	}

	/**
	 * 
	 * @return department_id
	 */
	public Long getDepartment_id() {
		return department_id;
	}

	/**
	 * 
	 * @param department_id
	 */
	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}

	/**
	 * 
	 * @return department_name
	 */
	public String getDepartment_name() {
		return department_name;
	}

	/**
	 * 
	 * @param department_name
	 */
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	/**
	 * 
	 * @return manager_id
	 */
	public Long getManager_id() {
		return manager_id;
	}

	/**
	 * 
	 * @param manager_id
	 */
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	/**
	 * 
	 * @return location_id
	 */
	public Long getLocation_id() {
		return location_id;
	}

	/**
	 * 
	 * @param location_id
	 */
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	
	
	
	
}
