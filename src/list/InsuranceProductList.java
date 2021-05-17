package list;
import java.util.ArrayList;

import entity.InsuranceProduct;
public interface InsuranceProductList {
	
	public boolean add(InsuranceProduct insuranceProduct);
	public boolean delete(InsuranceProduct insuranceProduct);
	public InsuranceProduct search(String productName);
	public ArrayList<InsuranceProduct> searchListByApproval(boolean approval);
	public ArrayList<InsuranceProduct> getInsuranceProductList();
	
}