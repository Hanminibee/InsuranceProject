package entity;

import type.InsuranceProductType;

public class Contract {
	private String clientId;
	private int insuranceContractDate;
	private String insuranceExpiryDate;
	private String nameOfSalesPerson;
	private InsuranceProductType insuranceProductType;
	private InsuranceProducts insuranceProducts;
	
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getInsuranceExpiryDate() {
		return insuranceExpiryDate;
	}
	public void setInsuranceExpiryDate(String insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
	}
	public String getNameOfSalesPerson() {
		return nameOfSalesPerson;
	}
	public void setNameOfSalesPerson(String nameOfSalesPerson) {
		this.nameOfSalesPerson = nameOfSalesPerson;
	}
	public int getInsuranceContractDate() {
		return insuranceContractDate;
	}
	public void setInsuranceContractDate(int insuranceContractDate) {
		this.insuranceContractDate = insuranceContractDate;
	}
	public InsuranceProducts getInsuranceProducts() {
		return insuranceProducts;
	}
	public void setInsuranceProducts(InsuranceProducts insuranceProducts) {
		this.insuranceProducts = insuranceProducts;
	}
	public InsuranceProductType getInsuranceProductType() {
		return insuranceProductType;
	}

	public void setInsuranceProductType(
			InsuranceProductType insuranceProductType) {
		this.insuranceProductType = insuranceProductType;
	}

	public void CancelInsuranceProducts(insuranceProduct[]) {
		
	}
	public void ContractInsuranceProducts(getInsurance[]) {
		
	}
	public void ProvideInsuranceProducts(insuranceProduct]) {
		
	}
	public Contract clone() {
		try {
			return (Contract)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
