package type;
public enum ClientJobType {
	SOLDIER("군인", 1.3), PW("생산직", 1.5), AF("농립어업", 1.6), DRIVER("운전기사", 1.2), ETC("기타", 1.0);
	
	private String jobName;
	private double rate;
	
	ClientJobType(String jobName, double rate){
		this.jobName = jobName;
		this.rate = rate;
	}
	public String getJobName() {
		return this.jobName;
	}
	public double getRate() {
		return this.rate;
	}
}