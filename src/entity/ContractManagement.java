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
		System.out.println("����� ������ �������ּ���.");
		System.out.println("1.�Ǻ���");
		System.out.println("2.�Ϻ���");
		System.out.println("3.���ݺ���");
		System.out.println("4.���ź���");
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
		System.out.println("����ϴ� ������ ���̵� �Է��� �ּ���.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract contractCancer() {
		Cancer cancer = (Cancer)getContract;
		System.out.println("����ϴ� ������ ���̵� �Է��� �ּ���.");
		cancer.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		cancer.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		cancer.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
		cancer.setNameOfSalesPerson(sc.nextLine());
		return cancer;
	}

	private Contract contractPension() {
		Pension pension = (Pension)getContract;
		System.out.println("����ϴ� ������ ���̵� �Է��� �ּ���.");
		pension.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		pension.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		pension.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
		pension.setNameOfSalesPerson(sc.nextLine());
		return pension ;
	}

	private Contract contractLife() {
		Life life = (Life)getContract;
		System.out.println("����ϴ� ������ ���̵� �Է��� �ּ���.");
		life.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		life.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		life.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
		life.setNameOfSalesPerson(sc.nextLine());
		return life;
	}
	
	public ContractManagement cancelInsuranceProducts() {
		System.out.println("������ ������ �������ּ���.");
		System.out.println("1.�Ǻ���");
		System.out.println("2.�Ϻ���");
		System.out.println("3.���ݺ���");
		System.out.println("4.���ź���");
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
		System.out.println("������ �����ϰ��� �ϴ� ������ ���̵� �Է��� �ּ���.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract cancelContractCancer() {
		ActualExpense actualExpense = (ActualExpense)cancelContract;
		System.out.println("������ �����ϰ��� �ϴ� ������ ���̵� �Է��� �ּ���.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract cancelContractPension() {
		ActualExpense actualExpense = (ActualExpense)cancelContract;
		System.out.println("������ �����ϰ��� �ϴ� ������ ���̵� �Է��� �ּ���.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
		actualExpense.setNameOfSalesPerson(sc.nextLine());
		return actualExpense;
	}

	private Contract cancelContractLife() {
		ActualExpense actualExpense = (ActualExpense)cancelContract;
		System.out.println("������ �����ϰ��� �ϴ� ������ ���̵� �Է��� �ּ���.");
		actualExpense.setClidnetId(sc.nextLine());
		System.out.println("������ ����� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceContractDate(sc.nextLine());
		System.out.println("����� ������ ���� ��¥�� �Է��ϼ���.");
		actualExpense.setInsuranceExpiryDate(sc.nextLine());
		System.out.println("������ ����� ��������� �̸��� �Է��ϼ���.");
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