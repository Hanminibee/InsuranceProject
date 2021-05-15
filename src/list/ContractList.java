package list;

import entity.Contract;

public interface ContractList {
	public boolean add(Contract contract);
	public boolean delete(Contract contract);
	public Contract search(Contract contract);
}
