package entity;

import java.util.Calendar;
import java.util.Date;

public class Contract {
	
	private Client client;
	private Date insuranceContractDate;
	private Date insuranceExpiryDate;
	private InsuranceProduct insuranceProduct;
	private SalesPerson salesPerson;
	private boolean approval;
	
	
	
	public Contract() {
		this.approval = false;
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
	public void setInsuranceExpiryDate(int paymentPeriod) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(this.insuranceContractDate);
		ca.add(Calendar.YEAR, paymentPeriod);
		this.insuranceExpiryDate = ca.getTime()  ;
	}
	

	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public InsuranceProduct getInsuranceProduct() {
		return insuranceProduct;
	}

	public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	}

	public SalesPerson getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(SalesPerson salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	public String Id() {
		// TODO Auto-generated method stub
		return client.getId();
	}



}