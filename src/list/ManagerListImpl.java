package list;
import java.util.ArrayList;
import entity.Manager;
public class ManagerListImpl implements ManagerList{
	private ArrayList<Manager> managerList;
	public ManagerListImpl() {
		this.managerList = new ArrayList<Manager>();
	}
	public boolean add(Manager manager) {
		return managerList.add(manager);
	}
	public boolean delete(Manager manager) {
		return managerList.remove(manager);
	}
	public Manager search(String managerID) {
		for(Manager manager : managerList) {
			if(managerID.equals(manager.getId()))
				return manager;
		}
		return null;
	}
	public Manager search(String managerID, String password) {
		for(Manager manager : managerList) {
			if(managerID.equals(manager.getId()) & password.equals(manager.getPassword()))
				return manager;
		}
		return null;
	}
}