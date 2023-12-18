package testdata;

import io.restassured.response.Response;

public class GetDataPayload {
	private String accountno ;
	private String departmentno ;
	private String salary ;
	private String pincode;
	private String id;
	private String userid;
	
	
	public   GetDataPayload(String sAccountNo,String sDeptNo,String sSalary,String sPincode,String sId,String sUserId) {
		
		this.accountno=sAccountNo;
		this.departmentno = sDeptNo;
		this.salary = sSalary;
		this.pincode = sPincode;
		this.id=sId;
		this.userid = sUserId;
		
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
	public String getId() {
		return id;
	}
	public String getUserId() {
		return userid;
	}
}
