package service;
import entity.Manager;
public interface ManagerService {
	public boolean register();
	public Manager login();
	public boolean delete();
}