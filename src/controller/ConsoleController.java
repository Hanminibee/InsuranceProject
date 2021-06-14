package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entity.Accident;
import entity.ActualExpense;
import entity.Cancer;
import entity.Client;
import entity.CompensationHandle;
import entity.Contract;
import entity.ContractManagement;
import entity.InsuranceProduct;
import entity.Life;
import entity.Manager;
import entity.Pension;
import entity.SalesPerson;
import service.AssociationObject;
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
import type.ClientJobType;
import type.InsuranceProductType;
import type.ManagerType;

public class ConsoleController {
	private Scanner sc;
	private Manager managerLogin;
	private Client clientLogin;
	private ManagerService managerService;
	private InsuranceProductService insuranceProductService;
	private ClientService clientService;
	private ContractService contractService;

	public ConsoleController() {
		this.sc = new Scanner(System.in);
		this.managerService = new ManagerServiceImpl();
		this.insuranceProductService = new InsuranceProductServiceImpl();
		this.clientService = new ClientServiceImpl();
		this.contractService = new ContractServiceImpl();
		AssociationObject associationObject = new AssociationObject();
		associationObject.setInsuranceProductList(
				insuranceProductService.getInsuranceProductList());
		associationObject.setClientList(clientService.getClientList());
		associationObject
				.setMedicalHistoryList(clientService.getMedicalHistoryList());
		associationObject.setManagerList(managerService.getManagerList());
		this.contractService.association(associationObject);
		this.managerLogin = null;
		this.clientLogin = null;
	}

	public void run() {// MainMenu실행
		this.mainMenu();
	}

	private void mainMenu() {// MainMenu
		while (true) {
			System.out.println("\n---MainMenu---");
			System.out.println("1.관리자");
			System.out.println("2.회원");
			System.out.println("3.보험");
			System.out.println("4.끝내기");
			try {
				int a = sc.nextInt();
				if (a < 1 || a > 4) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				switch (a) {
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
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
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
			try {
				int a = sc.nextInt();
				if(a < 1 || a > 4 ) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				switch (a) {
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
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요");
			}
		}
	}
	
	private void managerRegisterMenu() { //Manager등록메뉴
		while (true) {
			try {
				System.out.println("직종을 입력해주세요.");
				System.out.println("[1.보험상품개발자 2.보험상품승인자 3.U/W 4.계약관리자 5.보상처리자 6.영업사원]");
				int input = sc.nextInt();
				Manager manager = ManagerType.values()[input-1].getManager().clone();
				manager.setJobPosition(ManagerType.values()[input-1]);
				sc.nextLine();
				
				System.out.println("이름을 입력해주세요.");
				manager.setName(sc.nextLine());
				
				System.out.println("나이를 입력해주세요.");
				manager.setAge(sc.nextInt());
				sc.nextLine();
				
				System.out.println("전화번호를 입력해주세요.");
				manager.setPhoneNumber(sc.nextLine());
				
				System.out.println("ID를 입력해주세요.");
				String id = sc.nextLine();
				manager.setId(id);
				
				while(managerService.checkManagerID(manager.getId()) != null) {//중복된 아이디 체크 변경(21.05.31)
					System.out.println("이미 가입된 ID입니다. 다시 입력해주세요.");
					manager.setId(sc.nextLine());
				}
				
				System.out.println("비밀번호를 입력해주세요.");
				manager.setPassword(sc.nextLine());
				System.out.println(managerService.register(manager) ? "등록이 완료되었습니다." : "등록에 실패하였습니다.");
				
				break;
			}catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("잘못된 형식의 값을 입력하셨습니다. 다시 입력해주세요.");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void managerLoginMenu() {
		sc.nextLine();
		if (managerLogin == null) {
			System.out.println("ID를 입력해주세요.");
			String id = sc.nextLine();
			System.out.println("비밀번호를 입력해주세요.");
			String pw = sc.nextLine();

			managerLogin = managerService.login(id, pw);
		}
		if (managerLogin != null)
			managerWorkMenu();
		else
			System.out.println("입력한 정보를 확인해주세요.");
	}

	private void managerDeleteMenu() {
		sc.nextLine();
		System.out.println("ID를 입력해주세요.");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력해주세요.");
		String pw = sc.nextLine();
		
		System.out.println(managerService.delete(id, pw) ? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
		managerLogin = null;
	}

	private void managerWorkMenu() {// managerWorkMenu
		switch (managerLogin.getJobPosition()) {// manager직업마다 다른 메뉴
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

	private void insuranceProductsMenu() {
		try {
			while (true) {
				System.out.println("\n---InsuranceProductsMenu---");
				System.out.println("1.보험상품 설계");
				System.out.println("2.사후관리");
				System.out.println("3.로그아웃");
				switch (sc.nextInt()) {
				case 1:
					InsuranceProduct developInsuranceProduct = designInsurance();//보험상품설계개발
					if(developInsuranceProduct != null) {
						developInsuranceProduct = developInsurance(developInsuranceProduct);
						if(developInsuranceProduct != null)
							insuranceProductService.addInsuranceProduct(developInsuranceProduct);
						else
							System.out.println("보험 생성에 실패하였습니다.");
					}
					else
						System.out.println("보험 생성에 실패하였습니다.");
					break;	
				case 2:
					this.followUpInsurance();
					break;
				case 3:
					managerLogin = null;
					return;
				}
			}
		}catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자를 입력해주세요.");
		}
	}

	private InsuranceProduct developInsurance(InsuranceProduct developInsuranceProduct) {
		switch (developInsuranceProduct.getInsuranceProductType()) {
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
		System.out.println("\n개발할 보험을 선택해주세요.");
		System.out.println("1.실비보험");
		System.out.println("2.암보험");
		System.out.println("3.연금보험");
		System.out.println("4.종신보험");
		while (true) {
			try {
				int input = sc.nextInt();
				sc.nextLine();
				if (input <= 4) {
					InsuranceProduct developInsuranceProduct = InsuranceProductType.values()[input - 1]
							.getInsuranceProduct().clone();
					developInsuranceProduct.setInsuranceProductType(InsuranceProductType.values()[input - 1]);
					return developInsuranceProduct;
				} else {
					System.out.println("항목을 제대로 선택해주세요.");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	private ActualExpense developActualExpense(InsuranceProduct insuranceProduct) {
		try {
			ActualExpense actualExpense = (ActualExpense) insuranceProduct;
			System.out.println("--실비보험을 개발합니다.--");
			System.out.println("\n상품명을 입력해주세요.");
			actualExpense.setProductName(sc.nextLine());
			while (insuranceProductService.searchInsuranceProduct(actualExpense.getProductName()) != null) {
				System.out.println("이미 존재하는 제품명입니다. 다시 입력해주세요.");
				actualExpense.setProductName(sc.nextLine());
			}
			System.out.println("기본보험료를 입력하세요. (단위: ?원)");
			actualExpense.setBasicInsurancePremium(sc.nextInt());

			System.out.println("납입기간을 입력해주세요. (단위: ?년)");
			actualExpense.setPaymentPeriod(sc.nextInt());

			System.out.println("납입주기를 입력해주세요. (단위: 매월 ?일)");
			actualExpense.setPaymentCycle(sc.nextInt());

			System.out.println("제한나이를 입력해주세요. (단위: ?세)");
			actualExpense.setLimitAge(sc.nextInt());

			System.out.println("자기부담금 비율을 입력해주세요. (단위: %)");
			actualExpense.setSelfPayment(sc.nextInt());
			
			System.out.println("보장내역을 설정해주세요.");
			System.out.println("1.입원 2.병원진료비 3.약처방비");
			int input = sc.nextInt();
			actualExpense.setActualExpenseType(ActualExpenseType.values()[input - 1]);
			System.out.println(ActualExpenseType.values()[input - 1].getactualexpensename());

			System.out.println("\n보장금액을 설정해주세요. (단위: 최대 ?원)");
			actualExpense.setLimitOfIndemnity(sc.nextInt());

			return actualExpense;
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("잘못된 값을 입력하셨습니다.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}
		return null;
	}

	private Cancer developCancer(InsuranceProduct insuranceProduct) {
		try {
			Cancer cancer = (Cancer) insuranceProduct;
			System.out.println("--암보험을 개발합니다.--");
			System.out.println("\n상품명을 입력해주세요.");
			cancer.setProductName(sc.nextLine());
			while (insuranceProductService.searchInsuranceProduct(cancer.getProductName()) != null) {
				System.out.println("이미 존재하는 제품명입니다. 다시 입력해주세요.");
				cancer.setProductName(sc.nextLine());
			}
			System.out.println("기본보험료를 입력하세요. (단위: ?원)");
			cancer.setBasicInsurancePremium(sc.nextInt());

			System.out.println("납입기간을 입력해주세요. (단위: ?년)");
			cancer.setPaymentPeriod(sc.nextInt());

			System.out.println("납입주기를 입력해주세요. (단위: 매월 ?일)");
			cancer.setPaymentCycle(sc.nextInt());

			System.out.println("제한나이를 입력해주세요. (단위: ?세)");
			cancer.setLimitAge(sc.nextInt());

			System.out.println("보장내역(보험요율)을 설정해주세요.");
			System.out.println("1.췌장암(1.6) 2.폐암(1.5) 3.위암(1.4) 4.대장암(1.3) 5.간암(1.2) 6.기타(1.1)");
			int input = sc.nextInt();
			if (input == 7)
				throw new IndexOutOfBoundsException();
			cancer.setGuaranteedType(CancerType.values()[input - 1]);
			double rate = CancerType.values()[input - 1].getRate();
			System.out.println(CancerType.values()[input - 1].getCancerName() + " " + rate);
			System.out.println("보험금을 설정해주세요. (단위: 최대 ?원)");
			cancer.setInsuranceMoney(sc.nextInt());
			return cancer;
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("잘못된 값을 입력하셨습니다.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}
		return null;
	}

	private Pension developPension(InsuranceProduct insuranceProduct) {
		try {
			Pension pension = (Pension) insuranceProduct;
			System.out.println("--연금보험을 개발합니다.--");
			System.out.println("상품명을 입력해주세요.");
			pension.setProductName(sc.nextLine());
			while (insuranceProductService.searchInsuranceProduct(pension.getProductName()) != null) {
				System.out.println("이미 존재하는 제품명입니다. 다시 입력해주세요.");
				pension.setProductName(sc.nextLine());
			}
			System.out.println("기본보험료를 입력해주세요. (단위: ?원)");
			pension.setBasicInsurancePremium(sc.nextInt());

			System.out.println("납입기간을 입력해주세요. (단위: ?년)");
			pension.setPaymentPeriod(sc.nextInt());

			System.out.println("납입주기를 입력해주세요. (단위: 매월 ?일)");
			pension.setPaymentCycle(sc.nextInt());

			System.out.println("보장기간 입력해주세요. (단위: ?세)");
			pension.setGuaranteedPeriod(sc.nextInt());

			System.out.println("보험금을 설정해주세요. (단위: ?원)");
			pension.setInsuranceMoney(sc.nextInt());

			return pension;
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("잘못된 값을 입력하셨습니다.");
		}
		return null;
	}

	private Life developLife(InsuranceProduct insuranceProduct) {
		try {
			Life life = (Life) insuranceProduct;
			System.out.println("--종신보험을 개발합니다.--");
			System.out.println("상품명을 입력해주세요.");
			life.setProductName(sc.nextLine());
			while (insuranceProductService.searchInsuranceProduct(life.getProductName()) != null) {
				System.out.println("이미 존재하는 제품명입니다. 다시 입력해주세요.");
				life.setProductName(sc.nextLine());
			}
			System.out.println("기본보험료를 입력해주세요. (단위: ?원)");
			life.setBasicInsurancePremium(sc.nextInt());

			System.out.println("납입기간을 입력해주세요. (단위: ?년)");
			life.setPaymentPeriod(sc.nextInt());

			System.out.println("납입주기 입력해주세요. (단위: 매월 ?일)");
			life.setPaymentCycle(sc.nextInt());

			System.out.println("필수납입기간을 입력해주세요. (단위: ?년)");
			life.setRequiredPaymentPeriod(sc.nextInt());

			System.out.println("보험금을 설정해주세요. (단위: ?원)");
			life.setInsuranceMoney(sc.nextInt());

			return life;
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("잘못된 값을 입력하셨습니다.");
		}
		return null;
	}

	private void insuranceProductsAcceptanceMenu() {// IPA(보험상품승인자)
		while (true) {
			try {
				System.out.println("---InsuranceProductsAcceptanceMenu---");
				System.out.println("1.승인할 보험 선택하기 2.승인된 보험 삭제 3.금융감독원에게 승인메일 보내기 4.로그아웃");
				int a = sc.nextInt();
				if (a < 1 || a > 5) {
					System.out.println("올바른 값을 입력해주세요.\n");
				}
				switch (a) {
				case 1:
					if (insuranceProductService.showInsuranceProductIsNotApproval().isEmpty()) {
						System.out.println("현재 만들어진 보험이 없습니다.");
						return;
					} else
						approvalMenu(insuranceMenu(insuranceProductService.showInsuranceProductIsNotApproval()));
					break;
				case 2:
					approvalInsuranceDelete();// 승인한보험삭제
					break;
				case 3:
					emailSend();
					break;
				case 4:
					managerLogin = null;
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.\n");
			}
		}
	}

	private void approvalMenu(InsuranceProduct insuranceProduct) {
		if(insuranceProduct == null) return;
		while (true) {
			try {
				System.out.println("\n1.보험승인 2.보험승인거절 3.돌아가기");
				int input = sc.nextInt();
				if (input < 1 || input > 3) 
					System.out.println("올바른 값을 입력해주세요.");
				switch (input) {
				case 1:
					insuranceProduct.setApproval(true);
					System.out.println(insuranceProductService.modifyInsuranceProduct(insuranceProduct)? 
							"승인이 완료되었습니다." : "승인에 실패하였습니다.");
					return;
				case 2:
					System.out.println(insuranceProductService.deleteInsuranceProduct(insuranceProduct)? 
							"승인이 거절 되었습니다. 목록에서 삭제합니다." : "승인거절에 문제가 발생하였습니다.");
					return;
				case 3:
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.\n");
			}
		}
	}

	private void approvalInsuranceDelete() {
		while(true) {
			try {
				if (insuranceProductService.showInsuranceProductIsApproval().isEmpty()) {
					System.out.println("현재 승인된 보험이 없습니다.");
					return;
				} else
					System.out.println("--현재 승인된 보험 목록입니다.--");
				int i = 1;
				ArrayList<InsuranceProduct> list = insuranceProductService.showInsuranceProductIsApproval();
				for (InsuranceProduct insuranceProduct : list) {
					System.out.println(i + ".상품명: " + insuranceProduct.getProductName() + " 보험종류: "
							+ insuranceProduct.getInsuranceProductType().getInsuranceName());
					i++;
				}
				System.out.println("\n--삭제할 보험을 선택해주세요.--");
				int x = sc.nextInt();
				InsuranceProduct deleteInsuranceProduct = list.get(x-1); 
				System.out.println("1.삭제하기 2.돌아가기");
				int y = sc.nextInt();
				switch (y) {
				case 1:
					System.out.println(insuranceProductService.deleteInsuranceProduct(deleteInsuranceProduct)? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
					return;
				case 2:
					return;
				}
			}catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.\n");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void emailSend() {// 이메일 보내기
		try {
			System.out.println("보험상품승인자의 이메일을 입력해주세요.(Naver Email을 입력해주세요.)");
			String user = sc.next(); // Naver Email 계정 입력
			System.out.println("보험상품승인자의 이메일 Password를 입력해주세요.");
			String password = sc.next(); // Naver Email 계정 패스워드 입력
			System.out.println("금융감독원 이메일을 입력해주세요.");
			String fssEmail = sc.next(); // 금융감독원 email 입력
			System.out.println("메일 제목을 입력해주세요.");
			sc.nextLine();
			String mailTitle = sc.nextLine(); // 메일 제목 입력
			System.out.println("메일 내용을 입력해주세요.");
			String mailContent = sc.nextLine(); // 메일 내용 입력
			System.out.println("메일을 보내는 중입니다. 잠시만 기다려주세요...!");

			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", 587);
			prop.put("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(fssEmail));
			message.setSubject(mailTitle);
			message.setText(mailContent); // send the message
			Transport.send(message);
			System.out.println("Message sent successfully...!!");
		} catch (MessagingException e) {
			System.out.println("메일전송에 실패하였습니다. 입력한 내용을 확인해주세요.");
		}
	}

	private void followUpInsurance() {
		System.out.println("보험목록에서 사후관리할 보험을 선택해주세요.");
		InsuranceProduct selectedInsuranceProduct = this.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
		if(selectedInsuranceProduct == null) {
			return;
		}
		System.out.println("\n해당 보험을 수정하시겠습니까? \n1.수정하기 2.뒤로가기");
		
		int input = sc.nextInt();
		sc.nextLine();
		switch(input) {
		case 1:
			System.out.println(this.modifyInsuranceProduct(selectedInsuranceProduct)? "보험 수정에 성공했습니다.":"보험 수정에 실패하였습니다.");;
			break;
		case 2:
			return;
		default :
			System.out.println("잘못된 값을 입력하셨습니다.");
		}
	}

	private boolean modifyInsuranceProduct(InsuranceProduct selectedInsuranceProduct) {
		switch(selectedInsuranceProduct.getInsuranceProductType()) {
			case ACTUALEXPENSE:
				return insuranceProductService.modifyInsuranceProduct(this.developActualExpense(selectedInsuranceProduct));
			case CANCER:
				return insuranceProductService.modifyInsuranceProduct(this.developCancer(selectedInsuranceProduct));
			case PENSION:
				return insuranceProductService.modifyInsuranceProduct(this.developPension(selectedInsuranceProduct));
			case LIFE:
				return insuranceProductService.modifyInsuranceProduct(this.developLife(selectedInsuranceProduct));
			}
			return false;
	}

	private void underWriterMenu() {
		while (true) {
			try {
				System.out.println("\n---UWMenu---");
				System.out.println("1.인수심사하기");
				System.out.println("2.로그아웃");
				int a = sc.nextInt();
				if (a < 1 || a > 2) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				switch (a) {
				case 1:
					this.underwriteClient(this.selectUnderWriteContract());
					break;
				case 2:
					managerLogin = null;
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	private void underwriteClient(Contract contract){
		if (contract != null) {
			while (true) {
				try {
					System.out.println("해당 계약을 승인하시겠습니까?");
					System.out.println("1.승인하기  2.승인거절");
					int input = sc.nextInt();
					if (input < 1 || input > 2) {
						System.out.println("올바른 값을 입력해주세요.");
					}
					switch (input) {
					case 1:
						contract.setApproval(true);
						System.out.println(contractService.modifyContract(contract)? "승인이 완료되었습니다." : "승인에 실패하였습니다.");
						return;
					case 2:
						System.out.println(contractService.deleteContract(contract)? "승인을 거절하였습니다." : "승인 거절에 실패하였습니다.");
						return;
					}
				} catch (InputMismatchException e) {
					sc.next();
					System.out.println("숫자를 입력해주세요.");
				}
			}
		}
	}

	private Contract selectUnderWriteContract() {
		ArrayList<Contract> contractList = contractService.selectByApproval(false);
		if (contractList.size() > 0) {
			System.out.println("---인수심사 계약 목록---");
			for (int i = 0; i < contractList.size(); i++)
				System.out.println((i + 1 + ". ") + contractList.get(i).getClient().getName() + " "
						+ contractList.get(i).getInsuranceProduct().getInsuranceProductType().getInsuranceName());
			System.out.println("\n인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input - 1);
			this.showClientInfo(contract);
			return contract;
		} else {
			System.out.println("현재 심사할 계약이 없습니다.");
			return null;
		}
	}

	private void showClientInfo(Contract contract) {
		Client client = contract.getClient();
		System.out.println("---고객 정보---");
		System.out.println("이름: " + client.getName());
		System.out.println("나이: " + client.getAge());
		System.out.println("성별: " + (client.isGender() ? "남자" : "여자"));
		System.out.println("직업: " + client.getJob().getJobName());
		switch (contract.getInsuranceProduct().getInsuranceProductType()) {
		case ACTUALEXPENSE:
			System.out.println("입원내역: " + client.getMedicalHistory().getNumberOfHospitalizations());
			System.out.println("병원진료: " + client.getMedicalHistory().getNumberOfHospitalVisits());
			break;
		case CANCER:
			System.out.println("암경력: " + client.getMedicalHistory().getClientCancerCareer().getCancerName() + "(본인)"
					+ client.getMedicalHistory().getFamilyCancerCareer().getCancerName() + "(가족)");
			break;
		case LIFE:
			break;
		case PENSION:
			break;
		default:
			break;
		}
	}

	private void contractManagerMenu() {// CM(계약관리자)
		ContractManagement contractManagement = (ContractManagement) managerLogin;
		while (true) {
			try {
				System.out.println("\n---ContractManagementMenu---");
				System.out.println("1. 보험계약관리");
				System.out.println("2. 만기계약관리");
				System.out.println("3. 로그아웃");
				int a = sc.nextInt();
				if (a < 1 || a > 3) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				switch (a) {
				case 1:
					this.insuranceContract(contractManagement);
					break;
				case 2:
					this.showExpiredInsuranceMenu();
					break;
				case 3:
					managerLogin = null;
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}

		}
	}

	private void insuranceContract(ContractManagement contractManagement) {
		while (true) {
			try {
				System.out.println("\n---확인하실 고객 보험계약리스트를 선택해주세요---");
				System.out.println("1. 실비보험");
				System.out.println("2. 암보험");
				System.out.println("3. 연금보험");
				System.out.println("4. 종신보험");
				System.out.println("5. 돌아가기");
				int a = sc.nextInt();
				if (a == 5) {
					return;
				}
				if (a < 1 || a > 5) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				ArrayList<Contract> contractList = contractService
						.selectByInsuranceProductType(InsuranceProductType.values()[a - 1]);
				showContractList(contractList);
				if (!contractList.isEmpty()) {
					System.out.println("---상세정보를 확인하실 계약번호를 입력하세요.---");
					showContractInfo(contractList.get(sc.nextInt() - 1));
				} else {
					System.out.println("----계약이 존재하지 않습니다.----\n");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	private void showContractList(ArrayList<Contract> contractList) {
		int i = 1;
		for (Contract contract : contractList) {
			System.out.println("계약번호: " + i + " 고객이름: " + contract.getClient().getName() + " 보험이름: "
					+ contract.getInsuranceProduct().getProductName() + " 가입날짜: " + contract.getInsuranceContractDate()
					+ " 만기날짜: " + contract.getInsuranceExpiryDate() + " 승인여부: " + contract.isApproval());
			i++;
		}
	}

	private void showContractInfo(Contract contract) {
		Client client = contract.getClient();
		InsuranceProduct insuranceProduct = contract.getInsuranceProduct();
		Manager manager = contract.getSalesPerson();
		System.out.println("고객이름: " + client.getName());
		System.out.println("고객나이: " + client.getAge());
		System.out.println("가입한 보험: " + insuranceProduct.getInsuranceProductType().getInsuranceName());
		System.out.println("가입한 날짜: " + contract.getInsuranceContractDate());
		System.out.println("만기 날짜: " + contract.getInsuranceExpiryDate());
		System.out.println("계약한 영업사원 이름: " + manager.getName());
	}

	private void showExpiredInsuranceMenu() {
		while (true) {
			try {
				System.out.println("\n---만기된 계약 리스트를 볼 보험을 선택해주세요.");
				System.out.println("1. 실비보험");
				System.out.println("2. 암보험");
				System.out.println("3. 연금보험");
				System.out.println("4. 종신보험");
				System.out.println("5. 돌아가기");
				int a = sc.nextInt();
				if (a == 5) {
					return;
				}
				if (a < 1 || a > 5) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				ArrayList<Contract> contractList = contractService
						.selectByExpiredDate(InsuranceProductType.values()[a - 1]);
				showContractList(contractList);
				if (!contractList.isEmpty()) {
					System.out.println("상세정보를 확인하실 계약번호를 입력하세요.");
					showExpiredInsuranceInfo(contractList.get(sc.nextInt() - 1));
				} else {
					System.out.println("계약이 존재하지 않습니다.");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	private void showExpiredInsuranceInfo(Contract contract) {
		Client client = contract.getClient();
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("가입한 보험: " + contract.getInsuranceProduct().getInsuranceProductType().getInsuranceName());
		System.out.println("만기 날짜:" + contract.getInsuranceExpiryDate());
		System.out.println("\n1.만기된 고객 삭제하기");
		System.out.println("2.돌아가기");
		switch (sc.nextInt()) {
			case 1 :
				System.out.println(contractService.deleteContract(contract)
						? "만기고객 삭제가 완료되었습니다."
						: "만기고객 삭제에 실패하였습니다.");
			case 2 :
				return;
		}
	}

	private void compensationHandleMenu() {// CH(보상처리자)
		CompensationHandle compensationHandle = (CompensationHandle) managerLogin;
		while (true) {
			try {
				System.out.println("\n---CompensationHandleMenu---");
				System.out.println("1.사고처리");
				System.out.println("2.로그아웃");
				int a = sc.nextInt();
				if (a < 1 || a > 2) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				switch (a) {
				case 1:
					this.accidentHandlingMenu(compensationHandle);
					break;
				case 2:
					managerLogin = null;
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	private void accidentHandlingMenu(CompensationHandle compensationHandle) {
		while (true) {
			try {
				System.out.println("---보고싶은 사고의 보험종류를 선택해주세요.---");
				System.out.println("1.실비보험 2.암보험 3.연금보험 4.종신보험 5.돌아가기");
				int input = sc.nextInt();
				if (input == 5)
					break;
				if (input < 1 || input > 5) {
					System.out.println("올바른 값을 입력해주세요.");
				}
				ArrayList<Accident> accidentList = contractService
						.showAccidentListByProductType(InsuranceProductType.values()[input - 1]);
				System.out.println("---사고 목록---");
				int i = 0;
				if (!accidentList.isEmpty()) {
					for (Accident accident : accidentList) {
						Client client = accident.getClient();
						System.out.println((i + 1) + ". " + "고객이름: " + client.getName() + " 상품명: "
								+ accident.getInsuranceProduct().getProductName() + " 접수날짜: "
								+ accident.getReceptionDate().toString());
						i++;
					}
					System.out.println("상세정보를 보고 싶은 사고의 번호를 입력해주세요.");
					input = sc.nextInt();
					this.showAccidentDetail(compensationHandle, accidentList.get(input - 1));
				} else {
					System.out.println("현재 해당 보험의 사고 접수 신청내역이 없습니다.\n");
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void showAccidentDetail(CompensationHandle compensationHandle, Accident accident) {
		try {
			Client client = accident.getClient();
			System.out.println("[상세정보]");
			System.out.println("고객 이름: " + client.getName());
			System.out.println("고객 나이: " + client.getAge());
			System.out.println("접수 내용: " + accident.getAccidentDetail());
			System.out.println("접수 날짜:" + accident.getReceptionDate());
			System.out.println("\n1.보험금 입력");
			System.out.println("2.돌아가기");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("보험금을 입력해주세요.");
				System.out.println(compensationHandle.payInsuranceMoney(sc.nextInt(), client) ? "보험금 지급이 완료되었습니다."
						: "보험금 지급에 실패하였습니다.");
				System.out.println(
						contractService.deleteAccidentList(accident) ? "사고기록 삭제에 성공하였습니다." : "사고기록 삭제에 실패하였습니다.");
				break;
			case 2:
				return;
			default:
				System.out.println("잘못된 번호를 입력하였습니다.");
				break;
			}
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("숫자를 입력해주세요.");
		}
	}

	private void salesPersonMenu() {// SP(영업사원)
		while (true) {
			try {
				System.out.println("\n---salesPersonMenu---");
				System.out.println("1.영업 활동 관리");
				System.out.println("2.계약할 수 있는 보험 조회");
				System.out.println("3.로그아웃");
				switch (sc.nextInt()) {
				case 1:
					ArrayList<Contract> newList = contractService.searchBySalesPerson(managerLogin.getId());
					if (newList.isEmpty()) {
						System.out.println("---현재 계약한 보험이 없습니다.---");
						break;
					} else {
						System.out.println("---상세 정보를 볼 계약의 번호를 입력하세요.---");
						this.showContractList(newList);
					}
					this.showContractInfo(newList.get(sc.nextInt() - 1));
					break;
				case 2:
					InsuranceProduct selectInsuranceProduct = this
							.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
					if (selectInsuranceProduct == null)
						break;
					this.registeInsuranceProduct(selectInsuranceProduct);
					break;
				case 3:
					managerLogin = null;
					return;
				default:
					System.out.println("올바른 값을 입력해주세요.");
					break;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void registeInsuranceProduct(InsuranceProduct selectInsuranceProduct) {
		try {
			System.out.println("\n---해당 보험을 가입하시겠습니까?---");
			System.out.println("1.네 2.아니요");
			switch (sc.nextInt()) {
			case 1:
				sc.nextLine();
				System.out.println("--고객의 ID를 입력해주세요.--");
				String id = sc.nextLine();
				System.out.println("--고객의 PassWord를 입력해주세요.--");
				String pw = sc.nextLine();
				clientLogin = clientService.login(id, pw);
				if (clientLogin != null)
					clientContractMenu(selectInsuranceProduct);
				else
					System.out.println("입력하신 정보를 확인해주세요.");
				clientLogin = null;
			case 2:
				return;
			default:
				System.out.println("올바른 값을 입력해주세요.");
				break;
			}
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("숫자를 입력해주세요.");
		}
	}

	private void clientContractMenu(InsuranceProduct selectInsuranceProduct) {
		switch (selectInsuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			contractActualExpense(selectInsuranceProduct);
			break;
		case CANCER:
			contractCancer(selectInsuranceProduct);
			break;
		case PENSION:
			contractPension(selectInsuranceProduct);
			break;
		case LIFE:
			contractLife(selectInsuranceProduct);
			break;
		default:
			return;
		}
	}

	private void contractActualExpense(InsuranceProduct selectInsuranceProduct) {
		try {
			Contract contract = new Contract();
			System.out.println("입원 횟수를 입력해주세요.");
			clientLogin.getMedicalHistory().setNumberOfHospitalizations(sc.nextInt());
			System.out.println("병원방문 횟수를 입력해주세요.");
			clientLogin.getMedicalHistory().setNumberOfHospitalVisits(sc.nextInt());
			if (clientService.modifyMedicalHistory(clientLogin)) {
				System.out.println("기록이 완료되었습니다.");
			} else {
				System.out.println("기록에 실패하였습니다. 계약을 종료합니다.");
				return;
			}
			contract.setClient(clientLogin);
			contract.setInsuranceProduct(selectInsuranceProduct);
			contract.setInsuranceContractDate(new Date());
			contract.setInsuranceExpiryDate(selectInsuranceProduct.getPaymentPeriod());
			contract.setSalesPerson((SalesPerson) managerLogin);
			System.out.println("고객의 보험료는 " + selectInsuranceProduct.calculationRate(clientLogin) + " 입니다.");
			System.out.println(
					contractService.registerInsuranceProduct(contract) ? "계약 작성이 완료되었습니다. 계약 심사 후 보험가입 승인여부가 결정됩니다."
							: "계약 작성에 실패하였습니다. 다시 시도해주세요.");
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("숫자를 입력해주세요.");
		}
	}

	private void contractCancer(InsuranceProduct selectInsuranceProduct) {
		try {
			Contract contract = new Contract();
			System.out.println("자신의 암 경력을 입력해주세요.");
			System.out.println("1.췌장암(1.6) 2.폐암(1.5) 3.위암(1.4) 4.대장암(1.3) 5.간암(1.2) 6.기타(1.1) 7.없음(1.0)");
			int input = sc.nextInt();
			CancerType.values()[input - 1].getCancerName();
			double rate = CancerType.values()[input - 1].getRate();
			System.out.println(CancerType.values()[input - 1].getCancerName() + " " + rate);
			System.out.println("현재 암 경력은 " + CancerType.values()[input - 1].getCancerName() + " " + rate + "입니다.");
			clientLogin.getMedicalHistory().setClientCancerCareer(CancerType.values()[input - 1]);
			System.out.println("\n가족 암 경력을 입력해주세요.");
			System.out.println("1.췌장암(1.6) 2.폐암(1.5) 3.위암(1.4) 4.대장암(1.3) 5.간암(1.2) 6.기타(1.1) 7.없음(1.0)");
			int a = sc.nextInt();
			CancerType.values()[a - 1].getCancerName();
			double cancerRate = CancerType.values()[a - 1].getRate();
			System.out.println(CancerType.values()[a - 1].getCancerName() + " " + cancerRate);
			clientLogin.getMedicalHistory().setFamilyCancerCareer(CancerType.values()[input - 1]);

			contract.setClient(clientLogin);
			contract.setInsuranceProduct(selectInsuranceProduct);
			contract.setInsuranceContractDate(new Date());
			contract.setInsuranceExpiryDate(selectInsuranceProduct.getPaymentPeriod());
			contract.setSalesPerson((SalesPerson) managerLogin);
			System.out.println("고객의 보험료는 " + selectInsuranceProduct.calculationRate(clientLogin) + " 입니다.");
			System.out.println(
					contractService.registerInsuranceProduct(contract) ? "계약 작성이 완료되었습니다. 계약 심사 후 보험가입 승인여부가 결정됩니다."
							: "계약 작성에 실패하였습니다. 다시 시도해주세요.");
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("숫자를 입력해주세요.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}
	}

	private void contractPension(InsuranceProduct selectInsuranceProduct) {
		try {
			Contract contract = new Contract();
			contract.setClient(clientLogin);
			contract.setInsuranceProduct(selectInsuranceProduct);
			contract.setInsuranceContractDate(new Date());
			contract.setInsuranceExpiryDate(selectInsuranceProduct.getPaymentPeriod());
			contract.setSalesPerson((SalesPerson) managerLogin);
			System.out.println("고객의 보험료는 " + selectInsuranceProduct.calculationRate(clientLogin) + " 입니다.");
			System.out.println(
					contractService.registerInsuranceProduct(contract) ? "계약 작성이 완료되었습니다. 계약 심사 후 보험가입 승인여부가 결정됩니다."
							: "계약 작성에 실패하였습니다. 다시 시도해주세요.");
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("숫자를 입력해주세요.");
		}
	}

	private void contractLife(InsuranceProduct selectInsuranceProduct) {
		try {
			Contract contract = new Contract();
			contract.setClient(clientLogin);
			contract.setInsuranceProduct(selectInsuranceProduct);
			contract.setInsuranceContractDate(new Date());
			contract.setInsuranceExpiryDate(selectInsuranceProduct.getPaymentPeriod());
			contract.setSalesPerson((SalesPerson) managerLogin);
			System.out.println("고객의 보험료는 " + selectInsuranceProduct.calculationRate(clientLogin) + " 입니다.");
			System.out.println(
					contractService.registerInsuranceProduct(contract) ? "계약 작성이 완료되었습니다. 계약 심사 후 보험가입 승인여부가 결정됩니다."
							: "계약 작성에 실패하였습니다. 다시 시도해주세요.");
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("숫자를 입력해주세요.");
		}
	}

	private void clientWorkMenu() {
		while (true) {
			System.out.println("1.모든 보험 조회하기 2.가입한 보험 조회하기 3.사고접수 4.로그아웃");
			try {
				switch (sc.nextInt()) {
				case 1:
					ArrayList<InsuranceProduct> list = insuranceProductService.showInsuranceProductIsApproval();
					if (list == null)
						return;
					else
						insuranceMenu(list);
					break;
				case 2:
					signUpInsuranceProductMenu();
					break;
				case 3:
					accidentReception();
					break;
				case 4:
					clientLogin = null;
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
	}

	private void signUpInsuranceProductMenu() {
		while (true) {
			try {
				ArrayList<Contract> contractList = contractService.selectByApproval(true);
				if (contractList.size() > 0) {
					System.out.println("조회할 보험을 선택해주세요.");
					for (int i = 0; i < contractList.size(); i++)
						System.out.println((i + 1 + ". 보험상품명: ")
								+ contractList.get(i).getInsuranceProduct().getProductName() + " 보험종류: " + contractList
										.get(i).getInsuranceProduct().getInsuranceProductType().getInsuranceName());
					int a = sc.nextInt();
					Contract contract = contractList.get(a - 1);
					showInsuranceProductDetail(contract.getInsuranceProduct());
					System.out.println(contractList.size());
					System.out.println("\n1.보험 해지 2.보험료 납부 3.돌아가기");
					int b = sc.nextInt();
					switch (b) {
					case 1:
						System.out.println("정말로 해지하시겠습니까? 보험료는 환불이 불가능합니다.");
						System.out.println("1.예 2.아니오");
						int c = sc.nextInt();
						if (c == 1) {
							System.out.println(
									contractService.deleteContract(contract) ? "보험해지가 완료되었습니다." : "보험해지에 실패하였습니다.");
							return;
						}
						if (c == 2) {
							break;
						} else
							System.out.println("제대로 입력해주세요.");
						return;
					case 2:
						System.out.println("고객의 보험료 납부 현황");
						payInsuranceProduct(contract);
						return;
					case 3:
						return;
					default:
						System.out.println("올바른 값을 입력해주세요.");
						break;
					}
				} else {
					System.out.println("현재 가입한 보험이 없습니다.");
					break;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void payInsuranceProduct(Contract contract) {//보험료 납입
		while(true) {
			boolean[] month = contract.getMonth();
			System.out.println("------(보험료 납부 현황)-------");
			System.out.print("납부여부: ");
			for (int i = 0; i < 12; i++) {
				if (month[i] == true) {
					System.out.print((i + 1) + "월 O  ");
				}
			}
			System.out.println("\n보험료를 납부하실 달을 선택해주세요");
			int a = sc.nextInt();
			try {
				if (a > 12 || a < 1) {
					System.out.println("올바른 값을 입력해주세요.");
					return;
				}
				if(month[a-1]) {
					System.out.println("입력한 월에 이미 납부가 완료되었습니다.");
				} else {
					month[a-1] = true;
					System.out.println("납부하실 보험료는 " + 
					contract.getInsuranceProduct().calculationRate(clientLogin));
					contract.setMonth(month);
					System.out.println(contractService.modifyContract(contract)?
							"보험료가 납부되었습니다." : "보험료 납부에 실패하였습니다.");}
				break;
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
	}

	private void accidentReception() {
		while (true) {
			try {
				System.out.println("---accidentReception Menu---");
				System.out.println("1.사고접수 신청하기 2.사고접수 신청내역 확인하기 3.돌아가기");
				int input = sc.nextInt();
				switch (input) {
				case 1:
					applyAccidentReception();
					break;
				case 2:
					checkApplyAccidentReception();
					break;
				case 3:
					return;
				default:
					System.out.println("올바른 값을 입력해주세요.");
					break;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
	}

	private void applyAccidentReception() {
		while (true) {
			try {
				ArrayList<Contract> contractList = contractService.selectByApproval(true);
				if (contractList.size() > 0) {
					System.out.println("사고접수 신청 할 수 있는 보험 목록입니다.");
					for (int i = 0; i < contractList.size(); i++)
						System.out
								.println((i + 1 + ". ") + contractList.get(i).getClient().getName() + " " + contractList
										.get(i).getInsuranceProduct().getInsuranceProductType().getInsuranceName());
					System.out.println("\n사고접수 신청할 보험을 입력해주세요.");
					int input = sc.nextInt();
					Contract contract = contractList.get(input - 1);
					showInsuranceProductDetail(contract.getInsuranceProduct());
					System.out.println("1.신청 2.돌아가기");
					int a = sc.nextInt();
					sc.nextLine();
					switch (a) {
					case 1:
						Accident applyAccident = new Accident();
						System.out.println("사고접수 내용을 입력해주세요.");
						applyAccident.setAccidentDetail(sc.nextLine());
						applyAccident.setClient(clientLogin);
						applyAccident.setInsuranceProduct(contract.getInsuranceProduct());
						applyAccident.setReceptionDate(new Date());
						System.out.println(contractService.addApplyAccidentList(applyAccident) ? "\n사고접수 신청이 완료되었습니다."
								: "\n사고접수 신청이 실패하였습니다.");
						return;
					case 2:
						return;
					default:
						System.out.println("올바른 값을 입력해주세요.");
						break;
					}
				} else {
					System.out.println("현재 가입된 보험이 없습니다.");
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void checkApplyAccidentReception() {
		while (true) {
			try {
				System.out.println("---현재 신청한 사고접수 목록입니다.---");
				ArrayList<Accident> accidentList = contractService.applyAccidentList();
				int a = 0;
				if (!accidentList.isEmpty()) {
					System.out.println("보고싶은 사고접수를 입력해주세요.");
					for (Accident accident : accidentList) {
						System.out.println((a + 1) + ". " + "상품명: " + accident.getInsuranceProduct().getProductName()
								+ " 보험종류: " + accident.getInsuranceProduct().getInsuranceProductType() + " 접수날짜: "
								+ accident.getReceptionDate().toString());
						a++;
					}
					int b = sc.nextInt();
					Accident acc = accidentList.get(b - 1);
					Client cli = acc.getClient();
					System.out.println("--사고접수 상세내용--");
					System.out.println("고객이름: " + cli.getName());
					System.out.println("상품명: " + acc.getInsuranceProduct().getProductName());
					System.out.println("보험종류: " + acc.getInsuranceProduct().getInsuranceProductType());
					System.out.println("접수날짜: " + acc.getReceptionDate().toString());
					System.out.println("사고내용: " + acc.getAccidentDetail());
					System.out.println("\n1.삭제 2.돌아가기");
					int c = sc.nextInt();
					switch (c) {
					case 1:
						System.out.println(
								contractService.deleteAccidentList(acc) ? "사고접수 삭제가 완료되었습니다." : "사고접수 삭제가 실패하였습니다.");
						break;
					case 2:
						return;
					}
				} else {
					System.out.println("현재 신청한 사고접수가 없습니다.\n");
					return;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void clientMenu() {
		while (true) {
			try {
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
				default:
					System.out.println("올바른 값을 입력해주세요.");
					break;
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
	}

	private void clientRegisterMenu() {
		while (true) {
			try {
				sc.nextLine();
				Client client = new Client();
				System.out.println("ID를 입력해주세요.");
				client.setId(sc.nextLine());
				while (clientService.checkClientID(client.getId()) != null) {
					System.out.println("이미 가입된 ID입니다. 다시 입력해주세요.");
					client.setId(sc.nextLine());
				}
				System.out.println("비밀번호를 입력하세요");
				client.setPassword(sc.nextLine());
				System.out.println("이름을 입력해주세요.");
				client.setName(sc.nextLine());
				System.out.println("나이를 입력해주세요.");
				client.setAge(sc.nextInt());
				sc.nextLine();
				System.out.println("Email을 입력해주세요.");
				client.setEmail(sc.nextLine());
				System.out.println("성별을 입력해주세요. (1.남 2.여)");
				int genderInt = sc.nextInt();
				if (genderInt == 1) {
					client.setGender(true);
					System.out.println("남자");
				} else if (genderInt == 2) {
					client.setGender(false);
					System.out.println("여자");
				} else {
					throw new IndexOutOfBoundsException();
				}
				System.out.println("직업을 입력해주세요.");
				System.out.println("1.군인 2.생산직 3.농립어업 4.운전기사 5.기타");
				int input = sc.nextInt();
				client.setJob(ClientJobType.values()[input - 1]);
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
				clientService.addMedicalHistory(client);
				break;
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("숫자만 입력 가능합니다.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private void clientLoginMenu() {
		if (clientLogin == null) {
			sc.nextLine();
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

	private void clientDeleteMenu() {
		sc.nextLine();
		System.out.println("--삭제할 고객 ID를 입력해주세요.--");
		String id = sc.nextLine();
		System.out.println("--삭제할 고객 PW를 입력해주세요.--");
		String pw = sc.nextLine();

		if (clientService.login(id, pw) != null) {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1.예 2.아니오");
			int a = sc.nextInt();
			switch (a) {
			case 1:
				System.out.println(clientService.delete(id, pw) ? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
				clientLogin = null;
				break;
			case 2:
				return;
			default:
				System.out.println("잘못된 번호를 입력하였습니다.");
				return;
			}
		} else {
			System.out.println("입력한 정보를 다시 확인해주세요.");
		}
	}

	private InsuranceProduct insuranceMenu(ArrayList<InsuranceProduct> insuranceProductList) {
		try {
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
		} catch (IndexOutOfBoundsException e) {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("숫자만 입력 가능합니다.");
		}
		return null;
	}

	private void showInsuranceProductDetail(InsuranceProduct insuranceProduct) {
		System.out.println("보험상품 이름: " + insuranceProduct.getProductName());
		System.out.println("기본보험료: " + insuranceProduct.getBasicInsurancePremium());
		System.out.println("보험 종류: " + insuranceProduct.getInsuranceProductType().getInsuranceName());
		System.out.println("납입기간: " + insuranceProduct.getPaymentPeriod());
		System.out.println("납입주기: " + insuranceProduct.getPaymentCycle());
		switch (insuranceProduct.getInsuranceProductType()) {
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

	private void actualexpenseInfo(InsuranceProduct insuranceProduct) {// 21.05.19
																		// 완성
		ActualExpense actualExpense = (ActualExpense) insuranceProduct;
		System.out.println("제한나이: " + actualExpense.getLimitAge() + " \n보장한도: " + actualExpense.getLimitOfIndemnity()
				+ " \n자기부담금: " + actualExpense.getSelfPayment());
	}

	private void cancerInfo(InsuranceProduct insuranceProduct) {
		Cancer cancer = (Cancer) insuranceProduct;
		System.out.println("제한나이: " + cancer.getLimitAge() + "\n보장내역: " + cancer.getCancerType().getCancerName() + " ("
				+ cancer.getCancerType().getRate() + ")" + "\n보험금: " + cancer.getInsuranceMoney());
	}

	private void pensionInfo(InsuranceProduct insuranceProduct) {
		Pension pension = (Pension) insuranceProduct;
		System.out.println("보장기간: " + pension.getGuaranteedPeriod() + "\n보험금: " + pension.getInsuranceMoney());
	}

	private void lifeInfo(InsuranceProduct insuranceProduct) {
		Life life = (Life) insuranceProduct;
		System.out.println("필수납입기간: " + life.getRequiredPaymentPeriod() + "\n보험금: " + life.getInsuranceMoney());
	}
}