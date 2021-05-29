package dao;

import entity.Manager;

public interface ManagerDao {
	public boolean add(Manager manager);
	public boolean delete(Manager manager);
	public Manager search(String managerId, String password);
	public Manager search(String managerID);
}