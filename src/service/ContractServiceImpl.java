package service;

import java.util.ArrayList;
import java.util.Date;

import entity.Accident;
import entity.Contract;
import list.AccidentList;
import list.AccidentListImpl;
import list.ContractList;
import list.ContractListImpl;
import list.InsuranceProductListImpl;
import type.InsuranceProductType;

public class ContractServiceImpl implements ContractService {

	private ContractList contractList;
	private AccidentList accidentList;
	private InsuranceProductListImpl insuranceProductListImpl;

	public ContractServiceImpl() {
		this.contractList = new ContractListImpl();
		this.accidentList = new AccidentListImpl();
	}
	
	public void association(InsuranceProductListImpl insuranceProductListImpl) {
		this.insuranceProductListImpl = insuranceProductListImpl;
	}

	public ArrayList<Contract> selectByApproval(boolean approval) {
		ArrayList<Contract> list = contractList.getContractList();
		for (Contract contract : list) {
			if (contract.isApproval()==approval)
				list.add(contract);
		}
		return list;
	}
	public ArrayList<Contract> selectByInsuranceProductType (InsuranceProductType insuranceProductType) {
		ArrayList<Contract> list = this.selectByApproval(true);
		for (Contract contract : list) {
			if (contract.getInsuranceProduct().getInsuranceProductType() == insuranceProductType)
				list.add(contract);
		}
		return list;
	}
	public ArrayList<Contract> selectByExpiredDate (InsuranceProductType insuranceProductType) {
		ArrayList<Contract> list = this.selectByInsuranceProductType(insuranceProductType);
		for (Contract contract : list) {
			if (contract.getInsuranceExpiryDate().before(new Date()) ) 
				list.add(contract);
		}
		return list;
	}
	public boolean deleteExpiredContract (Contract contract) {
		return contractList.delete(contract);
		
	}

	// accident
	public ArrayList<Accident> showAccidentListByProductType(InsuranceProductType insuranceProductType) {
		ArrayList<Accident> returnList = new ArrayList<Accident>();
		String productName = "";
		for (Accident accident : accidentList.getAccidentList()) {
			productName = accident.getProductName();
			if (insuranceProductType == insuranceProductListImpl.search(productName).getInsuranceProductType())
				returnList.add(accident);
		}
		return accidentList.getAccidentList();
	}
	public boolean registerInsuracneProduct (Contract contract) {
		return contractList.add(contract);
	}
	public ArrayList<Contract> searchBySalesPerson (String salesPerson) {
		return contractList.searchBySalesPerson(salesPerson) ;
	}

	
}