package DBTranslate.DTO;

import java.sql.Date;

public class JobHistorySqlTableDTO implements ISqlTableDTO {

	private Long employee_id;
	private Date start_date;
	private Date  end_date ;
	private int job_id;
	private int department_id;
	
	
	public JobHistorySqlTableDTO() {
		
	}

	public JobHistorySqlTableDTO(Long employee_id, Date start_date,Date  end_date,int min_salary, int max_salary) {
		this.employee_id = employee_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.job_id = min_salary;
		this.department_id = max_salary;
	}

	/** 
	 * @return employee_id
	 */
	public Long getEmployee_id() {
		return employee_id;
	}

	/**
	 * @param employee_id
	 */
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	/**
	 * @return start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	
	/**
	 * @return end_date
	 */
	
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	/**
	 * @return end_date
	 */
	
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @param start_date
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return job_id
	 */
	public int getJob_id() {
		return job_id;
	}

	/**
	 * @param job_id
	 */
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	/**
	 * @return department_id
	 */
	public int getDepartment_id() {
		return department_id;
	}

	/**
	 * @param department_id
	 */
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	@Override
	public String getStringToCSV() {
		// TODO Auto-generated method stub
		return employee_id +","+start_date +","+end_date +","+job_id +","+department_id;
	}
	
	
	
	
	
	
}
