package type;
public enum ActualExpenseType {
	ADMISSION("�Կ�"), HOSPITALTREATMENT("���������"), MEDICINEPRESCRIPTION("��ó���");
	
	private String actualexpensename;
	
	ActualExpenseType(String actualexpensename){
		this.actualexpensename = actualexpensename;
	}
	public String getactualexpensename() {
		return actualexpensename;
	}
}