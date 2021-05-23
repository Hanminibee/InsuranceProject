package service;

import entity.Manager;
import list.ManagerList;
import list.ManagerListImpl;
public class ManagerServiceImpl implements ManagerService{
	private ManagerList managerList;
	
	public ManagerServiceImpl() {
		this.managerList = new ManagerListImpl();
	}
	
	public void association() {
		
	}
	
	public boolean register(Manager manager) {
		return managerList.add(manager);
	}
	
	public Manager login(String id, String pw) {
		return managerList.search(id, pw);
	}
	
	public boolean delete(String id, String pw) {
		return managerList.delete(managerList.search(id, pw));
	}
}