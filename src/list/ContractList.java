package list;

import java.util.ArrayList;

import entity.Contract;

public interface ContractList {
	public boolean add(Contract contract);
	public boolean delete(Contract contract);
	public Contract search(String clientID, String productName);
	public ArrayList<Contract> searchByClient(String clientID);
	public ArrayList<Contract> getContractList();
	public ArrayList<Contract> searchBySalesPerson(String salesPerson);
}