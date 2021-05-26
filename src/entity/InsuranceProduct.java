package entity;

import type.InsuranceProductType;

public class InsuranceProduct implements Cloneable {

	private int insuranceProductNum;
	private String productName;
	protected int basicInsurancePremium;
	private int insuranceMoney;
	private InsuranceProductType insuranceProductType;
	private int paymentCycle;
	private int paymentPeriod;
	private boolean approval;

	public int getInsuranceProductNum() {
		return insuranceProductNum;
	}

	public void setInsuranceProductNum(int insuranceProductNum) {
		this.insuranceProductNum = insuranceProductNum;
	}

	public InsuranceProduct() {
		this.approval = false;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getBasicInsurancePremium() {
		return basicInsurancePremium;
	}

	public void setBasicInsurancePremium(int basicInsurancePremium) {
		this.basicInsurancePremium = basicInsurancePremium;
	}

	public int getInsuranceMoney() {
		return insuranceMoney;
	}

	public void setInsuranceMoney(int insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public InsuranceProductType getInsuranceProductType() {
		return insuranceProductType;
	}

	public void setInsuranceProductType(
			InsuranceProductType insuranceProductType) {
		this.insuranceProductType = insuranceProductType;
	}

	public int getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(int paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public int getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(int paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public double calculationRate(Client client) {
		return basicInsurancePremium;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public void RegisterClient() {

	}

	public InsuranceProduct clone() {
		try {
			return (InsuranceProduct) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getApproval() { // jdbc
		return this.approval ? 1 : 0;
	}

}