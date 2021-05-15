package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Life extends InsuranceProduct {

	private int requiredPaymentPeriod;

	public Life(){

	}

	public int getRequiredPaymentPeriod() {
		return requiredPaymentPeriod;
	}

	public void setRequiredPaymentPeriod(int requiredPaymentPeriod) {
		this.requiredPaymentPeriod = requiredPaymentPeriod;
	}
	
	public Life clone() {
		return (Life)super.clone();
	}
	
	public double calculationRate(Client client) {
		double rate = 1 + (double)(client.getAge()/10)/10;
		if(rate > 1.5) rate = 1.5;
		return rate * basicInsurancePremium;
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