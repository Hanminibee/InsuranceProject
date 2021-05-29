package service;

import entity.Manager;

public interface ManagerService {
	
	public void association();
	public boolean register(Manager manager);
	public Manager login(String id, String pw);
	public boolean delete(String id, String pw);
	public Manager checkManagerID(String managerID);
	
}
