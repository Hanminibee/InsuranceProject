package list;

import java.util.ArrayList;

import entity.Contract;

public class ContractListImpl implements ContractList {

	private ArrayList<Contract> contractList;

	public ContractListImpl() {
		this.contractList = new ArrayList<Contract>();
	}

	@Override
	public boolean add(Contract contract) {
		return contractList.add(contract);
	}

	@Override
	public boolean delete(Contract contract) {
		return contractList.remove(contract);
	}

	@Override
	public Contract search(String clientID, String productName) {
		for (Contract contract : contractList) {
			if (clientID.equals(contract.getClient().getId()) && productName.equals(contract.getInsuranceProduct().getProductName()))
				return contract;
		}
		return null;
	}

	@Override
	public ArrayList<Contract> searchByClient(String clientID) {
		ArrayList<Contract> returnList = new ArrayList<Contract>();
		for (Contract contract : contractList) {
			if (clientID.equals(contract.getClient().getId()))
				returnList.add(contract);
		}
		return returnList;
	}

	public ArrayList<Contract> getContractList() {
		return this.contractList;
	}

	public ArrayList<Contract> searchBySalesPerson(String salesPerson) {
		ArrayList<Contract> returnList = new ArrayList<Contract>();
		for (Contract contract : contractList) {
			if (salesPerson.equals(contract.getSalesPerson().getId()))
				returnList.add(contract);
		}
		return returnList;
	}

}