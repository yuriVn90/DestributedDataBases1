package DBTranslate.DTO;

import java.sql.Date;

public class EmployeesSqlTableDTO implements ISqlTableDTO {

	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private int job_id;
	private int salary;
	private String commission_pct;
	private int manager_id;
	private int department_id;
	
	public EmployeesSqlTableDTO() {
		
	}

	public EmployeesSqlTableDTO(int employee_id, String first_name, String last_name, String email,
			String phone_number, Date hire_date, int job_id, int salary, String commission_pct, int manager_id,
			int department_id) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.hire_date = hire_date;
		this.job_id = job_id;
		this.salary = salary;
		this.commission_pct = commission_pct;
		this.manager_id = manager_id;
		this.department_id = department_id;
	}

	/**
	 * 
	 * @return employee_id
	 */
	public int getEmployee_id() {
		return employee_id;
	}

	/**
	 * 
	 * @param employee_id
	 */
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	/**
	 * 
	 * @return first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * 
	 * @param first_name
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * 
	 * @return last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * 
	 * @param last_name
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * 
	 * @param phone_number
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	/**
	 * 
	 * @return hire_date
	 */
	public Date getHire_date() {
		return hire_date;
	}

	/**
	 * 
	 * @param hire_date
	 */
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	/**
	 * 
	 * @return job_id
	 */
	public int getJob_id() {
		return job_id;
	}

	/**
	 * 
	 * @param job_id
	 */
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	/**
	 * 
	 * @return salary
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * 
	 * @param salary
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * 
	 * @return commission_pct
	 */
	public String getCommission_pct() {
		return commission_pct;
	}

	/**
	 * 
	 * @param commission_pct
	 */
	public void setCommission_pct(String commission_pct) {
		this.commission_pct = commission_pct;
	}

	/**
	 * 
	 * @return manager_id
	 */
	public int getManager_id() {
		return manager_id;
	}

	/**
	 * 
	 * @param manager_id
	 */
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	/**
	 * 
	 * @return department_id
	 */
	public int getDepartment_id() {
		return department_id;
	}

	/**
	 * 
	 * @param department_id
	 */
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	@Override
	public String getStringToCSV() {
		// TODO Auto-generated method stub
		return employee_id + ","+first_name + ","+last_name + ","+email + ","+phone_number + ","+hire_date + ","+job_id + "," +salary + ","+commission_pct + ","+manager_id + ","+department_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commission_pct == null) ? 0 : commission_pct.hashCode());
		result = prime * result + department_id;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employee_id;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((hire_date == null) ? 0 : hire_date.hashCode());
		result = prime * result + job_id;
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + manager_id;
		result = prime * result + ((phone_number == null) ? 0 : phone_number.hashCode());
		result = prime * result + salary;
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
		EmployeesSqlTableDTO other = (EmployeesSqlTableDTO) obj;
		if (commission_pct == null) {
			if (other.commission_pct != null)
				return false;
		} else if (!commission_pct.equals(other.commission_pct))
			return false;
		if (department_id != other.department_id)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (hire_date == null) {
			if (other.hire_date != null)
				return false;
		} else if (!hire_date.equals(other.hire_date))
			return false;
		if (job_id != other.job_id)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (manager_id != other.manager_id)
			return false;
		if (phone_number == null) {
			if (other.phone_number != null)
				return false;
		} else if (!phone_number.equals(other.phone_number))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}
	
	
	
	
}
