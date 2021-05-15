package entity;

import type.InsuranceProductType;

public class ContractManagement extends Manager {
	Contract getContract ;
	Contract cancelContract;
	Contract provideContract;
	
	public ContractManagement(){
		this.getContract = null;
		this.cancelContract = null;
		this.provideContract = null;
	}
	
	public ContractManagement contractInsuranceProducts() {
		System.out.println("계약할 보험을 선택해주세요.");
		System.out.println("1.실비보험");
		System.out.println("2.암보험");
		System.out.println("3.연금보험");
		System.out.println("4.종신보험");
		int input = sc.nextInt();
		sc.nextLine();
		this.getContract = InsuranceProductType.values()[input-1].getContract().clone();
		getContract.setInsuranceProductType(InsuranceProductType.values()[input-1]);
		return this;
	}
	public Contract getContract() {
		switch(getContract.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			return contractActualExpense();
		case CANCER:
			return contractCancer();
		case PENSION:
			return contractPension();
		case LIFE:
			return contractLife();
		default:
			return getContract;
		}
	}

	private Contract contractActualExpense() {
		ActualExpense actualExpense = (ActualExpense)getContract;
		System.out.println("계약하는 고객님의 아이디를 입력해 주세요.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract contractCancer() {
		Cancer cancer = (Cancer)getContract;
		System.out.println("계약하는 고객님의 아이디를 입력해 주세요.");
		cancer.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		cancer.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		cancer.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		cancer.setNameOfSalesPerson(sc.nextLine());
		return cancer;
	}

	private Contract contractPension() {
		Pension pension = (Pension)getContract;
		System.out.println("계약하는 고객님의 아이디를 입력해 주세요.");
		pension.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		pension.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		pension.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		pension.setNameOfSalesPerson(sc.nextLine());
		return pension ;
	}

	private Contract contractLife() {
		Life life = (Life)getContract;
		System.out.println("계약하는 고객님의 아이디를 입력해 주세요.");
		life.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		life.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		life.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		life.setNameOfSalesPerson(sc.nextLine());
		return life;
	}
	
	public ContractManagement cancelInsuranceProducts() {
		System.out.println("해지할 보험을 선택해주세요.");
		System.out.println("1.실비보험");
		System.out.println("2.암보험");
		System.out.println("3.연금보험");
		System.out.println("4.종신보험");
		int input = sc.nextInt();
		sc.nextLine();
		this.cancelContract = InsuranceProductType.remove()[input-1].getContract().clone();
		cancelContract.setInsuranceProductType(InsuranceProductType.values()[input-1]);
		return this;
	}
	public Contract cancelContract() {
		switch(cancelContract.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			return cancelContractActualExpense();
		case CANCER:
			return cancelContractCancer();
		case PENSION:
			return cancelContractPension();
		case LIFE:
			return cancelContractLife();
		default:
			return cancelContract;
		}
	}

	private Contract cancelContractActualExpense() {
		ActualExpense actualExpense = (ActualExpense)cancelContract;
		System.out.println("보험을 해지하고자 하는 고객님의 아이디를 입력해 주세요.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract cancelContractCancer() {
		ActualExpense actualExpense = (ActualExpense)cancelContract;
		System.out.println("보험을 해지하고자 하는 고객님의 아이디를 입력해 주세요.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract cancelContractPension() {
		ActualExpense actualExpense = (ActualExpense)cancelContract;
		System.out.println("보험을 해지하고자 하는 고객님의 아이디를 입력해 주세요.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract cancelContractLife() {
		ActualExpense actualExpense = (ActualExpense)cancelContract;
		System.out.println("보험을 해지하고자 하는 고객님의 아이디를 입력해 주세요.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("보험을 계약한 날짜를 입력하세요.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("계약한 보험의 만기 날짜를 입력하세요.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("보험을 계약한 영업사원의 이름을 입력하세요.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	public ContractManagement clone() {
		return (ContractManagement)super.clone();
	}
	
	public void ManageAllClientContract(Client Client){

	}

	public void ManageExpriedContracts(Client Client){

	}

}