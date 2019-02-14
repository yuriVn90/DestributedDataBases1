package DBTranslate.DTO;

public class JobsSqlTableDTO implements ISqlTableDTO {

	private Long job_id;
	private String job_title;
	private int min_salary;
	private int max_salary;
	
	public JobsSqlTableDTO() {
		
	}

	public JobsSqlTableDTO(Long job_id, String job_title, int min_salaty, int max_salary) {
		super();
		this.job_id = job_id;
		this.job_title = job_title;
		this.min_salary = min_salaty;
		this.max_salary = max_salary;
	}
	
	/**
	 * @return job_id
	 */
	public Long getJob_id() {
		return job_id;
	}

	/**
	 * @param job_id
	 */
	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}
	
	/**
	 * @return job_title
	 */
	public String getJob_title() {
		return job_title;
	}

	/**
	 * @param job_title
	 */
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	/**
	 * @return min_salary
	 */
	public int getMin_salary() {
		return min_salary;
	}

	/**
	 * @param min_salaty
	 */
	public void setMin_salary(int min_salaty) {
		this.min_salary = min_salaty;
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
