package entity;

public class Life extends InsuranceProduct {
	private int requiredPaymentPeriod;

	public Life(){
	}
	public int getRequiredPaymentPeriod() {
		return requiredPaymentPeriod;
	}
	public void setRequiredPaymentPeriod(int requiredPaymentPeriod) {
		this.requiredPaymentPeriod = requiredPaymentPeriod;
	}
	public Life clone() {
		return (Life)super.clone();
	}
	public double calculationRate(Client client) {
		double rate = 1 + (double)(client.getAge()/10)/10;
		if(rate > 1.5) rate = 1.5;
		return rate * basicInsurancePremium;
	}
}