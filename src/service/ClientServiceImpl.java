package service;

import java.util.Scanner;

import entity.Client;
import list.ClientListImpl;

public class ClientServiceImpl implements ClientService {

	private ClientListImpl clientListImpl;
	private Scanner sc;
	
	public ClientServiceImpl() {
		this.clientListImpl = new ClientListImpl();
		this.sc = new Scanner(System.in);
	}
	
<<<<<<< HEAD
	public Client checkClientID(String clientID) {
		return clientList.search(clientID);
	}

=======
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
	public void association() {
		
	}

	@Override
	public boolean register() {
		Client client = new Client();
		System.out.println("[�̸�]");
		client.setName(sc.nextLine());
		
		System.out.println("[����]");
		client.setAge(sc.nextInt());
		sc.nextLine();
		
		System.out.println("[�ּ�]");
		
		System.out.println("[email]");
		
		System.out.println("[���� (1.�� 2.��)]");
		int gender = sc.nextInt();
		if(gender == 1) client.setGender(true);
		else client.setGender(false);
		
		
		sc.nextLine();
		
		
		System.out.println();
		
		return clientListImpl.add(client);
	}

	@Override
	public Client login() {
		System.out.println("[ID]");
		String id = sc.nextLine();
		System.out.println("[Password]");
		String pw = sc.nextLine();
		
		return clientListImpl.search(id, pw);
	}

	@Override
	public boolean delete() {
		System.out.println("[ID]");
		String id = sc.nextLine();
		System.out.println("[Password]");
		String pw = sc.nextLine();
		
		return clientListImpl.delete(clientListImpl.search(id, pw));
	}
	
}
