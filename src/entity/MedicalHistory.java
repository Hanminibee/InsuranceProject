package entity;

import type.CancerType;

public class MedicalHistory {
	private CancerType clientCancerCareer;
	private CancerType familyCancerCareer;
	private int numberOfHospitalizations;
	private int numberOfHospitalVisits;

	public MedicalHistory() {
		
	}
	public CancerType getClientCancerCareer() {
		return clientCancerCareer;
	}
	public void setClientCancerCareer(CancerType clientCancerCareer) {
		this.clientCancerCareer = clientCancerCareer;
	}
	public CancerType getFamilyCancerCareer() {
		return familyCancerCareer;
	}
	public void setFamilyCancerCareer(CancerType familyCancerCareer) {
		this.familyCancerCareer = familyCancerCareer;
	}
	public int getNumberOfHospitalizations() {
		return numberOfHospitalizations;
	}
	public void setNumberOfHospitalizations(int numberOfHospitalizations) {
		this.numberOfHospitalizations = numberOfHospitalizations;
	}
	public int getNumberOfHospitalVisits() {
		return numberOfHospitalVisits;
	}
	public void setNumberOfHospitalVisits(int numberOfHospitalVisits) {
		this.numberOfHospitalVisits = numberOfHospitalVisits;
	}
}