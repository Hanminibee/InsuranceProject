package entity;

import type.InsuranceProductType;

public class InsuranceProducts extends Manager{
	
	InsuranceProduct developInsuranceProduct;

	public InsuranceProducts() {
		this.developInsuranceProduct = null;
	}

	public InsuranceProducts designInsurance() {
		System.out.println("개발할 보험을 선택해주세요.");
		System.out.println("1.실비보험");
		System.out.println("2.암보험");
		System.out.println("3.연금보험");
		System.out.println("4.종신보험");
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
		System.out.println("상품명을 입력해주세요.");
		actualExpense.setProductName(sc.nextLine());
		
		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		actualExpense.setPaymentPeriod(sc.nextInt());
		
		System.out.println("납입주기를 입력해주세요.(단위: 매월 x일)");
		actualExpense.setPaymentCycle(sc.nextInt());
		
		System.out.println("제한나이를 입력해주세요. (단위: 만 x세)");
		actualExpense.setLimitAge(sc.nextInt());
		
		System.out.println("자기부담금 비율을 입력해주세요.(단위: %)");
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
		System.out.println("상품명을 입력해주세요.");
		pension.setProductName(sc.nextLine());
		
		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		pension.setPaymentPeriod(sc.nextInt());
		
		System.out.println("납입주기를 입력해주세요.(단위: 매월 x일)");
		pension.setPaymentCycle(sc.nextInt());
		
		System.out.println("기본보험료를 입력해주세요. (단위: 원)");
		pension.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("보장기간 입력해주세요. (단위: 만 나이)");
		pension.setGuaranteedPeriod(sc.nextInt());
		
		System.out.println("보상금액을 입력해주세요. (단위: 매월 x원)");
		pension.setInsuranceMoney(sc.nextInt());
		
		return pension;
	}
	
	private Life developLife() {
		Life life = (Life)developInsuranceProduct;
		System.out.println("상품명을 입력해주세요.");
		life.setProductName(sc.nextLine());
		
		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		life.setPaymentPeriod(sc.nextInt());
		
		System.out.println("필수 납입기간을 입력해주세요. (단위: 년)");
		life.setRequiredPaymentPeriod(sc.nextInt());
		
		System.out.println("납입주기를 입력해주세요.(단위: 매월 x일)");
		life.setPaymentCycle(sc.nextInt());
		
		System.out.println("기본보험료를 입력해주세요. (단위: 원)");
		life.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("보상금을 입력해주세요. (단위: 원)");
		life.setInsuranceMoney(sc.nextInt());
		
		return life;
	}
	
	public void FollowUpInsurance(InsuranceProduct InsuranceProduct) {
		
	}
	
	public InsuranceProducts clone() {
		return (InsuranceProducts) super.clone();
	}

}