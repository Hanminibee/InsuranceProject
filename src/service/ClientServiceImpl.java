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
	
	public Client search(String clientID) {
		return clientListImpl.search(clientID);
	}

	@Override
	public boolean register() {
		Client client = new Client();
		
		System.out.println("[ID]");
		client.setId(sc.nextLine());
		
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
		if(sc.nextInt() == 1) {
			client.setGender(true);
			System.out.println("남자");
		}
		else {
			client.setGender(false);
			System.out.println("여자");
		}
		
		System.out.println("주소를 입력하세요.");
		client.setAddress(sc.nextLine());
		sc.nextLine();
		
		System.out.println("핸드폰 번호를 입력하세요.");
		client.setPhoneNumber(sc.nextLine());
		
		System.out.println("주민등록번호를 입력하세요.");
		client.setResidentRegistrationNumber(sc.nextLine());
		
		System.out.println("계좌번호를 입력하세요.");
		client.setBankAccountNumber(sc.nextLine());
		
		System.out.println("회원가입이 완료되었습니다.");
		
		return clientListImpl.add(client);
	}

	@Override
	public Client login() {
		System.out.println("--ID를 입력해주세요.--");
		String id = sc.next();
		System.out.println("--Password를 입력해주세요.--");
		String pw = sc.next();
		
		return clientListImpl.search(id, pw);
	}

	@Override
	public boolean delete() {
		System.out.println("--삭제할 고객 ID를 입력해주세요.--");
		String id = sc.next();
		System.out.println("--삭제할 고객 PW를 입력해주세요.--");
		String pw = sc.next();
		
		if(clientListImpl.search(id, pw) != null) {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1.예 2.아니오");
			if(sc.nextInt() == 1 ) {
				return clientListImpl.delete(clientListImpl.search(id, pw));
			}else {
				return false;
			}
		}else {
			System.out.println("입력한 정보를 다시 확인해주세요.");
		}
		return false;
	}
	
	public ClientListImpl getClientList() {
		return this.clientListImpl;
	}
	
}
