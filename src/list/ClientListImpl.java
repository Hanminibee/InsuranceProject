package list;

import java.util.ArrayList;

import entity.Client;

public class ClientListImpl implements ClientList {
	ArrayList<Client> clientList = new ArrayList<Client>();
	
	public ClientListImpl() {
		Client client = new Client();
		client.setId("a");
		client.setPassword("a");
		this.add(client);
	}

	public ArrayList<Client> getClientList() {
		return this.clientList;
	}
	public void setClientlist(ArrayList<Client> clientlist) {
		this.clientList = clientlist;
	}
	public boolean add(Client client) {
		return this.clientList.add(client);
	}
	public boolean delete(Client client) {
		return this.clientList.remove(client);
	}
	public Client search(String clientID) {
		for (Client client : clientList) {
			if (clientID.equals(client.getId()))
				return client;
		}
		return null;
	}
	public Client search(String clientID, String password) {
		System.out.println(clientList.size());
		for (Client client : clientList) {
			if (clientID.equals(client.getId()) & password.equals(client.getPassword()))
				return client;
		}
		return null;
	}
}