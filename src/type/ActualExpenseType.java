package type;
public enum ActualExpenseType {
	ADMISSION("입원"), HOSPITALTREATMENT("병원진료비"), MEDICINEPRESCRIPTION("약처방비");
	
	private String actualexpensename;
	
	ActualExpenseType(String actualexpensename){
		this.actualexpensename = actualexpensename;
	}
	public String getactualexpensename() {
		return actualexpensename;
	}
}