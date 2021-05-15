package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pension extends InsuranceProduct {

	private int guaranteedPeriod;

	public Pension() {

	}

	public int getGuaranteedPeriod() {
		return guaranteedPeriod;
	}

	public void setGuaranteedPeriod(int guaranteedPeriod) {
		this.guaranteedPeriod = guaranteedPeriod;
	}
	
	public Pension clone() {
		return (Pension)super.clone();
	}

	public void setClidnetId(String nextLine) {
	
	}

	public void setInsuranceContractDate(String nextLine) {
		DateTimeFormatter dob_format=DateTimeFormatter.ofPattern("dd-MM-uuuu");
		LocalDate dob = LocalDate.parse(nextLine,dob_format);
		System.out.println(dob);
	}

	public void setInsuranceExpiryDate(String nextLine) {
		DateTimeFormatter dob_format=DateTimeFormatter.ofPattern("dd-MM-uuuu");
		LocalDate dob = LocalDate.parse(nextLine,dob_format);
		System.out.println(dob);
	}

	public void setNameOfSalesPerson(String nextLine) {
		
	}

}