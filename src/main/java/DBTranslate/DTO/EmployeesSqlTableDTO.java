package DBTranslate.DTO;

import java.sql.Date;

public class EmployeesSqlTableDTO implements ISqlTableDTO {

	private Long employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private Long job_id;
	private int salary;
	private String commission_pct;
	private Long manager_id;
	private Long department_id;
	
	public EmployeesSqlTableDTO() {
		
	}

	public EmployeesSqlTableDTO(Long employee_id, String first_name, String last_name, String email,
			String phone_number, Date hire_date, Long job_id, int salary, String commission_pct, Long manager_id,
			Long department_id) {
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
	public Long getEmployee_id() {
		return employee_id;
	}

	/**
	 * 
	 * @param employee_id
	 */
	public void setEmployee_id(Long employee_id) {
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
	public Long getJob_id() {
		return job_id;
	}

	/**
	 * 
	 * @param job_id
	 */
	public void setJob_id(Long job_id) {
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
	
	
	
	
}
