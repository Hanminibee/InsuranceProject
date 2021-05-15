package type;

public enum CancerType {
	PANCREATIC("췌장암", 1.6), LUNG("폐암", 1.5), STOMACH("위암", 1.4), 
	COLORECTAL("대장암", 1.3), LIVER("간암", 1.2), ETC("기타", 1.1), 
	HEALTHY("없음", 1.0);
	
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
