package testdata;

public class AddDataPayLoad {
	
	private String accountno ;
	private String departmentno ;
	private String salary ;
	private String pincode;
	
	
	public AddDataPayLoad(String sAccountNo,String sDeptNo,String sSalary,String sPincode) {
		
		this.accountno=sAccountNo;
		this.departmentno = sDeptNo;
		this.salary = sSalary;
		this.pincode = sPincode;
		
	}

	public String getAccountNo() {
		return accountno;
	}
	public String getDepartmentNo() {
		return departmentno;
	}
	public String getSalary() {
		return salary;
	}
	public String getPincode() {
		return pincode;
	}
}
