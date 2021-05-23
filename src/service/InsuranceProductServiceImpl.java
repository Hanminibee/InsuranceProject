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

	public void designInsuranceProduct(InsuranceProducts insuranceProducts) {
		InsuranceProduct developedProduct = insuranceProducts.designInsurance().developInsurance();
		System.out.println(insuranceProductListImpl.add(developedProduct)? 
				"�����ǰ ������ �Ϸ�Ǿ����ϴ�.":"�����ǰ ������ �����Ͽ����ϴ�.");
	}
	
	public void add(InsuranceProduct developedProduct) {
		System.out.println(insuranceProductListImpl.add(developedProduct)? 
				"�����ǰ ������ �Ϸ�Ǿ����ϴ�.":"�����ǰ ������ �����Ͽ����ϴ�.");
	}
}
