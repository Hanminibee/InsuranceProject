package controller;
import java.util.Scanner;

import entity.Client;
import entity.InsuranceProduct;
import entity.InsuranceProducts;
import entity.Manager;
import service.ClientServiceImpl;
import service.InsuranceProductServiceImpl;
import service.ManagerServiceImpl;

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
				else System.out.println("없는 매니저입니다.");
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
			
			break;
		case UW:
			
			break;
		case CM:
			contractManagementMenu();
			break;
			
		case CH:
			
			break;
		case SP:
			
			break;
		}
	}
	
	private void insuranceProductsMenu() {
		InsuranceProducts ip = (InsuranceProducts)managerLogin;
		while(true) {
			System.out.println("\n---InsuranceProductsMenu---");
			System.out.println("1.보험상품 설계");
			System.out.println("2.사후관리");
			System.out.println("3.계약관리");
			System.out.println("3.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				InsuranceProduct developedProduct = ip.designInsurance().developInsurance();
				insuranceProductService.add(developedProduct);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				managerLogin = null;
				return;
			}
		}
	}
	private void contractManagementMenu() {
		ContractManagement cm = (ContractManagement)managerLogin;
		while(true) {
			System.out.println("\n---ContractManagementMenu");
			System.out.println("1.계약한 보험 관리");
			System.out.println("2.만료된 보험계약 관리");
			System.out.println("3.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				Contract c
			}
		}
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
				break;
			case 3:
				clientService.delete();
				break;
			case 4:
				return;
			}
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
	//contractMenu
	private void contractMenu() {
		System.out.println("\n---ContractList---");
		int i = 1;
		for()
	}
	}
	

}
