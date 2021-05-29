package service;

import dao.ClientDao;
import dao.InsuranceProductDao;
import dao.MedicalHistoryDao;

public class AssociationObject {
	private InsuranceProductDao insuranceProductList;
	private ClientDao clientList;
	private MedicalHistoryDao medicalHistoryList;
	
	public InsuranceProductDao getInsuranceProductList() {
		return insuranceProductList;
	}
	public void setInsuranceProductList(InsuranceProductDao insuranceProductList) {
		this.insuranceProductList = insuranceProductList;
	}
	public ClientDao getClientList() {
		return clientList;
	}
	public void setClientList(ClientDao clientList) {
		this.clientList = clientList;
	}
	public MedicalHistoryDao getMedicalHistoryList() {
		return medicalHistoryList;
	}
	public void setMedicalHistoryList(MedicalHistoryDao medicalHistoryList) {
		this.medicalHistoryList = medicalHistoryList;
	}
}
