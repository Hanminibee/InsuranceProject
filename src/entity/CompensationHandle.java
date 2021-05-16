package entity;
/**
 * @author hm539
 * @version 1.0
 * @created 28-4-2021 ���� 5:02:47
 */
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