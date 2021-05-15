package service;

import java.util.ArrayList;

import entity.Accident;
import entity.Contract;
import list.AccidentListImpl;
import list.ContractListImpl;
import list.InsuranceProductListImpl;
import type.InsuranceProductType;

public class ContractServiceImpl implements ContractService {

	private ContractListImpl contractListImpl;
	private AccidentListImpl accidentListImpl;
	private InsuranceProductListImpl insuranceProductListImpl;

	public ContractServiceImpl() {
		this.contractListImpl = new ContractListImpl();
		this.accidentListImpl = new AccidentListImpl();
	}
	
	public void association(InsuranceProductListImpl insuranceProductListImpl) {
		this.insuranceProductListImpl = insuranceProductListImpl;
	}

	public ArrayList<Contract> selectNotApproval() {
		ArrayList<Contract> list = contractListImpl.getContractList();
		for (Contract contract : list) {
			if (!contract.isApproval())
				list.add(contract);
		}
		return list;
	}

	// accident
	public ArrayList<Accident> showAccidentListByProductType(InsuranceProductType insuranceProductType) {
		ArrayList<Accident> returnList = new ArrayList<Accident>();
		String productName = "";
		for (Accident accident : accidentListImpl.getAccidentList()) {
			productName = accident.getProductName();
			if (insuranceProductType == insuranceProductListImpl.search(productName).getInsuranceProductType())
				returnList.add(accident);
		}
		return accidentListImpl.getAccidentList();
	}
}
