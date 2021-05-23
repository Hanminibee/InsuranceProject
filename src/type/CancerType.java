package type;

public enum CancerType {
	PANCREATIC("�����", 1.6), LUNG("���", 1.5), STOMACH("����", 1.4), 
	COLORECTAL("�����", 1.3), LIVER("����", 1.2), ETC("��Ÿ", 1.1), 
	HEALTHY("����", 1.0);
	
	private String cancerName;
	private double rate;
	
	CancerType(String cancerName, double rate){
		this.cancerName = cancerName;
		this.rate = rate;
	}
	
	public String getCancerName() {
		return cancerName;
	}
	
	public double getRate() {
		return rate;
	}
	
}
