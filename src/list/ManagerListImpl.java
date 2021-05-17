package list;

import java.util.ArrayList;

import entity.InsuranceProducts;
import entity.InsuranceProductsAcceptance;
import entity.Manager;
import type.ManagerType;

public class ManagerListImpl implements ManagerList{
	
	private ArrayList<Manager> managerList;
	
	public ManagerListImpl() {
		this.managerList = new ArrayList<Manager>();
		
		//test
		InsuranceProducts ip = new InsuranceProducts();
		ip.setName("test");
		ip.setId("qwe");
		ip.setPassword("qwe");
		ip.setJobPosition(ManagerType.IP);
		
		InsuranceProductsAcceptance ipa = new InsuranceProductsAcceptance();
		ipa.setName("test2");
		ipa.setId("asd");
		ipa.setPassword("asd");
		ipa.setJobPosition(ManagerType.IPA);
		
		this.add(ip);
		this.add(ipa);
	}

	@Override
	public boolean add(Manager manager) {
		return managerList.add(manager);
	}

	@Override
	public boolean delete(Manager manager) {
		return managerList.remove(manager);
	}

	@Override
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
