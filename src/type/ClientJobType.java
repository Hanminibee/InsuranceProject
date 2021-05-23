package type;
public enum ClientJobType {
	SOLDIER("����", 1.3), PW("������", 1.5), AF("�󸳾��", 1.6), DRIVER("�������", 1.2), ETC("��Ÿ", 1.0);
	
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