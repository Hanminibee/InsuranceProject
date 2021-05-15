package list;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Client;
import entity.Contract;

public class ContractListImpl implements ContractList{
	
	Scanner sc = new Scanner(System.in);
	ArrayList<Contract> contractList = new ArrayList<Contract>();
	
	
	
	@Override
	public boolean add(Contract contract) {
		return this.contractList.add(contract);
	}

	@Override
	public boolean delete(Contract contract) {
		return this.contractList.remove(contract);
	}

	@Override
	public Contract search(Contract contract) {
		for (Contract contractName : contractList) {
			if (contractName.equals(contract.ContractInsuranceProducts()))
				return contract;
		}
		return null;




	}
}
