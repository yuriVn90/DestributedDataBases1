package DBTranslate.DTO;

import java.sql.Date;

public class JobHistorySqlTableDTO implements ISqlTableDTO {

	private Long employee_id;
	private Date start_date;
	private int min_salary;
	private int max_salary;
	
	
	public JobHistorySqlTableDTO() {
		
	}

	public JobHistorySqlTableDTO(Long employee_id, Date start_date, int min_salary, int max_salary) {
		this.employee_id = employee_id;
		this.start_date = start_date;
		this.min_salary = min_salary;
		this.max_salary = max_salary;
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
	 * @param start_date
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return min_salary
	 */
	public int getMin_salary() {
		return min_salary;
	}

	/**
	 * @param min_salary
	 */
	public void setMin_salary(int min_salary) {
		this.min_salary = min_salary;
	}

	/**
	 * @return max_salary
	 */
	public int getMax_salary() {
		return max_salary;
	}

	/**
	 * @param max_salary
	 */
	public void setMax_salary(int max_salary) {
		this.max_salary = max_salary;
	}
	
	
	
	
	
	
}
