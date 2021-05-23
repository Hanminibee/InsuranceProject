package service;
import entity.Client;
import list.ClientList;
import list.ClientListImpl;

public class ClientServiceImpl implements ClientService {
	private ClientList clientList;
	

	public ClientServiceImpl() {
		this.clientList = new ClientListImpl();
	}
	
	public Client checkClientID(String clientID) {
		return clientList.search(clientID);
	}

	public void association() {
	}

	public boolean register(Client client) {
		return clientList.add(client);
	}

	public Client login(String id, String pw) {
		return clientList.search(id, pw);
	}

	public boolean delete(String id, String pw) {
		return clientList.delete(clientList.search(id, pw));

	}
}