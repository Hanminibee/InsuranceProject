package entity;

import java.util.Date;

public class Accident {
	private int accidentNum;
	private Client client;
	private InsuranceProduct insuranceProduct;
	private String accidentDetail;
	private Date receptionDate;

	public int getAccidentNum() {
		return accidentNum;
	}
	public void setAccidentNum(int accidentNum) {
		this.accidentNum = accidentNum;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public InsuranceProduct getInsuranceProduct() {
		return insuranceProduct;
	}
	public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	}
	public String getAccidentDetail() {
		return accidentDetail;
	}
	public void setAccidentDetail(String accidentDetail) {
		this.accidentDetail = accidentDetail;
	}
	public Date getReceptionDate() {
		return receptionDate;
	}
	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}
}