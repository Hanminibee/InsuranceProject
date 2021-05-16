package service;
import java.util.ArrayList;

import entity.InsuranceProduct;
import entity.InsuranceProducts;
import list.InsuranceProductListImpl;

public class InsuranceProductServiceImpl implements InsuranceProductService{
	
	private InsuranceProductListImpl insuranceProductListImpl;
	
	public InsuranceProductServiceImpl(){
		this.insuranceProductListImpl = new InsuranceProductListImpl();
	}

	public ArrayList<InsuranceProduct> showAllList() {
		return insuranceProductListImpl.getInsuranceProductList();
	}
	
	public ArrayList<InsuranceProduct> showInsuranceProductIsApproval(){
		return insuranceProductListImpl.searchListByApproval(true);
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
	
	public InsuranceProductListImpl getInsuranceProductList() {
		return this.insuranceProductListImpl;
	}
}
