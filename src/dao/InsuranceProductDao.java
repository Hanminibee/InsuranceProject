package dao;

import entity.InsuranceProduct;

public interface InsuranceProductDao {

	public boolean add(InsuranceProduct insuranceProduct);
	public boolean update(InsuranceProduct insuranceProduct);
	public boolean delete(InsuranceProduct insuranceProduct);
	public InsuranceProduct search(String productName);
	
}
