package entity;
public class Pension extends InsuranceProduct {
	private int guaranteedPeriod;

	public Pension() {
	}
	public int getGuaranteedPeriod() {
		return guaranteedPeriod;
	}
	public void setGuaranteedPeriod(int guaranteedPeriod) {
		this.guaranteedPeriod = guaranteedPeriod;
	}
	public Pension clone() {
		return (Pension)super.clone();
	}
}