package controller;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Accident;
import entity.Client;
import entity.CompensationHandle;
import entity.Contract;
import entity.ContractManagement;
import entity.InsuranceProduct;
import entity.InsuranceProducts;
import entity.InsuranceProductsAcceptance;
import entity.Manager;
import entity.UW;
import service.ClientServiceImpl;
import service.InsuranceProductServiceImpl;
import service.ManagerServiceImpl;
import type.InsuranceProductType;

public class ConsoleController{
	
	private Scanner sc;
	private ClientServiceImpl clientService;
	private InsuranceProductServiceImpl insuranceProductService;
	private ManagerServiceImpl managerService;
	private Manager managerLogin;
	private Client clientLogin;
	
	public ConsoleController() {
		this.sc = new Scanner(System.in);
		this.clientService = new ClientServiceImpl();
		this.insuranceProductService = new InsuranceProductServiceImpl();
		this.managerService = new ManagerServiceImpl();
		
		this.clientService.association(insuranceProductService.getInsuranceProductList());
		
		this.managerLogin = null;
		this.clientLogin = null;  
	}
	
	public void run() {
		this.mainMenu();
	}
	
	private void mainMenu() {
		while(true) {
			System.out.println("\n---MainMenu---");
			System.out.println("1.관리자");
			System.out.println("2.회원");
			System.out.println("3.보험");
			System.out.println("4.끝내기");
			switch(sc.nextInt()) {
			case 1:
				managerMenu();
				break;
			case 2:
				clientMenu();
				break;
			case 3:
				insuranceMenu();
				break;
			case 4:
				return;
			}
		}
	}
	
	//ManagerMenus
	private void managerMenu() {
		while(true) {
			System.out.println("\n---ManagerMenu---");
			System.out.println("1.관리자 등록");
			System.out.println("2.관리자 로그인");
			System.out.println("3.관리자 삭제");
			System.out.println("4.돌아가기");
			switch(sc.nextInt()) {
			case 1:
				System.out.println(managerService.register() ? "등록이 완료되었습니다." : "등록에 실패하였습니다.");
				break;
			case 2:
				if(managerLogin == null) managerLogin = managerService.login();
				if(managerLogin != null) managerWorkMenu();
				else System.out.println("등록되지 않은 매니저입니다.");
				break;
			case 3:
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
	
	private void managerWorkMenu() {
		switch(managerLogin.getJobPosition()) {
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
		InsuranceProducts insuranceProducts = (InsuranceProducts)managerLogin;
		while(true) {
			System.out.println("\n---InsuranceProductsMenu---");
			System.out.println("1.보험상품 설계");
			System.out.println("2.사후관리");
			System.out.println("3.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				InsuranceProduct developedProduct = insuranceProducts.designInsurance().developInsurance();
				insuranceProductService.add(developedProduct);
				break;
			case 2:
				
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	
	private void insuranceProductsAcceptanceMenu() {
		InsuranceProductsAcceptance insuranceProductsAcceptance = (InsuranceProductsAcceptance)managerLogin;
		while(true) {
			System.out.println("\n---InsuranceProductsAcceptanceMenu---");
			System.out.println("1.보험상품 승인");
			System.out.println("2.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				break;
			case 2:
				managerLogin = null;
				return;
			}
		}
	}
	
	private void underWriterMenu() {
		UW uw = (UW)managerLogin;
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
	
	private Contract selectUnderWriteContract() {
		ArrayList<Contract> contractList = insuranceProductService.selectNotApproval();
		if(contractList.size()>0) {
			System.out.println("[인수심사 계약 목록]");
			for(int i = 0; i < contractList.size(); i++)
				System.out.println(String.format("%d.%5s%10s", i+1, contractList.get(i).getClientID(), contractList.get(i).getProductName()));
			System.out.println("인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input-1);
			this.showClientInfo(contract.getClientID());
//			this.showInsuranceInfo(contract.getProductName());
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
	
	private void contractManagerMenu() {
		//ContractManagement contractManagement = (ContractManagement)managerLogin;
		while(true) {
			System.out.println("\n---ContractManagementMenu---");
			System.out.println("");
		}
	}
	
	private void compensationHandleMenu() {
		//CompensationHandle compensationHandle = (CompensationHandle)managerLogin;
		while(true) {
			System.out.println("\n---CompensationHandleMenu---");
			System.out.println("1.사고처리");
			System.out.println("2.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				this.accidentHandlingMenu();
				break;
			case 2:
				managerLogin = null;
				break;
			}
		}
	}
	
	private void accidentHandlingMenu() {
		System.out.println("보고싶은 사고의 보험종류를 선택해주세요.");
		System.out.println("[1.실비보험, 2.암보험, 3.연금보험, 4.종신보험]");
		int input = sc.nextInt();
		ArrayList<Accident> accidentList = clientService.showAccidentListByProductType(InsuranceProductType.values()[input-1]);		
		System.out.println("[사고 목록]");
		int i = 0;
		for(Accident accident : accidentList) {
			Client client = clientService.search(accident.getClientID());
			System.out.println(String.format("%d.%5s%10s%12s", i+1, client.getName(), accident.getProductName(), accident.getReceptionDate().toString()));
			i++;
		}
		System.out.println("상세정보를 보고 싶은 사고의 번호를 입력해주세요.");
		input = sc.nextInt();
		this.showAccidentDetail(accidentList.get(input-1));
	}
	
	private void showAccidentDetail(Accident accident) {
		Client client = clientService.search(accident.getClientID());
		System.out.println("[상세정보]");
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("접수 내용: " + accident.getAccidentDetail());
		System.out.println("접수 날짜:" + accident.getReceptionDate());
	}
	
	private void salesPersonMenu() {
		
	}
	
	
	
	//clientMenus
	private void clientMenu() {
		while(true) {
			System.out.println("\n---ClientMenu---");
			System.out.println("1.회원가입");
			System.out.println("2.회원 로그인");
			System.out.println("3.회원 탈퇴");
			System.out.println("4.돌아가기");
			switch(sc.nextInt()) {
			case 1:
				clientService.register();
				break;
			case 2:
				if(clientLogin == null) clientLogin = clientService.login();
				if(clientLogin != null) clientWorkMenu();
				else System.out.println("입력한 정보가 잘못되었습니다.");
				break;
			case 3:
				clientService.delete();
				break;
			case 4:
				return;
			}
		}
	}
	
	private void clientWorkMenu() {
		System.out.println("1.모든 보험 조회하기 2.가입한 보험 조회하기 3.로그아웃");
		switch(sc.nextInt()) {
		case 1: 
			 insuranceMenu();
		case 2: 
			
		case 3: clientLogin = null;
			return;
		}
	}
	
	//insuranceMenus
	private void insuranceMenu() {
		System.out.println("\n---InsuranceList---");
		int i = 1;
		for(InsuranceProduct insuranceProduct : insuranceProductService.showAllList()) {
			System.out.println(i+". " + insuranceProduct.getProductName() +" "+ insuranceProduct.getInsuranceProductType().getInsuranceName());
			i++;
		}
	}

}
