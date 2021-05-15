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
	
	public void association() {
		
	}

	@Override
	public boolean register() {
		Client client = new Client();
		System.out.println("[이름]");
		client.setName(sc.nextLine());
		
		System.out.println("[나이]");
		client.setAge(sc.nextInt());
		sc.nextLine();
		
		System.out.println("[주소]");
		
		System.out.println("[email]");
		
		System.out.println("[성별 (1.남 2.여)]");
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
