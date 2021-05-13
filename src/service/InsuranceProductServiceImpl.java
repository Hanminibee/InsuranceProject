package service;
import java.util.ArrayList;

import entity.Contract;
import entity.InsuranceProduct;
import entity.InsuranceProducts;
import list.ContractListImpl;
import list.InsuranceProductListImpl;

public class InsuranceProductServiceImpl implements InsuranceProductService{
	
	private InsuranceProductListImpl insuranceProductListImpl;
	private ContractListImpl contractListImpl;
	
	public InsuranceProductServiceImpl(){
		this.insuranceProductListImpl = new InsuranceProductListImpl();
		this.contractListImpl = new ContractListImpl();
	}
	
	public void association() {
		
	}

	public ArrayList<InsuranceProduct> showAllList() {
		return insuranceProductListImpl.getInsuranceProductList();
	}

	public void designInsuranceProduct(InsuranceProducts insuranceProducts) {
		InsuranceProduct developedProduct = insuranceProducts.designInsurance().developInsurance();
		System.out.println(insuranceProductListImpl.add(developedProduct)?
				"보험상품 생성이 완료되었습니다.":"보험상품 생성에 실패하였습니다.");
	}
	
	public void add(InsuranceProduct developedProduct) {
		System.out.println(insuranceProductListImpl.add(developedProduct)? 
				"보험상품 생성이 완료되었습니다.":"보험상품 생성에 실패하였습니다.");
	}
	
	public ArrayList<Contract> selectNotApproval() {
		ArrayList<Contract> list = contractListImpl.getContractList();
		for(Contract contract : list) {
			if(!contract.isApproval())
				list.add(contract);
		}
		return list;
	}
	
	public InsuranceProductListImpl getInsuranceProductList() {
		return this.insuranceProductListImpl;
	}

}
