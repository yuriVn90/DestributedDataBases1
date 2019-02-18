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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + department_id;
		result = prime * result + ((employee_id == null) ? 0 : employee_id.hashCode());
		result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + job_id;
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
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
		JobHistorySqlTableDTO other = (JobHistorySqlTableDTO) obj;
		if (department_id != other.department_id)
			return false;
		if (employee_id == null) {
			if (other.employee_id != null)
				return false;
		} else if (!employee_id.equals(other.employee_id))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (job_id != other.job_id)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}
	
	
	
	
	
	
}
