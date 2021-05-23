package entity;

import java.util.Date;

public class Accident {
	private int accidentNum;
	private String clientID;
	private String productName;
	private String accidentDetail;
	private Date receptionDate;

	public int getAccidentNum() {
		return accidentNum;
	}
	public void setAccidentNum(int accidentNum) {
		this.accidentNum = accidentNum;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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