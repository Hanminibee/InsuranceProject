package type;
import entity.*;
public enum InsuranceProductType {
	ACTUALEXPENSE("�Ǻ���", new ActualExpense()), CANCER("�Ϻ���", new Cancer()), 
	PENSION("���ݺ���", new Pension()), LIFE("���ź���",new Life());

	private String insuranceName;
	private InsuranceProduct insuranceProduct;

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
}