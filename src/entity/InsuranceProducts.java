package entity;

import type.InsuranceProductType;

public class InsuranceProducts extends Manager{
	
	InsuranceProduct developInsuranceProduct;

	public InsuranceProducts() {
		this.developInsuranceProduct = null;
	}

	public InsuranceProducts designInsurance() {
		System.out.println("������ ������ �������ּ���.");
		System.out.println("1.�Ǻ���");
		System.out.println("2.�Ϻ���");
		System.out.println("3.���ݺ���");
		System.out.println("4.���ź���");
		int input = sc.nextInt();
		sc.nextLine();
		this.developInsuranceProduct = InsuranceProductType.values()[input-1].getInsuranceProduct().clone();
		developInsuranceProduct.setInsuranceProductType(InsuranceProductType.values()[input-1]);
		return this;
	}

	public InsuranceProduct developInsurance() {
		switch(developInsuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			return developActualExpense();
		case CANCER:
			return developCancer();
		case PENSION:
			return developPension();
		case LIFE:
			return developLife();
		default:
			return developInsuranceProduct;
		}
	}
	
	private ActualExpense developActualExpense() {
		ActualExpense actualExpense = (ActualExpense)developInsuranceProduct;
		System.out.println("��ǰ���� �Է����ּ���.");
		actualExpense.setProductName(sc.nextLine());
		
		System.out.println("���ԱⰣ�� �Է����ּ���.(����: ��)");
		actualExpense.setPaymentPeriod(sc.nextInt());
		
		System.out.println("�����ֱ⸦ �Է����ּ���.(����: �ſ� x��)");
		actualExpense.setPaymentCycle(sc.nextInt());
		
		System.out.println("���ѳ��̸� �Է����ּ���. (����: �� x��)");
		actualExpense.setLimitAge(sc.nextInt());
		
		System.out.println("�ڱ�δ�� ������ �Է����ּ���.(����: %)");
		actualExpense.setSelfPayment(sc.nextInt());
		
		System.out.println();
		
		return actualExpense;
	}
	
	private Cancer developCancer() {
		Cancer cancer = (Cancer)developInsuranceProduct;
		
		return cancer;
	}
	
	private Pension developPension() {
		Pension pension = (Pension)developInsuranceProduct;
		System.out.println("��ǰ���� �Է����ּ���.");
		pension.setProductName(sc.nextLine());
		
		System.out.println("���ԱⰣ�� �Է����ּ���.(����: ��)");
		pension.setPaymentPeriod(sc.nextInt());
		
		System.out.println("�����ֱ⸦ �Է����ּ���.(����: �ſ� x��)");
		pension.setPaymentCycle(sc.nextInt());
		
		System.out.println("�⺻����Ḧ �Է����ּ���. (����: ��)");
		pension.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("����Ⱓ �Է����ּ���. (����: �� ����)");
		pension.setGuaranteedPeriod(sc.nextInt());
		
		System.out.println("����ݾ��� �Է����ּ���. (����: �ſ� x��)");
		pension.setInsuranceMoney(sc.nextInt());
		
		return pension;
	}
	
	private Life developLife() {
		Life life = (Life)developInsuranceProduct;
		System.out.println("��ǰ���� �Է����ּ���.");
		life.setProductName(sc.nextLine());
		
		System.out.println("���ԱⰣ�� �Է����ּ���.(����: ��)");
		life.setPaymentPeriod(sc.nextInt());
		
		System.out.println("�ʼ� ���ԱⰣ�� �Է����ּ���. (����: ��)");
		life.setRequiredPaymentPeriod(sc.nextInt());
		
		System.out.println("�����ֱ⸦ �Է����ּ���.(����: �ſ� x��)");
		life.setPaymentCycle(sc.nextInt());
		
		System.out.println("�⺻����Ḧ �Է����ּ���. (����: ��)");
		life.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("������� �Է����ּ���. (����: ��)");
		life.setInsuranceMoney(sc.nextInt());
		
		return life;
	}
	
	public void FollowUpInsurance(InsuranceProduct InsuranceProduct) {
		
	}
	
	public InsuranceProducts clone() {
		return (InsuranceProducts) super.clone();
	}

}