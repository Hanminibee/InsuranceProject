package service;

import java.util.ArrayList;
import java.util.Date;

import dao.AccidentDao;
import dao.AccidentDaoImpl;
import dao.ClientDao;
import dao.ContractDao;
import dao.ContractDaoImpl;
import dao.InsuranceProductDao;
import dao.MedicalHistoryDao;
import entity.Accident;
import entity.Client;
import entity.Contract;
import entity.MedicalHistory;
import type.InsuranceProductType;

public class ContractServiceImpl implements ContractService {

	private ContractDao contractList;
	private AccidentDao accidentList;
	private InsuranceProductDao insuranceProductList;
	private ClientDao clientList;
	private MedicalHistoryDao medicalHistoryList;
	
	public ContractServiceImpl() {
		this.contractList = new ContractDaoImpl();
		this.accidentList = new AccidentDaoImpl();
	}
	
	public void association(AssociationObject associationObject) {
		this.insuranceProductList = associationObject.getInsuranceProductList();
		this.clientList = associationObject.getClientList();
		this.medicalHistoryList = associationObject.getMedicalHistoryList();
	}

	public ArrayList<Contract> selectByApproval(boolean approval) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		for (Contract contract : contractList.findAll()) {
			if (contract.isApproval() == approval)
				list.add(contract);
		}
		return this.insertContractData(list);
	}
	
	public ArrayList<Contract> selectByInsuranceProductType (InsuranceProductType insuranceProductType) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		for (Contract contract : this.selectByApproval(true)) {
			if (contract.getInsuranceProduct().getInsuranceProductType() == insuranceProductType)
				list.add(contract);
		}
		return list;
	}
	
	public ArrayList<Contract> selectByExpiredDate (InsuranceProductType insuranceProductType) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		for (Contract contract : this.selectByInsuranceProductType(insuranceProductType)) {
			if (contract.getInsuranceExpiryDate().before(new Date()) ) 
				list.add(contract);
		}
		return list;
	}
	
	public boolean deleteExpiredContract (Contract contract) {
		return contractList.delete(contract);
	}
	
	public boolean registerInsuranceProduct (Contract contract) {
		return contractList.add(contract);
	}
	
	public ArrayList<Contract> searchBySalesPerson (String salesPerson) {
		return this.insertContractData(contractList.searchBySalesPerson(salesPerson));
	}

	@Override
	public boolean modifyContract(Contract contract) {
		return contractList.update(contract);
	}
	
	private ArrayList<Contract> insertContractData(ArrayList<Contract> list){
		for(Contract contract : list) {
			contract.setInsuranceProduct(insuranceProductList.search(contract.getInsuranceProduct().getProductName()));
			Client client = clientList.search(contract.getClient().getId());
			MedicalHistory medicalHistory = medicalHistoryList.search(client.getId());
			client.getMedicalHistory().setClientCancerCareer(medicalHistory.getClientCancerCareer());
			client.getMedicalHistory().setFamilyCancerCareer(medicalHistory.getFamilyCancerCareer());
			client.getMedicalHistory().setNumberOfHospitalizations(medicalHistory.getNumberOfHospitalizations());
			client.getMedicalHistory().setNumberOfHospitalVisits(medicalHistory.getNumberOfHospitalVisits());
			contract.setClient(client);
		}
		return list;
	}

	// accident
	public ArrayList<Accident> showAccidentListByProductType(InsuranceProductType insuranceProductType) {
		ArrayList<Accident> returnList = new ArrayList<Accident>();
		for (Accident accident : accidentList.findAll()) {
			if (insuranceProductType == accident.getInsuranceProduct().getInsuranceProductType())
				returnList.add(accident);
		}
		return returnList;
	}
	
	public boolean addApplyAccidentList(Accident accident) {
		return accidentList.add(accident);
	}

	@Override
	public ArrayList<Accident> applyAccidentList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAccidentList(Accident accident) {
		return accidentList.delete(accident);
	}
}