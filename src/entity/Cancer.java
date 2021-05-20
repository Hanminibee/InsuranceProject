package entity;

import type.CancerType;

public class Cancer extends InsuranceProduct {

	private CancerType guaranteedType;
	private int limitAge;

	public Cancer() {
		
	}

	public CancerType getGuaranteedType() {
		return guaranteedType;
	}

	public void setGuaranteedType(CancerType guaranteedType) {
		this.guaranteedType = guaranteedType;
	}

	public int getLimitAge() {
		return limitAge;
	}

	public void setLimitAge(int limitAge) {
		this.limitAge = limitAge;
	}

	@Override
	public double calculationRate(Client client) {
		double clientCancerCareerRate = client.getMedicalHistory()
				.getClientCancerCareer().getRate();
		double familyCancerCareerRate = client.getMedicalHistory()
				.getFamilyCancerCareer().getRate();
		return clientCancerCareerRate * familyCancerCareerRate
				* basicInsurancePremium;
	}

}