package entity;

import type.CancerType;

public class Cancer extends InsuranceProduct {

	private String cancerList;
	private String guaranteedType;
	private int limitAge;
	public CancerHistory m_CancerHistory;
	private CancerType cancerType;

	public Cancer() {
		//m_CancerHistory.getClientCancerCareer();
		//m_CancerHistory.getFamilyCancerCareer();
	}

	public CancerType getCancerType() {
		return cancerType;
	}

	public void setCancerType(CancerType cancerType) {
		this.cancerType = cancerType;
	}

	public String getCancerList() {
		return cancerList;
	}

	public void setCancerList(String cancerList) {
		this.cancerList = cancerList;
	}

	public String getGuaranteedType() {
		return guaranteedType;
	}

	public void setGuaranteedType(String guaranteedType) {
		this.guaranteedType = guaranteedType;
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