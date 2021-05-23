package controller;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Accident;
import entity.ActualExpense;
import entity.Cancer;
import entity.Client;
import entity.CompensationHandle;
import entity.Contract;
import entity.InsuranceProduct;
import entity.Life;
import entity.Manager;
import entity.Pension;
import service.ClientService;
import service.ClientServiceImpl;
import service.ContractService;
import service.ContractServiceImpl;
import service.InsuranceProductService;
import service.InsuranceProductServiceImpl;
import service.ManagerService;
import service.ManagerServiceImpl;
import type.ActualExpenseType;
import type.CancerType;
import type.InsuranceProductType;
import type.ManagerType;

public class ConsoleController {
	private Scanner sc;
	private ClientService clientService;
	private InsuranceProductService insuranceProductService;
	private ManagerService managerService;
	private ContractService contractService;
	
	private Manager managerLogin;
	private Client clientLogin;
	

	public ConsoleController() {
		this.sc = new Scanner(System.in);
		this.clientService = new ClientServiceImpl();
		this.insuranceProductService = new InsuranceProductServiceImpl();
		this.managerService = new ManagerServiceImpl();
		this.contractService = new ContractServiceImpl();
		this.contractService.association(insuranceProductService.getInsuranceProductList());
		this.managerLogin = null;
		this.clientLogin = null;
	}
	
	public void run() {//MainMenu실행
		this.mainMenu();
	}

	private void mainMenu() {//MainMenu
		while (true) {
			System.out.println("\n---MainMenu---");
			System.out.println("1.관리자");
			System.out.println("2.회원");
			System.out.println("3.보험");
			System.out.println("4.끝내기");
			switch (sc.nextInt()) {
			case 1:
				this.managerMenu();
				break;
			case 2:
				this.clientMenu();
				break;
			case 3:
				if (insuranceProductService.showInsuranceProductIsApproval().isEmpty()) {//초기화면에서 보험 목록 보여주는 페이지
					System.out.println("---현재 상품 준비중입니다.---");
				} 
				else {
					this.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
				}
				break;
			case 4:
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
				;
			}
		}
	}

	private void managerMenu() {// ManagerMenu
		while (true) {
			System.out.println("\n---ManagerMenu---");
			System.out.println("1.관리자 등록");
			System.out.println("2.관리자 로그인");
			System.out.println("3.관리자 삭제");
			System.out.println("4.돌아가기");
			switch (sc.nextInt()) {
			case 1:
				this.managerRegisterMenu();
				break;
			case 2:
				this.managerLoginMenu();
				break;
			case 3:
				this.managerDeleteMenu();
				break;
			case 4:
				return;
			default:
				System.out.println("잘못된 값을 입력하셨습니다.");
				break;
			}
		}
	}
	
	private void managerRegisterMenu() {
		System.out.println("[JobPosition]");
		System.out.println("[1.보험상품개발자 2.보험상품승인자 3.U/W 4.계약관리자 5.보상처리자 6.영업사원]");
		int input = sc.nextInt();
		Manager manager = ManagerType.values()[input-1].getManager().clone();
		manager.setJobPosition(ManagerType.values()[input-1]);
		sc.nextLine();
		
		//System.out.println("[이름]");
		//manager.setName(sc.nextLine());
		
		//System.out.println("[나이]");
		//manager.setAge(sc.nextInt());
		//sc.nextLine();
		
		//System.out.println("[전화번호]");
		//manager.setPhoneNumber(sc.nextLine());
		
		System.out.println("[ID]");
		manager.setId(sc.nextLine());
		
		System.out.println("[Password]");
		manager.setPassword(sc.nextLine());
		
		System.out.println(managerService.register(manager) ? "등록이 완료되었습니다." : "등록에 실패하였습니다.");
	}
	
	private void managerLoginMenu() {
		sc.nextLine();
		if (managerLogin == null) {
			System.out.println("[ID]");
			String id = sc.nextLine();
			System.out.println("[Password]");
			String pw = sc.nextLine();
			
			managerLogin = managerService.login(id, pw);
		}
		if (managerLogin != null)
			managerWorkMenu();
		else
			System.out.println("등록되지 않은 매니저입니다.");	
	}

	private void managerWorkMenu() {//managerWorkMenu
		switch (managerLogin.getJobPosition()) {//manager직업마다 다른 메뉴
		case IP:
			insuranceProductsMenu();
			break;
		case IPA:
			insuranceProductsAcceptanceMenu();
			break;
		case UW:
			underWriterMenu();
			break;
		case CM:
			contractManagerMenu();
			break;
		case CH:
			compensationHandleMenu();
			break;
		case SP:
			salesPersonMenu();
			break;
		}
	}
	
	private void managerDeleteMenu() {
		System.out.println("[ID]");
		String id = sc.nextLine();
		System.out.println("[Password]");
		String pw = sc.nextLine();
		
		System.out.println(managerService.delete(id, pw) ? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
	}

	private void insuranceProductsMenu() {// IP(보험상품개발자)
//		InsuranceProducts ip = (InsuranceProducts) managerLogin;
		while (true) {
			System.out.println("\n---InsuranceProductsMenu---");
			System.out.println("1.보험상품 설계");
			System.out.println("2.사후관리");
			System.out.println("3.로그아웃");
			switch (sc.nextInt()) {
			case 1:
				InsuranceProduct developInsuranceProduct = designInsurance();//보험상품설계개발
				insuranceProductService.addInsuranceProduct(developInsurance(developInsuranceProduct));
				break;
			case 2:
				this.followUpInsurance();//사후관리
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	
	private InsuranceProduct developInsurance(InsuranceProduct developInsuranceProduct) {
		switch(developInsuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			return developActualExpense(developInsuranceProduct);
		case CANCER:
			return developCancer(developInsuranceProduct);
		case PENSION:
			return developPension(developInsuranceProduct);
		case LIFE:
			return developLife(developInsuranceProduct);
		default:
			return developInsuranceProduct;
		}
	}
	
	private InsuranceProduct designInsurance() {
		System.out.println("개발할 보험을 선택해주세요.");
		System.out.println("1.실비보험");
		System.out.println("2.암보험");
		System.out.println("3.연금보험");
		System.out.println("4.종신보험");
		int input = sc.nextInt();
		sc.nextLine();
		InsuranceProduct developInsuranceProduct = InsuranceProductType.values()[input-1].getInsuranceProduct().clone();
		developInsuranceProduct.setInsuranceProductType(InsuranceProductType.values()[input-1]);
		return developInsuranceProduct;
	}
	
	private ActualExpense developActualExpense(InsuranceProduct insuranceProduct) {
		ActualExpense actualExpense = (ActualExpense)insuranceProduct;
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
	
	private Cancer developCancer(InsuranceProduct insuranceProduct) {
		Cancer cancer = (Cancer)insuranceProduct;
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
	
	private Pension developPension(InsuranceProduct insuranceProduct) {
		Pension pension = (Pension)insuranceProduct;
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
	
	private Life developLife(InsuranceProduct insuranceProduct) {
		Life life = (Life)insuranceProduct;
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
	
	private void insuranceProductsAcceptanceMenu(){//IPA(보험상품승인자)
//		InsuranceProductsAcceptance ipa = (InsuranceProductsAcceptance) managerLogin;
		while (true) {
			System.out.println("---InsuranceProductsAcceptanceMenu---");
			System.out.println("1.승인할 보험 선택하기 2.승인된 보험 삭제 3.로그아웃");
			switch (sc.nextInt()) {
			case 1:
				if (insuranceProductService.showInsuranceProductIsNotApproval().isEmpty()) {
					System.out.println("현재 만들어진 보험이 없습니다.");
					return;
				} else
				approvalMenu(insuranceMenu(insuranceProductService.showInsuranceProductIsNotApproval()));
				break;
			case 2:
				if (insuranceProductService.showInsuranceProductIsApproval().isEmpty()) {
					System.out.println("현재 승인된 보험이 없습니다.");
					return;
				}else 
					System.out.println("--현재 승인된 보험 목록입니다.--");
					approvalInsuranceDelete();//승인한보험삭제
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	
	private void approvalMenu(InsuranceProduct insuranceProduct) {//승인메뉴
		System.out.println("\n1.보험승인 2.보험승인거절 3.돌아가기");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			insuranceProduct.setApproval(true);
			System.out.println("승인이 완료되었습니다.");
			return;
		case 2:
			System.out.println("승인이 거절 되었습니다. 목록에서 삭제합니다.");
			return;
		case 3:
			return;
		}
	}
	
	private void approvalInsuranceDelete() {//승인한보험삭제
		System.out.println("--삭제할 보험을 선택해주세요.--");
		
		System.out.println("1.삭제하기 2.돌아가기");
		switch(sc.nextInt()) {
		case 1:
			
			break;
		case 2:
			return;
		}
	}

	private void followUpInsurance() {//사후관리
		System.out.println("보험목록에서 사후관리할 보험을 선택해주세요.");
		InsuranceProduct selectedInsuranceProduct = this.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
		System.out.println("해당 보험을 수정하시겠습니까? 1.수정하기, 2.뒤로가기");
		int input = sc.nextInt();
		sc.nextLine();
		switch(input) {
		case 1:
			this.modifyInsuranceProduct(selectedInsuranceProduct);
			break;
		case 2:
			return;
		}
		
	}
	
	private void modifyInsuranceProduct(InsuranceProduct insuranceProduct) {
		switch(insuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			this.developActualExpense(insuranceProduct);
			break;
		case CANCER:
			this.developCancer(insuranceProduct);
			break;
		case PENSION:
			this.developPension(insuranceProduct);
			break;
		case LIFE:
			this.developLife(insuranceProduct);
			break;
		}
	}

	private void clientMenu() {// clientMenu
		while (true) {
			System.out.println("\n---ClientMenu---");
			System.out.println("1.회원가입");
			System.out.println("2.회원 로그인");
			System.out.println("3.회원 탈퇴");
			System.out.println("4.돌아가기");
			switch (sc.nextInt()) {
			case 1:
				clientService.register();//clientRegisterMenu로 바꾸기
				break;
			case 2:
				if (clientLogin == null)//로그인도 clientLoginMenu로 바꾸기
					clientLogin = clientService.login();
				if (clientLogin != null)
					clientWorkMenu();
				else
					System.out.println("입력한 정보가 잘못되었습니다.");
				break;
			case 3:
				clientService.delete();//clientDeleteMenu로 바꾸기
				break;
			case 4:
				return;
			}
		}
	}

	private void clientWorkMenu() {
		System.out.println("1.모든 보험 조회하기 2.가입한 보험 조회하기 3.로그아웃");
		switch (sc.nextInt()) {
		case 1:
			this.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
		case 2:
			
		case 3:
			clientLogin = null;
			return;
		}
	}
	
//	private void clientRegisterMenu() {
//		
//	}
	
	private void underWriterMenu() {//UW(UW)
//		UW uw = (UW)managerLogin;
		while(true) {
			System.out.println("\n---UWMenu---");
			System.out.println("1.인수심사하기");
			System.out.println("2.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				this.underwriteClient(this.selectUnderWriteContract());
				break;
			case 2:
				managerLogin = null;
				return;
			}
		}
	}
	
	private void underwriteClient(Contract contract){
		if(contract != null) {
			System.out.println("해당 계약을 승인하시겠습니까? (1. 승인하기, 2. 승인거절)");
			switch(sc.nextInt()) {
			case 1:
				contract.setApproval(true);
				System.out.println("승인이 완료되었습니다.");
				break;
			case 2:
				System.out.println("승인을 거절하였습니다.");
				break;
			}
		}
	}
	
	private Contract selectUnderWriteContract() {
		ArrayList<Contract> contractList = contractService.selectNotApproval();
		if(contractList.size()>0) {
			System.out.println("[인수심사 계약 목록]");
			for(int i = 0; i < contractList.size(); i++)
				System.out.println(String.format("%d.%5s%10s", i+1, contractList.get(i).getClient().getName(), contractList.get(i).getInsuranceProduct().getProductName()));
			
			System.out.println("인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input-1);
			this.showClientInfo(contract.getClient().getId());
//			this.showInsuranceProductDetail(contract.getProductName());
			return contract;
		}else {
			System.out.println("현재 심사할 계약이 없습니다.");
			return null;
		}
		
	}
	
	private void showClientInfo(String clientID) {
		Client client = clientService.search(clientID);
		System.out.println("[고객 정보]");
		System.out.println("이름: " + client.getName());
		System.out.println("나이: " + client.getAge());
		System.out.println("성별: " + (client.isGender()? "남자" : "여자"));
		System.out.println("직업: " + client.getJob().getJobName());
		System.out.println("암경력: " + client.getMedicalHistory().getClientCancerCareer().getCancerName() + "(본인)"
							+ client.getMedicalHistory().getFamilyCancerCareer().getCancerName() + "(가족)");
		System.out.println("입원내역: " + client.getMedicalHistory().getNumberOfHospitalizations());
		System.out.println("병원진료: " + client.getMedicalHistory().getNumberOfHospitalVisits());
	}
	
	private void contractManagerMenu() {//CM(계약관리자)
		//ContractManagement contractManagement = (ContractManagement)managerLogin;
		while(true) {
			System.out.println("\n---ContractManagementMenu---");
			System.out.println("");
		}
	}
	
	private void compensationHandleMenu() {//CH(보상처리자)
		CompensationHandle compensationHandle = (CompensationHandle)managerLogin;
		while(true) {
			System.out.println("\n---CompensationHandleMenu---");
			System.out.println("1.사고처리");
			System.out.println("2.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				this.accidentHandlingMenu(compensationHandle);
				break;
			case 2:
				managerLogin = null;
				break;
			}
		}
	}
	
	private void accidentHandlingMenu(CompensationHandle compensationHandle) {
		while(true) {
			System.out.println("보고싶은 사고의 보험종류를 선택해주세요.");
			System.out.println("[1.실비보험, 2.암보험, 3.연금보험, 4.종신보험, 5.돌아가기]");
			int input = sc.nextInt();
			if(input == 5) break; 
			ArrayList<Accident> accidentList = contractService.showAccidentListByProductType(InsuranceProductType.values()[input-1]);		
			System.out.println("[사고 목록]");
			int i = 0;
			for(Accident accident : accidentList) {
				Client client = accident.getClient();
				System.out.println(String.format("%d.%5s%10s%12s", i+1, client.getName(), accident.getInsuranceProduct().getProductName(), accident.getReceptionDate().toString()));
				i++;
			}
			System.out.println("상세정보를 보고 싶은 사고의 번호를 입력해주세요.");
			input = sc.nextInt();
			this.showAccidentDetail(compensationHandle, accidentList.get(input-1));
		}
	}
	
	private void showAccidentDetail(CompensationHandle compensationHandle, Accident accident) {
		Client client = accident.getClient();
		System.out.println("[상세정보]");
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("접수 내용: " + accident.getAccidentDetail());
		System.out.println("접수 날짜:" + accident.getReceptionDate());
		
		System.out.println("\n1.보험금 입력");
		System.out.println("2.돌아가기");
		switch(sc.nextInt()) {
			case 1:
				System.out.println("보험금을 입력해주세요.");
				System.out.println(compensationHandle.payInsuranceMoney(sc.nextInt(), client)? "보험금 지급이 완료되었습니다." : "보험금 지급에 실패하였습니다.");
				break;
			case 2:
				return;
		}
	}
	
	private void salesPersonMenu() {//SP(영업사원)
		while(true) {
			System.out.println("\n---salesPersonMenu---");
			System.out.println("1.영업 활동 관리");
			System.out.println("2.모든 보험 조회");
			System.out.println("4.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				this.manageClientMenu();
				break;
			case 2:
				this.insuranceProductsMenu();
				break;
			case 3:
				managerLogin = null;
				break;
			}
		}
	}
	
	private void manageClientMenu() {
		
	}
	
	private InsuranceProduct insuranceMenu(ArrayList<InsuranceProduct> insuranceProductList) {
		System.out.println("\n---InsuranceList---");
		int i = 1;
		if(insuranceProductList.isEmpty()) {
			System.out.println("현재 준비된 상품이 없습니다.");
			return null;
		}else {
			for(InsuranceProduct insuranceProduct : insuranceProductList) {
				System.out.println(i+". " + insuranceProduct.getProductName() +" "+ insuranceProduct.getInsuranceProductType().getInsuranceName());
				i++;
			}
			System.out.println("보험상품의 번호를 입력해주세요.");
			InsuranceProduct selectInsurance = insuranceProductList.get(sc.nextInt()-1);
			this.showInsuranceProductDetail(selectInsurance);
			return selectInsurance;
		}
	}
	
	private void showInsuranceProductDetail(InsuranceProduct insuranceProduct) {
		System.out.println("보험상품 이름: " + insuranceProduct.getProductName());
		System.out.println("기본보험료: " + insuranceProduct.getBasicInsurancePremium());
		System.out.println("보험 종류: " + insuranceProduct.getInsuranceProductType().getInsuranceName());
		System.out.println("납입기간: " + insuranceProduct.getPaymentPeriod());
		System.out.println("납입주기: " + insuranceProduct.getPaymentCycle());
		switch(insuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE: 
			this.actualexpenseInfo(); 
			break;
		case CANCER: 
			this.cancerInfo(); 
			break;
		case PENSION: 
			this.pensionInfo();
			break;
		case LIFE: 
			this.lifeInfo(); 
			break;
		}
	}
	
	private void actualexpenseInfo() {
	
	}
	
	private void cancerInfo() {
		
	}
	
	private void pensionInfo() {
		
	}
	
	private void lifeInfo() {
		
	}
	
	private void registerInsuranceMenu(InsuranceProduct insuranceProduct) {
		switch(sc.nextInt()) {
		case 1:
			
			break;
		case 2:
			
			break;
		}
	}
}