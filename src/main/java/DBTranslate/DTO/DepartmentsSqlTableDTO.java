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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return department_id+","+department_name+","+manager_id+","+location_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department_id == null) ? 0 : department_id.hashCode());
		result = prime * result + ((department_name == null) ? 0 : department_name.hashCode());
		result = prime * result + ((location_id == null) ? 0 : location_id.hashCode());
		result = prime * result + ((manager_id == null) ? 0 : manager_id.hashCode());
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
		DepartmentsSqlTableDTO other = (DepartmentsSqlTableDTO) obj;
		if (department_id == null) {
			if (other.department_id != null)
				return false;
		} else if (!department_id.equals(other.department_id))
			return false;
		if (department_name == null) {
			if (other.department_name != null)
				return false;
		} else if (!department_name.equals(other.department_name))
			return false;
		if (location_id == null) {
			if (other.location_id != null)
				return false;
		} else if (!location_id.equals(other.location_id))
			return false;
		if (manager_id == null) {
			if (other.manager_id != null)
				return false;
		} else if (!manager_id.equals(other.manager_id))
			return false;
		return true;
	}
	
	
	
	
}
