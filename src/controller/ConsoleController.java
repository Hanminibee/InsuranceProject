package controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
import entity.SalesPerson;
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
				this.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
				break;
				
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
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("current: " + df.format(cal.getTime()));
        cal.add(Calendar.YEAR, 10);
        System.out.println("after: " + df.format(cal.getTime()));
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

	private void managerDeleteMenu() {
		System.out.println("[ID]");
		String id = sc.nextLine();
		System.out.println("[Password]");
		String pw = sc.nextLine();
		
		System.out.println(managerService.delete(id, pw) ? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
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
				this.followUpInsurance();//사후관리 미완성
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
			System.out.println("1.승인할 보험 선택하기 2.승인된 보험 삭제 3.금융감독원에게 승인메일 보내기 4.로그아웃");
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
				emailSend();
				break;
			case 4:
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
	
	private void emailSend() {//이메일 보내기 (21.05.18)
		String user = ""; // gmail계정
		String password = ""; // 패스워드

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("hm5395@naver.com")); // 수신자
			message.setSubject("Test"); // 메일 제목을 입력
			message.setText("Test"); // 메일 내용을 입력
			Transport.send(message); // 전송
			System.out.println("Message sent successfully...!!");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
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

	private void modifyInsuranceProduct(InsuranceProduct selectedInsuranceProduct) {
		switch(selectedInsuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			this.developActualExpense(selectedInsuranceProduct);
			break;
		case CANCER:
			this.developCancer(selectedInsuranceProduct);
			break;
		case PENSION:
			this.developPension(selectedInsuranceProduct);
			break;
		case LIFE:
			this.developLife(selectedInsuranceProduct);
			break;
		}

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
		ArrayList<Contract> contractList = contractService.selectByApproval(false);
		if (contractList.size() > 0) {
			System.out.println("[인수심사 계약 목록]");
			for (int i = 0; i < contractList.size(); i++)
				System.out.println(String.format("%d.%5s%10s", i + 1, contractList.get(i).getClient().getId(),
						contractList.get(i).getInsuranceProduct().getProductName()));
			System.out.println("인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input - 1);
			this.showClientInfo(contract.getClient().getId());
//			this.showInsuranceProductDetail(contract.getProductName());
			return contract;
		} else {
			System.out.println("현재 심사할 계약이 없습니다.");
			return null;
		}
	}
	
	private void showClientInfo(String clientID) {
		Client client = clientService.login(clientID, clientID);
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
		// ContractManagement contractManagement = (ContractManagement)managerLogin;
		while (true) {
			System.out.println("\n---ContractManagementMenu---");
			System.out.println("1. 보험계약관리");
			System.out.println("2. 만기계약관리");
			System.out.println("3. 로그아웃");
			switch (sc.nextInt()) {
			case 1:
				this.insuranceContract();
			case 2:
				this.showExpiredInsuranceMenu();
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	
	private void insuranceContract() { // 0525 계약 확인
		while (true) {
			System.out.println("---확인하실 고객 보험계약리스트를 선택해주세요---");
			System.out.println("1. 실비보험");
			System.out.println("2. 암보험");
			System.out.println("3. 종신보험");
			System.out.println("4. 연금보험");
			ArrayList<Contract> contractList = contractService.selectByInsuranceProductType(InsuranceProductType.values()[sc.nextInt()]);
			this.showContractList(contractList);
			if(!contractList.isEmpty()) {
				System.out.println("---상세정보를 확인하실 계약번호를 입력하세요.---");
				this.showContractInfo(contractList.get(sc.nextInt()-1));
			}
			else {
				System.out.println("----계약이 존재하지 않습니다.----");
			}
		}
	}
	
	private void showContractList(ArrayList<Contract> contractList) { // 0525 계약 리스트
		int i = 1;
		for (Contract contract : contractList) {
			System.out.println("계약 번호 : " + i + "고객 이름 : " + contract.getClient().getName() + "보험 이름 : "
					+ contract.getInsuranceProduct().getProductName() + "가입 날짜 : " + contract.getInsuranceContractDate()
					+ "만기 날짜 :" + contract.getInsuranceExpiryDate());
			i++;
		}
	}

	private void showContractInfo(Contract contract) { // 0525 계약 상세정보
		Client client = contract.getClient();
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("가입한 보험: " + contract.getInsuranceProduct());
		System.out.println("가입한 날짜: " + contract.getInsuranceContractDate());
		System.out.println("만기 날짜:" + contract.getInsuranceExpiryDate());
		System.out.println("계약한 영업사원 이름: " + contract.getSalesPerson());
	}
	
	private void showExpiredInsuranceMenu() { // 0525 만기계약 리스트
		while (true) {
			System.out.println("---만기된 계약 리스트를 볼 보험을 선택해주세요.");
			System.out.println("1. 실비보험");
			System.out.println("2. 암보험");
			System.out.println("3. 종신보험");
			System.out.println("4. 연금보험");
			ArrayList<Contract> contractList = contractService.selectByExpiredDate(InsuranceProductType.values()[sc.nextInt()]);
			if(!contractList.isEmpty()) {
				showContractList(contractList);
				System.out.println("---상세정보를 확인하실 계약번호를 입력하세요.---");
				this.showExpiredInsuranceInfo(contractList.get(sc.nextInt()-1));
			}
			else {
				System.out.println("----계약이 존재하지 않습니다.----");
			}
		}
	}

	private void showExpiredInsuranceInfo(Contract contract) { // 0525 만기계약 상세정보
		Client client = contract.getClient();
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("가입한 보험: " + contract.getInsuranceProduct());
		System.out.println("만기 날짜:" + contract.getInsuranceExpiryDate());
		//
		System.out.println("\n1.만기된 계약 삭제하기");
		System.out.println("2.돌아가기");
		switch (sc.nextInt()) {
		case 1:
			contractService.deleteExpiredContract(contract);
		case 2:
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
	
	private void salesPersonMenu() {// 0525 SP(영업사원)
		while (true) {
			System.out.println("\n---salesPersonMenu---");
			System.out.println("1.영업 활동 관리");
			System.out.println("2.모든 보험 조회");
			System.out.println("4.로그아웃");
			switch (sc.nextInt()) {
			case 1:
				ArrayList<Contract> newList = contractService.searchBySalesPerson(managerLogin.getId());
				this.showContractList(newList);
				System.out.println("---상세 정보를 볼 계약의 번호를 입력하세요.---");
				this.showContractInfo(newList.get(sc.nextInt()-1));
				break;
			case 2:
				InsuranceProduct selectInsuranceProduct = this.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
				this.showInsuranceProductDetail(selectInsuranceProduct);
				this.registeInsuranceProduct(selectInsuranceProduct);
				break;
			case 3:
				managerLogin = null;
				break;
			}
		}
	}
	
	private void registeInsuranceProduct(InsuranceProduct selectInsuranceProduct) { // 0525 보험 계약 메서드
		System.out.println("---해당 보험을 가입하시겠습니까?---");
		System.out.println("1. 네" + "2. 아니요");
		switch(sc.nextInt()) {
		case 1:
			this.clientLoginMenu();
			Contract contract = new Contract () ;
			contract.setClient(clientLogin);
			contract.setInsuranceProduct(selectInsuranceProduct);
			contract.setInsuranceContractDate(new Date());
			contract.setInsuranceExpiryDate(selectInsuranceProduct.getPaymentPeriod());
			contract.setSalesPerson((SalesPerson)managerLogin);
			contractService.registerInsuracneProduct(contract);
			clientLogin = null;
		case 2:
			return;
		}
	}
	
	
	
	private void clientWorkMenu() {//미완성
		System.out.println("1.모든 보험 조회하기 2.가입한 보험 조회하기 3.로그아웃");
		switch (sc.nextInt()) {
		case 1:
			if(insuranceMenu(insuranceProductService.showInsuranceProductIsApproval()) == null) {
				System.out.println("\n이전 페이지로 돌아갑니다.");
				return;
			}else
			contractRegisterClient();
		case 2:
			signUpInsuranceProductMenu();
		case 3:
			clientLogin = null;
			return;
		}
	}
	
	private void contractRegisterClient() {// 미완성
		System.out.println("/n1.가입하기 2.돌아가기");
		int a = sc.nextInt();
		switch (a) {
			case 1 :// 계약 리스트에 넣기

				break;
			case 2 :
				return;
		}
	}
	
	private void signUpInsuranceProductMenu() {//미완성
		
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

	private void clientRegisterMenu() {
		sc.nextLine();
		Client client = new Client();
		//clientService.checkClientID(client.getId()) == null
		System.out.println("[ID]");
		client.setId(sc.nextLine());
		
		if(clientService.checkClientID(client.getId()) !=null) {
			System.out.println("이미 가입된 ID입니다. 다시 입력해주세요.");
			client.setId(sc.nextLine());
		}
		System.out.println(client.getId());
		System.out.println("비밀번호를 입력하세요");
		client.setPassword(sc.nextLine());
		System.out.println(client.getPassword());
		System.out.println("[이름]");
		client.setName(sc.nextLine());
		System.out.println("[나이]");
		client.setAge(sc.nextInt());
		//System.out.println("[Email]");
		//client.setEmail(sc.nextLine());
		//sc.nextLine();
		/*System.out.println("[성별 (1.남 2.여)]");
		if (sc.nextInt() == 1) {
			client.setGender(true);
			System.out.println("남자");
		} else {
			client.setGender(false);
			System.out.println("여자");
		}*/
		/*System.out.println("[ClientJobType]");
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
		client.setBankAccountNumber(sc.nextLine());*/
		
		System.out.println(clientService.register(client) ? "회원가입이 완료되었습니다." : "회원가입에 실패했습니다.");
	}

	private void clientLoginMenu(){
		if (clientLogin == null) {sc.nextLine();
			System.out.println("--ID를 입력해주세요.--");
			String id = sc.nextLine();
			System.out.println("--Password를 입력해주세요.--");
			String pw = sc.nextLine();
			clientLogin = clientService.login(id, pw);
		}
		if (clientLogin != null) {
			clientWorkMenu();
		} else
			System.out.println("입력하신 정보를 확인해주세요.");
	}
	
	private boolean clientDeleteMenu() {
		sc.nextLine();
		System.out.println("--삭제할 고객 ID를 입력해주세요.--");
		String id = sc.nextLine();
		System.out.println("--삭제할 고객 PW를 입력해주세요.--");
		String pw = sc.nextLine();
		
		if(clientService.login(id, pw) != null) {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1.예 2.아니오");
			int a = sc.nextInt();
			switch(a) {
			case 1:
				clientService.delete(id, pw);
				System.out.println("삭제가 완료되었습니다.");
				break;
			case 2:
				return false;
			}
		}else {
			System.out.println("입력한 정보를 다시 확인해주세요.");
		}
		return false;
	}

	private InsuranceProduct insuranceMenu(ArrayList<InsuranceProduct> insuranceProductList) {
		System.out.println("\n---InsuranceList---");
		int i = 1;
		if (insuranceProductList.isEmpty()) {
			System.out.println("현재 준비된 상품이 없습니다.");
		} else {
			for (InsuranceProduct insuranceProduct : insuranceProductList) {
				System.out.println(i + ". " + insuranceProduct.getProductName() + " "
						+ insuranceProduct.getInsuranceProductType().getInsuranceName());
				i++;
			}
				System.out.println("\n보험상품의 번호를 입력해주세요.");
				InsuranceProduct selectInsurance = insuranceProductList.get(sc.nextInt() - 1);
				this.showInsuranceProductDetail(selectInsurance);
				return selectInsurance;
		}
		return null;
	}
	
	private void showInsuranceProductDetail(InsuranceProduct insuranceProduct) {
		System.out.println("보험상품 이름: " + insuranceProduct.getProductName());
		System.out.println("기본보험료: " + insuranceProduct.getBasicInsurancePremium());
		System.out.println("보험 종류: " + insuranceProduct.getInsuranceProductType().getInsuranceName());
		System.out.println("납입기간: " + insuranceProduct.getPaymentPeriod());
		System.out.println("납입주기: " + insuranceProduct.getPaymentCycle());
		switch(insuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE: 
			this.actualexpenseInfo(insuranceProduct); 
			break;
		case CANCER: 
			this.cancerInfo(insuranceProduct); 
			break;
		case PENSION: 
			this.pensionInfo(insuranceProduct); 
			break;
		case LIFE: 
			this.lifeInfo(insuranceProduct); 
			break;
		}
	}
	
	private void actualexpenseInfo(InsuranceProduct insuranceProduct) {// 21.05.19 완성
		ActualExpense actualExpense = (ActualExpense)insuranceProduct;
		System.out.println("제한나이: " + actualExpense.getLimitAge() + " \n보장한도: " + actualExpense.getLimitOfIndemnity() + " \n자기부담금: " + actualExpense.getSelfPayment());
	}

	private void cancerInfo(InsuranceProduct insuranceProduct) {
		Cancer cancer = (Cancer)insuranceProduct;
		System.out.println("제한나이: " + cancer.getLimitAge() + "\n보장내역: " + cancer.getCancerType().getCancerName() + cancer.getCancerType().getRate() + "\n보험금: " + cancer.getInsuranceMoney());
	}
	
	private void pensionInfo(InsuranceProduct insuranceProduct) {
		Pension pension = (Pension)insuranceProduct;
		System.out.println("보장기간: " + pension.getGuaranteedPeriod() + "\n보험금: " + pension.getInsuranceMoney());
	}
	
	private void lifeInfo(InsuranceProduct insuranceProduct) {
		Life life = (Life)insuranceProduct;
		System.out.println("\n필수납입기간" + life.getRequiredPaymentPeriod()+ "\n보험금" + life.getInsuranceMoney());
		
	}

}