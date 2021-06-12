package service;
import java.util.ArrayList;

import dao.InsuranceProductDao;
import dao.InsuranceProductDaoImpl;
import entity.InsuranceProduct;

public class InsuranceProductServiceImpl implements InsuranceProductService{
	
	private InsuranceProductDao insuranceProductList;
	
	public InsuranceProductServiceImpl(){
		this.insuranceProductList = new InsuranceProductDaoImpl();
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
	
	public InsuranceProductDao getInsuranceProductList() {
		return this.insuranceProductList;
	}

	public boolean modifyInsuranceProduct(InsuranceProduct insuranceProduct) {
		return insuranceProductList.update(insuranceProduct);
	}

	public boolean deleteInsuranceProduct(InsuranceProduct insuranceProduct) {
		return insuranceProductList.delete(insuranceProduct);
	}

	public InsuranceProduct searchInsuranceProduct(String productName) {
		return insuranceProductList.search(productName);
	}
	
}