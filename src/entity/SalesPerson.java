package entity;
public class SalesPerson extends Manager {
	public SalesPerson(){
	}
	public SalesPerson clone() {
		return (SalesPerson)super.clone();
	}
	public void ManageClient(Client Client){
	}
	public void ModifyInformation(){
	}
	public void ProvideInsuranceProducts(InsuranceProducts InsuranceProducts){
	}
	public void SignUpInsurance(Client Client){
	}
}