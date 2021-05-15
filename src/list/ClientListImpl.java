package list;

import java.util.ArrayList;

import entity.Client;

public class ClientListImpl implements ClientList {

	private ArrayList<Client> clientList = new ArrayList<Client>();

	public ArrayList<Client> getClientList() {
		return this.clientList;
	}

	public void setClientlist(ArrayList<Client> clientlist) {
		this.clientList = clientlist;
	}

	@Override
	public boolean add(Client client) {
		return this.clientList.add(client);
	}

	@Override
	public boolean delete(Client client) {
		return this.clientList.remove(client);
	}

	@Override
	public Client search(String clientID) {
		for (Client client : clientList) {
			if (clientID.equals(client.getId()))
				return client;
		}
		return null;
	}

	@Override
	public Client search(String clientID, String password) {
		for (Client client : clientList) {
			if (clientID.equals(client.getId()) & password.equals(client.getPassword()))
				return client;
		}
		return null;
	}

}
