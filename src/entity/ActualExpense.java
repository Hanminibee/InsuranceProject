package entity;

import type.ActualExpenseType;

public class ActualExpense extends InsuranceProduct{

	private ActualExpenseType actualExpenseType;//보장종류
	private int limitOfIndemnity;//보장한도
	private int limitAge;//제한나이
	private int selfPayment;//자기부담금
	private ActualExpenseHistory m_ActualExpenseHistory;

	public ActualExpense() {
		//m_ActualExpenseHistory.getNumberOfHospitalizations();
		//m_ActualExpenseHistory.getNumberOfHospitalVisits();
	}
	public ActualExpenseType getActualExpenseType() {
		return actualExpenseType;
	}
	public void setActualExpenseType(ActualExpenseType actualExpenseType) {
		this.actualExpenseType = actualExpenseType;
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
		MedicalHistory medicalHistory = client.getMedicalHistory();
		double rateHospitalization = this.getRateHospitalization(medicalHistory.getNumberOfHospitalizations());
		double rateVisits = this.getRateVisits(medicalHistory.getNumberOfHospitalVisits());
		double rateJob = client.getJob().getRate();
		return rateHospitalization*rateVisits*rateJob*basicInsurancePremium;
	}
	
	private double getRateHospitalization(int hospitalization) {
		double rateHospitalization = 1;
		if(hospitalization >= 5)
			rateHospitalization = 1.1;
		else if(hospitalization >= 10)
			rateHospitalization = 1.2;
		else if(hospitalization >= 15)
			rateHospitalization = 1.3;
		else if(hospitalization >= 20)
			rateHospitalization = 1.4;
		else if(hospitalization >= 25)
			rateHospitalization = 1.5;
		return rateHospitalization;
	}
	
	private double getRateVisits(int visits) {
		double rateVisits = 1;
		if(visits >= 10)
			rateVisits = 1.1;
		else if(visits >= 20)
			rateVisits = 1.2;
		else if(visits >= 30)
			rateVisits = 1.3;
		else if(visits >= 40)
			rateVisits = 1.4;
		else if(visits >= 50)
			rateVisits = 1.5;
		return rateVisits;
	}
}