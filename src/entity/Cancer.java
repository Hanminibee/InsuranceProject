package entity;

import type.CancerType;

public class Cancer extends InsuranceProduct {

	private String cancerList;
	private int limitAge;
	public CancerHistory m_CancerHistory;
	private CancerType guaranteedType;

	public Cancer() {
		//m_CancerHistory.getClientCancerCareer();
		//m_CancerHistory.getFamilyCancerCareer();
	}

	public CancerType getCancerType() {
		return guaranteedType;
	}


	public CancerType getGuaranteedType() {
		return guaranteedType;
	}

	public void setGuaranteedType(CancerType guaranteedType) {
		this.guaranteedType = guaranteedType;
	}

	public String getCancerList() {
		return cancerList;
	}

	public void setCancerList(String cancerList) {
		this.cancerList = cancerList;
	}

	public int getLimitAge() {
		return limitAge;
	}

	public void setLimitAge(int limitAge) {
		this.limitAge = limitAge;
	}

	public CancerHistory getM_CancerHistory() {
		return m_CancerHistory;
	}

	public void setM_CancerHistory(CancerHistory m_CancerHistory) {
		this.m_CancerHistory = m_CancerHistory;
	}
	
	public Cancer clone() {
		return (Cancer)super.clone();
	}
	
	@Override
	public double calculationRate(Client client) {
		double clientCancerCareerRate = client.getMedicalHistory().getClientCancerCareer().getRate();
		double familyCancerCareerRate = client.getMedicalHistory().getFamilyCancerCareer().getRate();
		return clientCancerCareerRate*familyCancerCareerRate*basicInsurancePremium;
	}

}