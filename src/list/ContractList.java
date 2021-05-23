package list;

import java.util.ArrayList;

import entity.Contract;

public interface ContractList {
	public boolean add(Contract contract);
	public boolean delete(Contract contract);
	public Contract search(Contract client, Contract insuranceProduct);
	public ArrayList<Contract> searchByClient(String clientID);
	public ArrayList<Contract> searchByClient(Contract client);
	public ArrayList<Contract> calculateExpiredDate(Contract insuranceExpiryDate);
}