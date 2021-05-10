package entity;

import type.CancerType;

public class Cancer extends InsuranceProduct {

	private CancerType[] guaranteedType;
	private int limitAge;
	private int[] insuranceMoneys;
	
	public CancerHistory m_CancerHistory;

	public Cancer() {

	}

	public CancerType[] getGuaranteedType() {
		return guaranteedType;
	}

	public void setGuaranteedType(CancerType[] guaranteedType) {
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
	
	public int[] getInsuranceMoneys() {
		return insuranceMoneys;
	}

	public void setInsuranceMoneys(int[] insuranceMoneys) {
		this.insuranceMoneys = insuranceMoneys;
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