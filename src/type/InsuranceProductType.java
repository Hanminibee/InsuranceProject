package type;

import entity.*;


public enum InsuranceProductType {
	ACTUALEXPENSE("실비보험", new ActualExpense()), CANCER("암보험", new Cancer()), 
	PENSION("연금보험", new Pension()), LIFE("종신보험",new Life());

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
