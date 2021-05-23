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
		System.out.println("[1.�����ǰ������ 2.�����ǰ������ 3.U/W 4.�������� 5.����ó���� 6.�������]");
		int input = sc.nextInt();
		Manager manager = ManagerType.values()[input-1].getManager().clone();
		manager.setJobPosition(ManagerType.values()[input-1]);
		sc.nextLine();
		
		System.out.println("[�̸�]");
		manager.setName(sc.nextLine());
		
		System.out.println("[����]");
		manager.setAge(sc.nextInt());
		sc.nextLine();
		
		System.out.println("[��ȭ��ȣ]");
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
