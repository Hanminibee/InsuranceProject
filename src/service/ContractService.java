package service;

import java.util.ArrayList;

import entity.Accident;
import entity.Contract;
import type.InsuranceProductType;

public interface ContractService {
	
	public void association(AssociationObject associationObject);
	public ArrayList<Contract> selectByApproval(boolean approval);
	public ArrayList<Accident> showAccidentListByProductType(InsuranceProductType insuranceProductType);
	public ArrayList<Contract> selectByInsuranceProductType(InsuranceProductType insuranceProductType);
	public ArrayList<Contract> selectByExpiredDate(InsuranceProductType insuranceProductType);
	public boolean deleteContract(Contract contract);
	public boolean registerInsuranceProduct(Contract contract);
	public ArrayList<Contract> searchBySalesPerson(String salesPerson);
	public boolean modifyContract(Contract contract);
	
	public ArrayList<Accident> applyAccidentList();
	public boolean addApplyAccidentList(Accident accident);//추가
	public boolean deleteAccidentList(Accident accident);//추가
}