package type;

public enum JDBCValue {
	DRIVER("com.mysql.cj.jdbc.Driver"),
	URL("jdbc:mysql://localhost:3306/insuranceproject?serverTimezone=UTC"),
	USER("root"),
	PASSWORD("426842");
	
	private String value;
	
	JDBCValue(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}