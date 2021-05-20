package list;
import java.util.ArrayList;

import entity.ActualExpense;
import entity.InsuranceProduct;
import type.InsuranceProductType;
public class InsuranceProductListImpl implements InsuranceProductList {
	
	private ArrayList<InsuranceProduct> insuranceProductList;
	
	public InsuranceProductListImpl() {
		this.insuranceProductList = new ArrayList<InsuranceProduct>();
		
		//test insuranceProduct
		ActualExpense ae = new ActualExpense();
		ae.setProductName("테스트실비보험");
		ae.setInsuranceProductType(InsuranceProductType.ACTUALEXPENSE);
		ae.setApproval(false);
		this.add(ae);
	}
	public ArrayList<InsuranceProduct> getInsuranceProductList() {
		return this.insuranceProductList;
	}
	
	public boolean add(InsuranceProduct insuranceProduct) {
		return insuranceProductList.add(insuranceProduct);
	}
	public boolean delete(InsuranceProduct insuranceProduct) {
		return insuranceProductList.remove(insuranceProduct);
	}
	public InsuranceProduct search(InsuranceProduct insuranceProduct) {
		return null;
	}
	public InsuranceProduct search(String productName) {
		for(InsuranceProduct insuranceProduct : insuranceProductList)
			if(productName.equals(insuranceProduct.getProductName()))
				return insuranceProduct;
		return null;
	}
	public ArrayList<InsuranceProduct> searchListByApproval(boolean approval) {
		ArrayList<InsuranceProduct> returnList = new ArrayList<InsuranceProduct>();
		for(InsuranceProduct insuranceProduct : insuranceProductList) {
			if(insuranceProduct.isApproval() == approval)
				returnList.add(insuranceProduct);
		}
		return returnList;
	}
}