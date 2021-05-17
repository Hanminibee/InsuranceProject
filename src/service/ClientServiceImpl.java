package service;
import java.util.Scanner;

import entity.Client;
import list.ClientList;
import list.ClientListImpl;
import type.ClientJobType;
public class ClientServiceImpl implements ClientService {
	private ClientList clientList;
	private Scanner sc;

	public ClientServiceImpl() {
		this.clientList = new ClientListImpl();
		this.sc = new Scanner(System.in);
	}
	public void association() {
		
	}
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
		
		System.out.println("[ClientJobType]");
		System.out.println("[1.군인 2.PW 3.AF 4.DRIVER 5.ETC]");
		int input = sc.nextInt();
		ClientJobType.values()[input-1].getJobName();
		System.out.println(ClientJobType.values()[input-1].getJobName());
		
		sc.nextLine();
		
		//Manager manager = ManagerType.values()[input-1].getManager().clone();
		//manager.setJobPosition(ManagerType.values()[input-1]);
		
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
		
		return clientList.add(client);
	}
	public Client login() {
		System.out.println("--ID를 입력해주세요.--");
		String id = sc.next();
		System.out.println("--Password를 입력해주세요.--");
		String pw = sc.next();
		return clientList.search(id, pw);
	}
	public boolean delete() {
		System.out.println("--삭제할 고객 ID를 입력해주세요.--");
		String id = sc.next();
		System.out.println("--삭제할 고객 PW를 입력해주세요.--");
		String pw = sc.next();
		
		if(clientList.search(id, pw) != null) {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1.예 2.아니오");
			if(sc.nextInt() == 1 ) {
				return clientList.delete(clientList.search(id, pw));
			}else {
				return false;
			}
		}else {
			System.out.println("입력한 정보를 다시 확인해주세요.");
		}
		return false;
	}
	public Client search(String clientID) {
		return clientList.search(clientID);
	}

}