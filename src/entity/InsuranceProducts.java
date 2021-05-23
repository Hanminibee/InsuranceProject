package entity;
import type.ActualExpenseType;
import type.CancerType;
import type.InsuranceProductType;
public class InsuranceProducts extends Manager{
	InsuranceProduct developInsuranceProduct;
	ActualExpense actualExpenseName;
	Cancer CancerName;
	public InsuranceProducts() {
		this.developInsuranceProduct = null;
		this.actualExpenseName = new ActualExpense();
		this.CancerName = new Cancer();
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
		System.out.println("--�Ǻ����� �����մϴ�.--");
		System.out.println("\n��ǰ���� �Է����ּ���.");
		actualExpense.setProductName(sc.nextLine());
		
		System.out.println("�⺻����Ḧ �Է��ϼ���.");
		actualExpense.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("���ԱⰣ�� �Է����ּ���.(����: ��)");
		actualExpense.setPaymentPeriod(sc.nextInt());
		
		System.out.println("�����ֱ⸦ �Է����ּ���.(����: �ſ� ��)");
		actualExpense.setPaymentCycle(sc.nextInt());
		
		System.out.println("���ѳ��̸� �Է����ּ���. (����: �� ��)");
		actualExpense.setLimitAge(sc.nextInt());
		
		System.out.println("�ڱ�δ�� ������ �Է����ּ���.(����: %)");
		actualExpense.setSelfPayment(sc.nextInt());
		
		//actualExpense.getM_ActualExpenseHistory().setNumberOfHospitalizations(sc.nextInt());
		//�Ǻ��谡�Զ� �������� �������°�
		System.out.println("���峻���� �������ּ���.");
		System.out.println("1.�Կ� 2.��������� 3.��ó���");
		int input = sc.nextInt();
		ActualExpenseType.values()[input-1].getactualexpensename();
		System.out.println(ActualExpenseType.values()[input-1].getactualexpensename());
		
		System.out.println("\n����ݾ��� �������ּ���. (�ִ� ?��)");
		actualExpense.setLimitOfIndemnity(sc.nextInt());
		
		return actualExpense;
	}
	private Cancer developCancer() {
		Cancer cancer = (Cancer)developInsuranceProduct;
		System.out.println("--�Ϻ����� �����մϴ�.--");
		System.out.println("\n��ǰ���� �Է����ּ���.");
		cancer.setProductName(sc.nextLine());
		
		System.out.println("�⺻����Ḧ �Է��ϼ���.");
		cancer.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("���ԱⰣ�� �Է����ּ���.(����: ��)");
		cancer.setPaymentPeriod(sc.nextInt());
		
		System.out.println("�����ֱ⸦ �Է����ּ���.(����: �ſ� ��)");
		cancer.setPaymentCycle(sc.nextInt());
		
		System.out.println("���ѳ��̸� �Է����ּ���. (����: �� ��)");
		cancer.setLimitAge(sc.nextInt());
		
		System.out.println("���峻��(�������)�� �������ּ���.");
		System.out.println("1.�����(1.6) 2.���(1.5) 3.����(1.4) 4.�����(1.3) 5.����(1.2) 6.��Ÿ(1.1)");
		int input = sc.nextInt();
		CancerType.values()[input-1].getCancerName();
		double rate = CancerType.values()[input-1].getRate();
		System.out.println(CancerType.values()[input-1].getCancerName() + " " + rate);
		
		System.out.println("������� �������ּ���. (�ִ� ?��)");
		cancer.setInsuranceMoney(sc.nextInt());
		return cancer;
	}
	private Pension developPension() {
		Pension pension = (Pension)developInsuranceProduct;
		System.out.println("--���ݺ����� �����մϴ�.--");
		System.out.println("��ǰ���� �Է����ּ���.");
		pension.setProductName(sc.nextLine());
		
		System.out.println("�⺻����Ḧ �Է����ּ���.(����: ��)");
		pension.setBasicInsurancePremium(sc.nextInt());	
		
		System.out.println("���ԱⰣ�� �Է����ּ���.(����: ��)");
		pension.setPaymentPeriod(sc.nextInt());
		
		System.out.println("�����ֱ⸦ �Է����ּ���.(����: �ſ� x��)");
		pension.setPaymentCycle(sc.nextInt());
		
		System.out.println("����Ⱓ �Է����ּ���. (����: �� ����)");
		pension.setGuaranteedPeriod(sc.nextInt());
		
		System.out.println("������� �Է����ּ���. (����: �ſ� x��)");
		pension.setInsuranceMoney(sc.nextInt());
		
		return pension;
	}
	private Life developLife() {
		Life life = (Life)developInsuranceProduct;
		System.out.println("--���ź����� �����մϴ�.--");
		System.out.println("��ǰ���� �Է����ּ���.");
		life.setProductName(sc.nextLine());
		
		System.out.println("�⺻����Ḧ �Է����ּ���. (����: ��)");
		life.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("���ԱⰣ�� �Է����ּ���.(����: ��)");
		life.setPaymentPeriod(sc.nextInt());
		
		System.out.println("�ʼ����ԱⰣ�� �Է����ּ���.(����: �ſ� ��)");
		life.setRequiredPaymentPeriod(sc.nextInt());
		
		System.out.println("�����ֱ� �Է����ּ���. (����: �� ����)");
		life.setPaymentCycle(sc.nextInt());
		
		System.out.println("������� �Է����ּ���. (����: �ſ� ��)");
		life.setInsuranceMoney(sc.nextInt());
		
		return life;
	}
	
	public InsuranceProducts clone() {
		return (InsuranceProducts) super.clone();
	}
}