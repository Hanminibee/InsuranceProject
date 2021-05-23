package service;

import java.util.ArrayList;

import entity.Accident;
import entity.Contract;
import type.InsuranceProductType;

public interface ContractService {
	
	public ArrayList<Contract> selectNotApproval();
	public ArrayList<Accident> showAccidentListByProductType(InsuranceProductType insuranceProductType);
	public ArrayList<Contract> showAllContract();
	public ArrayList<Contract> showExpiredInsurance();

}