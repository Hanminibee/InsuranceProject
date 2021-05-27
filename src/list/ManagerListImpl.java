package list;
import java.util.ArrayList;
import entity.Manager;
import entity.SalesPerson;
import entity.UW;
import type.ManagerType;
public class ManagerListImpl implements ManagerList{
	private ArrayList<Manager> managerList;
	
	public ManagerListImpl() {
		this.managerList = new ArrayList<Manager>();
		
		//더미데이터
		Manager salesPerson = new SalesPerson();
		salesPerson.setJobPosition(ManagerType.SP);
		salesPerson.setId("s");
		salesPerson.setPassword("s");
		
		Manager uw = new UW();
		uw.setJobPosition(ManagerType.UW);
		uw.setId("u");
		uw.setPassword("u");
		
		this.add(salesPerson);
		this.add(uw);
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