package service;

import java.util.ArrayList;

import entity.InsuranceProduct;
import list.InsuranceProductList;

public interface InsuranceProductService {
	
	public ArrayList<InsuranceProduct> showAllList();
	public ArrayList<InsuranceProduct> showInsuranceProductIsApproval();
	public ArrayList<InsuranceProduct> showInsuranceProductIsNotApproval();
	public boolean addInsuranceProduct(InsuranceProduct developedProduct);
	public InsuranceProductList getInsuranceProductList();
	
}