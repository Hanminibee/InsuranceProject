package service;

import java.util.ArrayList;

import dao.InsuranceProductDao;
import entity.InsuranceProduct;

public interface InsuranceProductService {
	
	public ArrayList<InsuranceProduct> showInsuranceProductIsApproval();
	public ArrayList<InsuranceProduct> showInsuranceProductIsNotApproval();
	public InsuranceProduct searchInsuranceProduct(String productName);
	public boolean addInsuranceProduct(InsuranceProduct developedProduct);
	public boolean modifyInsuranceProduct(InsuranceProduct insuranceProduct);
	public boolean deleteInsuranceProduct(InsuranceProduct insuranceProduct);
	public InsuranceProductDao getInsuranceProductList();
	
}