package fa.training.entities;

public class Employee {
	private int employeeId;
	private String employeeName;
	private double salary;
	private int spvld;

	public Employee() {
		super();
	}

	public Employee(int employeeId, String employeeName, double salary, int spvld) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.salary = salary;
		this.spvld = spvld;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getSpvld() {
		return spvld;
	}

	public void setSpvld(int spvld) {
		this.spvld = spvld;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", salary=" + salary
				+ ", spvld=" + spvld + "]";
	}

}
