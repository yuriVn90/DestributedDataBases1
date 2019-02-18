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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return job_id + ","+job_title + ","+min_salary + "," + max_salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((job_id == null) ? 0 : job_id.hashCode());
		result = prime * result + ((job_title == null) ? 0 : job_title.hashCode());
		result = prime * result + max_salary;
		result = prime * result + min_salary;
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
		JobsSqlTableDTO other = (JobsSqlTableDTO) obj;
		if (job_id == null) {
			if (other.job_id != null)
				return false;
		} else if (!job_id.equals(other.job_id))
			return false;
		if (job_title == null) {
			if (other.job_title != null)
				return false;
		} else if (!job_title.equals(other.job_title))
			return false;
		if (max_salary != other.max_salary)
			return false;
		if (min_salary != other.min_salary)
			return false;
		return true;
	}
	
	
	
}
