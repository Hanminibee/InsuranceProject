package entity;

import java.util.ArrayList;

import type.CancerType;
import type.InsuranceProductType;

public class InsuranceProducts extends Manager {

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
		this.developInsuranceProduct = InsuranceProductType.values()[input - 1]
				.getInsuranceProduct().clone();
		developInsuranceProduct.setInsuranceProductType(
				InsuranceProductType.values()[input - 1]);
		return this;
	}

	public InsuranceProduct developInsurance() {
		switch (developInsuranceProduct.getInsuranceProductType()) {
			case ACTUALEXPENSE :
				return developActualExpense();
			case CANCER :
				return developCancer();
			case PENSION :
				return developPension();
			case LIFE :
				return developLife();
			default :
				return developInsuranceProduct;
		}
	}

	private ActualExpense developActualExpense() {
		int[] limitOfIndemnity = new int[3];
		ActualExpense actualExpense = (ActualExpense) developInsuranceProduct;

		System.out.println("상품명을 입력해주세요.");
		actualExpense.setProductName(sc.nextLine());

		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		actualExpense.setPaymentPeriod(sc.nextInt());

		System.out.println("납입주기를 입력해주세요.(단위: 매월 x일)");
		actualExpense.setPaymentCycle(sc.nextInt());

		System.out.println("기본보험료를 입력해주세요. (단위: 원)");
		actualExpense.setBasicInsurancePremium(sc.nextInt());

		System.out.println("제한나이를 입력해주세요. (단위: 만 x세)");
		actualExpense.setLimitAge(sc.nextInt());

		System.out.println("자기부담금 비율을 입력해주세요.(단위: %)");
		actualExpense.setSelfPayment(sc.nextInt());

		System.out.println("입원에 대한 보장한도를 입력해주세요.(단위: 원)");
		limitOfIndemnity[0] = sc.nextInt();

		System.out.println("병원진료에 대한 보장한도를 입력해주세요.(단위: 원)");
		limitOfIndemnity[1] = sc.nextInt();

		System.out.println("약처방에 대한 보장한도를 입력해주세요.(단위: 원)");
		limitOfIndemnity[2] = sc.nextInt();
		actualExpense.setLimitOfIndemnity(limitOfIndemnity);

		return actualExpense;
	}

	private Cancer developCancer() {
		ArrayList<CancerType> guaranteedTypeList = new ArrayList<CancerType>();
		int[] insuranceMoneys = new int[CancerType.values().length];
		Cancer cancer = (Cancer) developInsuranceProduct;

		System.out.println("상품명을 입력해주세요.");
		cancer.setProductName(sc.nextLine());

		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		cancer.setPaymentPeriod(sc.nextInt());

		System.out.println("납입주기를 입력해주세요.(단위: 매월 x일)");
		cancer.setPaymentCycle(sc.nextInt());

		System.out.println("기본보험료를 입력해주세요. (단위: 원)");
		cancer.setBasicInsurancePremium(sc.nextInt());

		System.out.println("제한나이를 입력해주세요. (단위: 만 x세)");
		cancer.setLimitAge(sc.nextInt());

		int i = 0;
		while (true) {
			System.out.println("\n해당 보험이 보장할 암의 종류를 선택해주세요.");
			System.out.println("1.췌장암, 2.폐암, 3.위암, 4.대장암, 5.간암, 6.기타, 7.선택 종료");
			System.out.print("현재 선택된 암:");

			for (CancerType selectedType : guaranteedTypeList)
				System.out.print(" [" + selectedType.getCancerName() + "]");
			System.out.println();
			int input = sc.nextInt();
			if (i == 0 && input == 7) {
				System.out.println("하나 이상은 선택하셔야합니다.");
				continue;
			} else if (input == 7)
				break;

			CancerType selectedType = CancerType.values()[input - 1];
			if (!guaranteedTypeList.contains(selectedType)) {
				guaranteedTypeList.add(selectedType);
				System.out.println("해당 암의 보상금을 입력해주세요.");
				insuranceMoneys[i] = sc.nextInt();
				i++;
			} else
				System.out.println("이미 선택된 암종류를 입력하셨습니다.");
			if (guaranteedTypeList.size() > 5)
				break;
		}

		CancerType[] guaranteedType = guaranteedTypeList
				.toArray(new CancerType[guaranteedTypeList.size()]);
		cancer.setGuaranteedType(guaranteedType);
		cancer.setInsuranceMoneys(insuranceMoneys);

		return cancer;
	}

	private Pension developPension() {
		Pension pension = (Pension) developInsuranceProduct;
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
		Life life = (Life) developInsuranceProduct;
		System.out.println("상품명을 입력해주세요.");
		life.setProductName(sc.nextLine());

		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		life.setPaymentPeriod(sc.nextInt());

		System.out.println("납입주기를 입력해주세요.(단위: 매월 x일)");
		life.setRequiredPaymentPeriod(sc.nextInt());

		System.out.println("기본보험료를 입력해주세요. (단위: 원)");
		life.setPaymentCycle(sc.nextInt());

		System.out.println("보장기간 입력해주세요. (단위: 만 나이)");
		life.setBasicInsurancePremium(sc.nextInt());

		System.out.println("보상금액을 입력해주세요. (단위: 매월 x원)");
		life.setInsuranceMoney(sc.nextInt());

		return life;
	}

	public void FollowUpInsurance(InsuranceProduct InsuranceProduct) {
		
	}

	public InsuranceProducts clone() {
		return (InsuranceProducts) super.clone();
	}

}