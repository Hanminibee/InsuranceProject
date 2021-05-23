package entity;

public class CompensationHandle extends Manager {

	public CompensationHandle(){
		
	}

	public CompensationHandle clone() {
		return (CompensationHandle)super.clone();
	}

	public void CheckAccidentReception(String accidentDetail){

	}

	public boolean payInsuranceMoney(int insuranceMoney, Client client){
		return this.remittance(client.getBankAccountNumber(), insuranceMoney);
	}
	
	private boolean remittance(String bankAccountNumber, int insuranceMoney) {
		return true;
	}

}