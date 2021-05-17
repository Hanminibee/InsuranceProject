package service;
import java.util.ArrayList;

import entity.InsuranceProduct;
import list.InsuranceProductList;
import list.InsuranceProductListImpl;

public class InsuranceProductServiceImpl implements InsuranceProductService{
	
	private InsuranceProductList insuranceProductList;
	
	public InsuranceProductServiceImpl(){
		this.insuranceProductList = new InsuranceProductListImpl();
	}

	public ArrayList<InsuranceProduct> showAllList() {
		return insuranceProductList.getInsuranceProductList();
	}
	
	public ArrayList<InsuranceProduct> showInsuranceProductIsApproval(){
		return insuranceProductList.searchListByApproval(true);
	}
	
	public ArrayList<InsuranceProduct> showInsuranceProductIsNotApproval() {
		return insuranceProductList.searchListByApproval(false);
	}

	public boolean addInsuranceProduct(InsuranceProduct developedProduct) {
		return insuranceProductList.add(developedProduct);
	}
	
	public InsuranceProductList getInsuranceProductList() {
		return this.insuranceProductList;
	}
	
}
