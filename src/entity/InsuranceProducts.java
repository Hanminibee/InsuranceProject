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
		System.out.println("--실비보험을 개발합니다.--");
		System.out.println("\n상품명을 입력해주세요.");
		actualExpense.setProductName(sc.nextLine());
		
		System.out.println("기본보험료를 입력하세요.");
		actualExpense.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		actualExpense.setPaymentPeriod(sc.nextInt());
		
		System.out.println("납입주기를 입력해주세요.(단위: 매월 일)");
		actualExpense.setPaymentCycle(sc.nextInt());
		
		System.out.println("제한나이를 입력해주세요. (단위: 만 세)");
		actualExpense.setLimitAge(sc.nextInt());
		
		System.out.println("자기부담금 비율을 입력해주세요.(단위: %)");
		actualExpense.setSelfPayment(sc.nextInt());
		
		//actualExpense.getM_ActualExpenseHistory().setNumberOfHospitalizations(sc.nextInt());
		//실비보험가입때 병원진료 내역적는거
		System.out.println("보장내역을 설정해주세요.");
		System.out.println("1.입원 2.병원진료비 3.약처방비");
		int input = sc.nextInt();
		ActualExpenseType.values()[input-1].getactualexpensename();
		System.out.println(ActualExpenseType.values()[input-1].getactualexpensename());
		
		System.out.println("\n보장금액을 설정해주세요. (최대 ?원)");
		actualExpense.setLimitOfIndemnity(sc.nextInt());
		
		return actualExpense;
	}
	private Cancer developCancer() {
		Cancer cancer = (Cancer)developInsuranceProduct;
		System.out.println("--암보험을 개발합니다.--");
		System.out.println("\n상품명을 입력해주세요.");
		cancer.setProductName(sc.nextLine());
		
		System.out.println("기본보험료를 입력하세요.");
		cancer.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		cancer.setPaymentPeriod(sc.nextInt());
		
		System.out.println("납입주기를 입력해주세요.(단위: 매월 일)");
		cancer.setPaymentCycle(sc.nextInt());
		
		System.out.println("제한나이를 입력해주세요. (단위: 만 세)");
		cancer.setLimitAge(sc.nextInt());
		
		System.out.println("보장내역(보험요율)을 설정해주세요.");
		System.out.println("1.췌장암(1.6) 2.폐암(1.5) 3.위암(1.4) 4.대장암(1.3) 5.간암(1.2) 6.기타(1.1)");
		int input = sc.nextInt();
		CancerType.values()[input-1].getCancerName();
		double rate = CancerType.values()[input-1].getRate();
		System.out.println(CancerType.values()[input-1].getCancerName() + " " + rate);
		
		System.out.println("보험금을 설정해주세요. (최대 ?원)");
		cancer.setInsuranceMoney(sc.nextInt());
		return cancer;
	}
	private Pension developPension() {
		Pension pension = (Pension)developInsuranceProduct;
		System.out.println("--연금보험을 개발합니다.--");
		System.out.println("상품명을 입력해주세요.");
		pension.setProductName(sc.nextLine());
		
		System.out.println("기본보험료를 입력해주세요.(단위: 원)");
		pension.setBasicInsurancePremium(sc.nextInt());	
		
		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		pension.setPaymentPeriod(sc.nextInt());
		
		System.out.println("납입주기를 입력해주세요.(단위: 매월 x일)");
		pension.setPaymentCycle(sc.nextInt());
		
		System.out.println("보장기간 입력해주세요. (단위: 만 나이)");
		pension.setGuaranteedPeriod(sc.nextInt());
		
		System.out.println("보험금을 입력해주세요. (단위: 매월 x원)");
		pension.setInsuranceMoney(sc.nextInt());
		
		return pension;
	}
	private Life developLife() {
		Life life = (Life)developInsuranceProduct;
		System.out.println("--종신보험을 개발합니다.--");
		System.out.println("상품명을 입력해주세요.");
		life.setProductName(sc.nextLine());
		
		System.out.println("기본보험료를 입력해주세요. (단위: 원)");
		life.setBasicInsurancePremium(sc.nextInt());
		
		System.out.println("납입기간을 입력해주세요.(단위: 년)");
		life.setPaymentPeriod(sc.nextInt());
		
		System.out.println("필수납입기간을 입력해주세요.(단위: 매월 일)");
		life.setRequiredPaymentPeriod(sc.nextInt());
		
		System.out.println("납입주기 입력해주세요. (단위: 만 나이)");
		life.setPaymentCycle(sc.nextInt());
		
		System.out.println("보험금을 입력해주세요. (단위: 매월 원)");
		life.setInsuranceMoney(sc.nextInt());
		
		return life;
	}
	
	public InsuranceProducts clone() {
		return (InsuranceProducts) super.clone();
	}
}