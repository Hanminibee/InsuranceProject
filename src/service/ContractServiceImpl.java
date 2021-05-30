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
	
	public ArrayList<Accident> applyAccidentList() {
		return accidentList.getAccidentList();
		
	}
	
	public boolean addApplyAccidentList(Accident accident) {//추가
		return accidentList.add(accident);
	}
	
	public boolean deleteApplyAccidentList(Accident accident) {//추가
		return accidentList.delete(accident);
	}
		

	public ArrayList<Contract> selectByApproval(boolean approval) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		for (Contract contract : contractList.getContractList()) {
			if (contract.isApproval() == approval)
				list.add(contract);
		}
		return list;
	}

	public ArrayList<Contract> selectByInsuranceProductType(InsuranceProductType insuranceProductType) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		for (Contract contract : this.selectByApproval(true)) {
			if (contract.getInsuranceProduct().getInsuranceProductType() == insuranceProductType)
				list.add(contract);
		}
		return list;
	}

	public ArrayList<Contract> selectByExpiredDate(InsuranceProductType insuranceProductType) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		for (Contract contract : this.selectByInsuranceProductType(insuranceProductType)) {
			if (contract.getInsuranceExpiryDate().before(new Date()))
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
		for (Accident accident : accidentList.getAccidentList()) {
			if (insuranceProductType == accident.getInsuranceProduct().getInsuranceProductType())
				returnList.add(accident);
		}
		return accidentList.getAccidentList();
	}
	
	public boolean registerInsuranceProduct (Contract contract) {
		return contractList.add(contract);
	}
	
	public ArrayList<Contract> searchBySalesPerson (String salesPerson) {
		return contractList.searchBySalesPerson(salesPerson) ;
	}
}