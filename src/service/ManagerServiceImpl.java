package service;

import dao.ManagerDao;
import dao.ManagerDaoImpl;
import entity.Manager;
public class ManagerServiceImpl implements ManagerService{
	private ManagerDao managerList;
	
	public ManagerServiceImpl() {
		this.managerList = new ManagerDaoImpl();
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
	
	public Manager checkManagerID(String managerID) {
		return managerList.search(managerID);
	}
}