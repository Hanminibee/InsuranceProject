package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ActualExpense extends InsuranceProduct{

	private String guaranteedType;
	private int limitOfIndemnity;
	private int limitAge;
	private int selfPayment;
	private ActualExpenseHistory m_ActualExpenseHistory;

	public ActualExpense() {
		
	}

	public String getGuaranteedType() {
		return guaranteedType;
	}

	public void setGuaranteedType(String guaranteedType) {
		this.guaranteedType = guaranteedType;
	}

	public int getLimitOfIndemnity() {
		return limitOfIndemnity;
	}

	public void setLimitOfIndemnity(int limitOfIndemnity) {
		this.limitOfIndemnity = limitOfIndemnity;
	}

	public int getLimitAge() {
		return limitAge;
	}

	public void setLimitAge(int limitAge) {
		this.limitAge = limitAge;
	}

	public int getSelfPayment() {
		return selfPayment;
	}

	public void setSelfPayment(int selfPayment) {
		this.selfPayment = selfPayment;
	}

	public ActualExpenseHistory getM_ActualExpenseHistory() {
		return m_ActualExpenseHistory;
	}

	public void setM_ActualExpenseHistory(ActualExpenseHistory m_ActualExpenseHistory) {
		this.m_ActualExpenseHistory = m_ActualExpenseHistory;
	}
	
	public ActualExpense clone() {
		return (ActualExpense)super.clone();
	}
	
	@Override
	public double calculationRate(Client client) {
		return (client.getJob().getRate())*basicInsurancePremium;
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