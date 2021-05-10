package entity;

import java.util.Date;

public class Contract {
	
	private String clientID;
	private Date insuranceContractDate;
	private Date insuranceExpiryDate;
	private String productName;
	private String NameOfSalesPerson;
	private boolean approval;
	
	public Contract() {
		this.approval = false;
	}
	
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public Date getInsuranceContractDate() {
		return insuranceContractDate;
	}
	public void setInsuranceContractDate(Date insuranceContractDate) {
		this.insuranceContractDate = insuranceContractDate;
	}
	public Date getInsuranceExpiryDate() {
		return insuranceExpiryDate;
	}
	public void setInsuranceExpiryDate(Date insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getNameOfSalesPerson() {
		return NameOfSalesPerson;
	}
	public void setNameOfSalesPerson(String nameOfSalesPerson) {
		NameOfSalesPerson = nameOfSalesPerson;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}

}
