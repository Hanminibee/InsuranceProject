package type;

import entity.*;

public enum ManagerType {

	IP("보험상품개발자", new InsuranceProducts()), IPA("보험상품승인자", new InsuranceProductsAcceptance()), 
	UW("U/W", new UW()), CM("계약관리자", new ContractManagement()), 
	CH("보상처리자", new CompensationHandle()), SP("영업사원", new SalesPerson());

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
