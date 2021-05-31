package entity;

import java.util.Calendar;
import java.util.Date;

public class Contract {

	private Client client;
	private Date insuranceContractDate;
	private Date insuranceExpiryDate;
	private InsuranceProduct insuranceProduct;
	private Manager salesPerson;
	private boolean[] month;
	private boolean approval;

	public Contract() {
		this.approval = false;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public void setInsuranceExpiryDate(int paymentPeriod) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(this.insuranceContractDate);
		ca.add(Calendar.YEAR, paymentPeriod);
		this.insuranceExpiryDate = ca.getTime();
	}

	public InsuranceProduct getInsuranceProduct() {
		return insuranceProduct;
	}

	public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	}

	public Manager getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(Manager salesPerson) {
		this.salesPerson = salesPerson;
	}

	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public boolean[] getMonth() {
		return month;
	}

	public void setMonth(boolean[] month) {
		this.month = month;
	}

}