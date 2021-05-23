package entity;

import type.InsuranceProductType;

public class InsuranceProduct implements Cloneable{

	private String productName; //상품명
	protected int basicInsurancePremium; //기본보험료
	private int insuranceMoney; //보상금
	private InsuranceProductType insuranceProductType; //보험타입
	private int paymentCycle; //납입주기
	private int paymentPeriod; //납입기간
	private boolean approval; //승인여부

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
			return (InsuranceProduct)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}