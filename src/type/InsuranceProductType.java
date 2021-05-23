package type;

import entity.*;


public enum InsuranceProductType {
	ACTUALEXPENSE("�Ǻ���", new ActualExpense()), CANCER("�Ϻ���", new Cancer()), 
	PENSION("���ݺ���", new Pension()), LIFE("���ź���",new Life());

	private String insuranceName;
	private InsuranceProduct insuranceProduct;
	private Contract contract;

	InsuranceProductType(String insuranceName, InsuranceProduct insuranceProduct) {
		this.insuranceName = insuranceName;
		this.insuranceProduct = insuranceProduct;
	}

	public String getInsuranceName() {
		return insuranceName;
	}
	
	public InsuranceProduct getInsuranceProduct() {
		return this.insuranceProduct;
	}
	public Contract getContract() {
		return this.contract;
	}
}
