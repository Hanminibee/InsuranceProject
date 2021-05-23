package list;
import entity.InsuranceProduct;
public interface InsuranceProductList {
	public boolean add(InsuranceProduct insuranceProduct);
	public boolean delete(InsuranceProduct insuranceProduct);
	public InsuranceProduct search(InsuranceProduct insuranceProduct);
}