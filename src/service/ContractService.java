package service;

import java.util.ArrayList;

import entity.Accident;
import entity.Contract;
import list.InsuranceProductList;
import type.InsuranceProductType;

public interface ContractService {
	public void association(InsuranceProductList insuranceProductList);
	public ArrayList<Contract> selectNotApproval();
	public ArrayList<Accident> showAccidentListByProductType(InsuranceProductType insuranceProductType);

}