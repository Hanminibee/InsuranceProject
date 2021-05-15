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
			System.out.println("1.������");
			System.out.println("2.ȸ��");
			System.out.println("3.����");
			System.out.println("4.������");
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
			System.out.println("1.������ ���");
			System.out.println("2.������ �α���");
			System.out.println("3.������ ����");
			System.out.println("4.���ư���");
			switch(sc.nextInt()) {
			case 1:
				System.out.println(managerService.register() ? "����� �Ϸ�Ǿ����ϴ�." : "��Ͽ� �����Ͽ����ϴ�.");
				break;
			case 2:
				if(managerLogin == null) managerLogin = managerService.login();
				if(managerLogin != null) managerWorkMenu();
				else System.out.println("���� �Ŵ����Դϴ�.");
				break;
			case 3:
				System.out.println(managerService.delete() ? "������ �Ϸ�Ǿ����ϴ�." : "������ �����Ͽ����ϴ�.");
				break;
			case 4:
				return;
			default: 
				System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
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
			System.out.println("1.�����ǰ ����");
			System.out.println("2.���İ���");
			System.out.println("3.������");
			System.out.println("3.�α׾ƿ�");
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
			System.out.println("1.����� ���� ����");
			System.out.println("2.����� ������ ����");
			System.out.println("3.�α׾ƿ�");
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
			System.out.println("1.ȸ������");
			System.out.println("2.ȸ�� �α���");
			System.out.println("3.ȸ�� Ż��");
			System.out.println("4.���ư���");
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
