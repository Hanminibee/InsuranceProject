
package controller;
import java.util.ArrayList;
import java.util.Scanner;
import entity.Accident;
import entity.ActualExpense;
import entity.Cancer;
import entity.Client;
import entity.CompensationHandle;
import entity.Contract;
import entity.ContractManagement;
import entity.InsuranceProduct;
import entity.InsuranceProducts;
import entity.InsuranceProductsAcceptance;
import entity.Manager;
import list.ClientList;
import list.ClientListImpl;
import list.ContractList;
import list.ContractListImpl;
import list.InsuranceProductList;
import list.InsuranceProductListImpl;
import list.ManagerList;
import list.ManagerListImpl;
import service.ClientService;
import service.ClientServiceImpl;
import service.ContractService;
import service.ContractServiceImpl;
import service.InsuranceProductService;
import service.InsuranceProductServiceImpl;
import service.ManagerService;
import service.ManagerServiceImpl;
import type.ClientJobType;
import type.InsuranceProductType;

public class ConsoleController {
	private Scanner sc;
	private Manager managerLogin;
	private Client clientLogin;
	private InsuranceProduct insuranceProduct;
	ManagerService managerService = new ManagerServiceImpl();
	ManagerList managerList = new ManagerListImpl();
	InsuranceProductService insuranceProductService = new InsuranceProductServiceImpl();
	InsuranceProductList insuranceProductList = new InsuranceProductListImpl();
	ClientService clientService = new ClientServiceImpl();
	ClientList clientList = new ClientListImpl();
	ContractService contractService = new ContractServiceImpl();
	ContractList contractList = new ContractListImpl();
	
	
	
	public ConsoleController() {
		this.sc = new Scanner(System.in);
		this.insuranceProduct = new InsuranceProduct();
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
				if (insuranceProductService.showInsuranceProductIsNotApproval().isEmpty()) {//초기화면에서 보험 목록 보여주는 페이지
					System.out.println("---현재 상품 준비중입니다.---");
					break;
				} else {
					approvalInsuranceMenu();
					return;
				}
			case 4:
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
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
			case 1://managerRegisterMenu로 바꿔야함
				System.out.println(managerService.register() ? "등록이 완료되었습니다." : "등록에 실패하였습니다.");
				break;
			case 2://managerLoginMenu로 바꿔야함
				if (managerLogin == null)
					managerLogin = managerService.login();
				if (managerLogin != null)
					managerWorkMenu();
				else
					System.out.println("등록되지 않은 매니저입니다.");
				break;
			case 3://managerDeleteMenu로 바꿔야함
				System.out.println(managerService.delete() ? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
				break;
			case 4:
				return;
			default:
				System.out.println("잘못된 값을 입력하셨습니다.");
				break;
			}
		}
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

	private void insuranceProductsMenu() {// IP(보험상품개발자)
		InsuranceProducts ip = (InsuranceProducts) managerLogin;
		while (true) {
			System.out.println("\n---InsuranceProductsMenu---");
			System.out.println("1.보험상품 설계");
			System.out.println("2.사후관리");
			System.out.println("3.로그아웃");
			switch (sc.nextInt()) {
			case 1:
				InsuranceProduct developedProduct = ip.designInsurance().developInsurance();//보험상품설계개발
				insuranceProductList.add(developedProduct);
				break;
			case 2:
				followUpInsurance(insuranceProduct);//사후관리 미완성
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	private void insuranceProductsAcceptanceMenu(){//IPA(보험상품승인자)
		InsuranceProductsAcceptance ipa = (InsuranceProductsAcceptance) managerLogin;
		while (true) {
			System.out.println("---InsuranceProductsAcceptanceMenu---");
			System.out.println("1.승인할 보험 선택하기 2.승인된 보험 삭제 3.로그아웃");
			switch (sc.nextInt()) {
			case 1:
				if (insuranceProductService.showInsuranceProductIsNotApproval().isEmpty()) {
					System.out.println("현재 만들어진 보험이 없습니다.");
					return;
				} else
				approvalMenu(insuranceMenu());
				break;
			case 2:
				if (insuranceProductService.showInsuranceProductIsApproval().isEmpty()) {
					System.out.println("현재 승인된 보험이 없습니다.");
					return;
				} else
					System.out.println("--현재 승인된 보험 목록입니다.--");
				int i = 1;
				for (InsuranceProduct insuranceProduct : insuranceProductService.showInsuranceProductIsApproval()) {
					System.out.println(i + ". " + insuranceProduct.getProductName());// 미완성
					i++;
				}
				approvalInsuranceDelete();// 승인한보험삭제
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	
	private void approvalMenu(InsuranceProduct insuranceProduct) {
		System.out.println("\n1.보험승인 2.보험승인거절 3.돌아가기");
		int a = sc.nextInt();
		switch (a) {
		case 1:
			insuranceProduct.setApproval(true);
			System.out.println("승인이 완료되었습니다.");
			return;
		case 2:
			insuranceProductService.showInsuranceProductIsNotApproval().remove(insuranceProduct);
			System.out.println(insuranceProductService.showAllList().size());
			System.out.println("승인이 거절 되었습니다. 목록에서 삭제합니다.");
			return;
		case 3:
			return;
		}
	}
	
	private void approvalInsuranceDelete() {//승인한보험삭제
		System.out.println("--삭제할 보험을 선택해주세요.--");
		int x = sc.nextInt();
		System.out.println("1.삭제하기 2.돌아가기");
		int y = sc.nextInt();
		switch(y) {
		case 1:
			insuranceProductService.showInsuranceProductIsApproval().remove(x-1);
			System.out.println(insuranceProductService.showInsuranceProductIsApproval().size());
			System.out.println("삭제가 완료되었습니다.");
			break;
		case 2:
			return;
		}
	}

	public void followUpInsurance(InsuranceProduct InsuranceProduct) {//사후관리
		System.out.println("보험목록에서 사후관리할 보험을 선택해주세요.");
		insuranceMenu();
		sc.nextInt();
		System.out.println("1.기본보험료 관리 2.납입기간 관리 3.납입주기 관리");
	}

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
		if (contractList.size() > 0) {
			System.out.println("[인수심사 계약 목록]");
			for (int i = 0; i < contractList.size(); i++)
				System.out.println(String.format("%d.%5s%10s", i + 1, contractList.get(i).getClient(),
						contractList.get(i).getInsuranceProduct()));
			System.out.println("인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input - 1);
			this.showClientInfo(contract.getClient());
//			this.showInsuranceProductDetail(contract.getProductName());
			return contract;
		} else {
			System.out.println("현재 심사할 계약이 없습니다.");
			return null;
		}
	}
	
	private void showClientInfo(String clientID) {
		Client client = clientList.search(clientID);
		System.out.println("[고객 정보]");
		System.out.println("이름: " + client.getName());
		System.out.println("나이: " + client.getAge());
		System.out.println("성별: " + (client.isGender() ? "남자" : "여자"));
		System.out.println("직업: " + client.getJob().getJobName());
		System.out.println("암경력: " + client.getMedicalHistory().getClientCancerCareer().getCancerName() + "(본인)"
				+ client.getMedicalHistory().getFamilyCancerCareer().getCancerName() + "(가족)");
		System.out.println("입원내역: " + client.getMedicalHistory().getNumberOfHospitalizations());
		System.out.println("병원진료: " + client.getMedicalHistory().getNumberOfHospitalVisits());
	}
	
	private void contractManagerMenu() {// CM(계약관리자)
		ContractManagement contractManagement = (ContractManagement)managerLogin;
		while (true) {
			System.out.println("\n---ContractManagementMenu---");
			System.out.println("1. 보험계약관리");
			System.out.println("2. 만기계약관리");
			System.out.println("3. 로그아웃");
			switch(sc.nextInt()) {
			case 1 :
				this.insuranceContract(contractManagement);
			case 2 :
				this.expiredInsuranceContract(contractManagement); 
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	private void insuranceContract(ContractManagement contractManagement){
		ArrayList<Contract> allcontractList = contractService.showAllContract();
		while (true) {
			System.out.println("1.고객의 보험 계약 상세 보기");
			System.out.println("2. 인수심사가 완료된 계약 리스트 보기");
			switch(sc.nextInt()) {
			case 1 :
				System.out.println("검색할 고객의 아이디를 입력하세요.");
				String input = sc.nextLine();
				if (allcontractList.contains(input)) {
					this.showContractInsuranceList(contractManagement); }	
				else {
					System.out.println("해당 고객은 만료된 보험이 없습니다.");
			case 2 :	
				ArrayList<Contract> contractList = contractService.selectNotApproval(); 
				for (int i = 0 ;  i<contractList.size(); i++)
				System.out.println(contractList.get(i));
				//
				System.out.println("\n1.인수심사 완료된 계약 최종 승인하기");
				System.out.println("2.돌아가기");
				switch(sc.nextInt()) {
				case 1:
					System.out.println();
					break;
				case 2:
					return;
		}
				}
}
	private void showContractInsuranceList(ContractManagement contractManagement, Contract contract) {
		Client client = clientList.search(contract.Id());
		System.out.printf("[검색한 고객 아이디] :" ,contract.Id());
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("가입한 보험: " + contract.getInsuranceProduct());
		System.out.println("가입한 날짜: " + contract.getInsuranceContractDate());
		System.out.println("만기 날짜:" + contract.getInsuranceExpiryDate());
		System.out.println("계약한 영업사원 이름: " + contract.getSalesPerson());
	}
		
	}
	private void expiredInsuranceContract(ContractManagement contractManagement, Contract contract) {
		ArrayList<Contract> expiredcontractList = contractService.showExpiredInsurance();
		while (true) {
			System.out.println("[고객의 만기된 보험 검색]");
			System.out.println("검색할 고객의 아이디를 입력하세요.");
			String input = sc.nextLine();
			if (expiredcontractList.contains(input)) {
				this.showExpiredInsuranceList(contractManagement); }	
			else {
				System.out.println("해당 고객은 만료된 보험이 없습니다.");
				return;
			}
		}
	}
	private void showExpiredInsuranceList(ContractManagement contractManagement, Contract contract) {
		Client client = clientList.search(contract.Id());
		System.out.printf("[검색한 고객 아이디] :" ,contract.Id());
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("가입한 보험: " + contract.getInsuranceProduct());
		System.out.println("만기 날짜:" + contract.getInsuranceExpiryDate());
		//
		System.out.println("\n1.만기 보험 연장하기");
		System.out.println("2.만기된 고객 삭제하기");
		System.out.println("3.돌아가기");
		switch(sc.nextInt()) {
		case 1:
			System.out.println("만기된 보험을 연장하는 영업사원의 이름 :");
			System.out.println("[만기된 보험 선택]");
			System.out.println("[1.실비보험, 2.암보험, 3.연금보험, 4.종신보험, 5.돌아가기]");
			switch(sc.nextInt()) {
			case 1:
				
			case 2:
				
			case 3 :
				
			case 4 :
				
			case 5 :
				return;
	        }
		case 2:
			
		case 3:
			return;
	}
	}
		
	
	private void compensationHandleMenu() {// CH(보상처리자)
		CompensationHandle compensationHandle = (CompensationHandle) managerLogin;
		while (true) {
			System.out.println("\n---CompensationHandleMenu---");
			System.out.println("1.사고처리");
			System.out.println("2.로그아웃");
			switch (sc.nextInt()) {
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
		while (true) {
			System.out.println("보고싶은 사고의 보험종류를 선택해주세요.");
			System.out.println("[1.실비보험, 2.암보험, 3.연금보험, 4.종신보험, 5.돌아가기]");
			int input = sc.nextInt();
			if (input == 5)
				break;
			ArrayList<Accident> accidentList = contractService.showAccidentListByProductType(InsuranceProductType.values()[input - 1]);
			System.out.println("[사고 목록]");
			int i = 0;
			for (Accident accident : accidentList) {
				Client client = clientList.search(accident.getClientID());
				System.out.println(String.format("%d.%5s%10s%12s", i + 1, client.getName(), accident.getProductName(),
						accident.getReceptionDate().toString()));
				i++;
			}
			System.out.println("상세정보를 보고 싶은 사고의 번호를 입력해주세요.");
			input = sc.nextInt();
			this.showAccidentDetail(compensationHandle, accidentList.get(input - 1));
		}
	}
	
	private void showAccidentDetail(CompensationHandle compensationHandle, Accident accident) {
		Client client = clientList.search(accident.getClientID());
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
				break;
			case 2:
				break;
			case 3:
				managerLogin = null;
				break;
			}
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
				this.clientRegisterMenu();
				break;
			case 2:
				this.clientLoginMenu();
				break;
			case 3:
				this.clientDeleteMenu();
				break;
			case 4:
				return;
			}
		}
	}

	private void clientWorkMenu() {
		System.out.println("1.모든 보험 조회하기 2.가입한 보험 조회하기 3.마이페이지 4.로그아웃");
		switch (sc.nextInt()) {
		case 1:
			approvalInsuranceMenu();
		case 2:
			
		case 3:
			clientMypageMenu();
		case 4:
			clientLogin = null;
			return;
		}
	}
	private void clientRegisterMenu() {
		Client client = new Client();
		System.out.println("[ID]");
		client.setId(sc.nextLine());sc.nextLine();
		System.out.println("비밀번호를 입력하세요");
		client.setPassword(sc.nextLine());
		System.out.println("[이름]");
		client.setName(sc.nextLine());
		System.out.println("[나이]");
		client.setAge(sc.nextInt());
		System.out.println("[Email]");
		client.setEmail(sc.nextLine());
		sc.nextLine();
		System.out.println("[성별 (1.남 2.여)]");
		if (sc.nextInt() == 1) {
			client.setGender(true);
			System.out.println("남자");
		} else {
			client.setGender(false);
			System.out.println("여자");
		}
		System.out.println("[ClientJobType]");
		System.out.println("[1.군인 2.PW 3.AF 4.DRIVER 5.ETC]");
		int input = sc.nextInt();
		ClientJobType.values()[input - 1].getJobName();
		System.out.println(ClientJobType.values()[input - 1].getJobName());
		sc.nextLine();
		System.out.println("주소를 입력하세요.");
		client.setAddress(sc.nextLine());
		System.out.println("핸드폰 번호를 입력하세요.");
		client.setPhoneNumber(sc.nextLine());
		System.out.println("주민등록번호를 입력하세요.");
		client.setResidentRegistrationNumber(sc.nextLine());
		System.out.println("계좌번호를 입력하세요.");
		client.setBankAccountNumber(sc.nextLine());
		
		System.out.println(clientService.register(client) ? "회원가입이 완료되었습니다." : "회원가입에 실패했습니다.");
	}

	private void clientLoginMenu() {
		if (clientLogin == null) {
			System.out.println("--ID를 입력해주세요.--");
			String id = sc.next();
			System.out.println("--Password를 입력해주세요.--");
			String pw = sc.next();
			clientLogin = clientService.login(id, pw);
			System.out.println(clientLogin.getAge());
		}
		if (clientLogin != null) {
			clientWorkMenu();
		} else
			System.out.println("입력하신 정보를 확인해주세요.");
	}
	
	private boolean clientDeleteMenu() {
		System.out.println("--삭제할 고객 ID를 입력해주세요.--");
		String id = sc.next();
		System.out.println("--삭제할 고객 PW를 입력해주세요.--");
		String pw = sc.next();
		
		if(clientList.search(id, pw) != null) {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1.예 2.아니오");
			int a = sc.nextInt();
			switch(a) {
			case 1:
				System.out.println("삭제가 완료되었습니다.");
				return clientList.delete(clientList.search(id, pw));
			case 2:
				return false;
			}
		}else {
			System.out.println("입력한 정보를 다시 확인해주세요.");
		}
		return false;
	}
	
	private void clientMypageMenu() {
	}

	private InsuranceProduct approvalInsuranceMenu() {
		System.out.println("\n---InsuranceList---");
		int i = 1;
		ArrayList<InsuranceProduct> approvalInsuranceProductList = insuranceProductService.showInsuranceProductIsApproval();
		for(InsuranceProduct approvalinsuranceProduct : approvalInsuranceProductList) {
			System.out.println(i+". " + approvalinsuranceProduct.getProductName() +" "+ approvalinsuranceProduct.getInsuranceProductType().getInsuranceName());
			i++;
		}
		System.out.println("상세정보를 볼 보험상품의 번호를 입력해주세요.");
		InsuranceProduct selectApprovalInsurance = approvalInsuranceProductList.get(sc.nextInt()-1);
		this.showInsuranceProductDetail(selectApprovalInsurance);
		return insuranceProduct;
		
	}
	private InsuranceProduct insuranceMenu() {//
		System.out.println("\n---InsuranceList---");
		int i = 1;
		ArrayList<InsuranceProduct> insuranceProductList = insuranceProductService.showAllList();//showAllList로 수정했음
		for(InsuranceProduct insuranceProduct : insuranceProductList) {
			System.out.println(i+". " + insuranceProduct.getProductName() +" "+ insuranceProduct.getInsuranceProductType().getInsuranceName());
			i++;
		}
		System.out.println("상세정보를 볼 보험상품의 번호를 입력해주세요.");
		InsuranceProduct selectInsurance = insuranceProductList.get(sc.nextInt()-1);
		this.showInsuranceProductDetail(selectInsurance);
		return selectInsurance;
		
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
	
	private void actualexpenseInfo() {//미완성
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
