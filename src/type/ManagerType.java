package type;

import entity.*;

public enum ManagerType {

	IP("�����ǰ������", new InsuranceProducts()), IPA("�����ǰ������", new InsuranceProductsAcceptance()), 
	UW("U/W", new UW()), CM("��������", new ContractManagement()), 
	CH("����ó����", new CompensationHandle()), SP("�������", new SalesPerson());

	private String job;
	private Manager manager;

	ManagerType(String job, Manager manager) {
		this.job = job;
		this.manager = manager;
	}

	public String getJob() {
		return job;
	}
	
	public Manager getManager() {
		return this.manager;
	}
	
}
