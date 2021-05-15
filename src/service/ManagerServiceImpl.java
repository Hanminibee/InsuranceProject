package service;

import java.util.Scanner;

import entity.Manager;
import list.ManagerListImpl;
import type.ManagerType;

public class ManagerServiceImpl implements ManagerService{
	
	private Scanner sc;
	private ManagerListImpl managerListImpl;
	
	public ManagerServiceImpl() {
		this.sc = new Scanner(System.in);
		this.managerListImpl = new ManagerListImpl();
		
	}
	
	public void association() {
		
	}

	@Override
	public boolean register() {
		System.out.println("[JobPosition]");
		System.out.println("[1.보험상품개발자 2.보험상품승인자 3.U/W 4.계약관리자 5.보상처리자 6.영업사원]");
		int input = sc.nextInt();
		Manager manager = ManagerType.values()[input-1].getManager().clone();
		manager.setJobPosition(ManagerType.values()[input-1]);
		sc.nextLine();
		
		System.out.println("[이름]");
		manager.setName(sc.nextLine());
		
		System.out.println("[나이]");
		manager.setAge(sc.nextInt());
		sc.nextLine();
		
		System.out.println("[전화번호]");
		manager.setPhoneNumber(sc.nextLine());
		
		System.out.println("[ID]");
		manager.setId(sc.nextLine());
		
		System.out.println("[Password]");
		manager.setPassword(sc.nextLine());
		
		return managerListImpl.add(manager);
	}
	
	@Override
	public Manager login() {
		System.out.println("[ID]");
		String id = sc.nextLine();
		System.out.println("[Password]");
		String pw = sc.nextLine();
		
		return managerListImpl.search(id, pw);
	}

	@Override
	public boolean delete() {
		System.out.println("[ID]");
		String id = sc.nextLine();
		System.out.println("[Password]");
		String pw = sc.nextLine();
		
		return managerListImpl.delete(managerListImpl.search(id, pw));
	}
	
}
