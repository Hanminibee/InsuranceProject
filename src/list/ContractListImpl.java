package list;

import java.util.ArrayList;

import entity.Contract;

public class ContractListImpl implements ContractList{
	
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
	public Contract search(Contract client, Contract insuranceProduct) {
		for(Contract contract : contractList) {
			if(client.equals(contract.getClient()) && insuranceProduct.equals(contract.getInsuranceProduct()))
				return contract;
		}
		return null;
	}
	

	public ArrayList<Contract> searchByClient(Contract client) {
		ArrayList<Contract> returnList = new ArrayList<Contract>();
		for(Contract contract : contractList) {
			if(client.equals(contract.getClient()))
				returnList.add(contract);
		}
		return returnList;
	}
	
	public ArrayList<Contract> getContractList(){
		return this.contractList;
	}

	@Override
	public ArrayList<Contract> searchByClient(String clientID) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Contract> calculateExpiredDate(Contract insuranceExpiryDate){
		Contract calculateExpiredDate = new Contract()
		return this.contractList;
	}
	
}