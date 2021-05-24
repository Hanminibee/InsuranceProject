package service;

import java.util.ArrayList;

import entity.Accident;
import entity.Contract;
import list.AccidentList;
import list.AccidentListImpl;
import list.ContractList;
import list.ContractListImpl;
import list.InsuranceProductList;
import type.InsuranceProductType;

public class ContractServiceImpl implements ContractService {

	private ContractList contractList;
	private AccidentList accidentList;
	private InsuranceProductList insuranceProductList;

	public ContractServiceImpl() {
		this.contractList = new ContractListImpl();
		this.accidentList = new AccidentListImpl();
	}
	
	public void association(InsuranceProductList insuranceProductList) {
		this.insuranceProductList = insuranceProductList;
	}

	public ArrayList<Contract> selectByApproval(boolean approval) {
		ArrayList<Contract> list = contractList.getContractList();
		for (Contract contract : list) {
			if (contract.isApproval() == approval)
				list.add(contract);
		}
		return list;
	}

	// accident
	public ArrayList<Accident> showAccidentListByProductType(InsuranceProductType insuranceProductType) {
		ArrayList<Accident> returnList = new ArrayList<Accident>();
		for (Accident accident : accidentList.getAccidentList()) {
			if (insuranceProductType == accident.getInsuranceProduct().getInsuranceProductType())
				returnList.add(accident);
		}
		return accidentList.getAccidentList();
	}

}