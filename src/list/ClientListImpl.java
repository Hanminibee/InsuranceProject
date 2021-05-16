package list;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Client;

public class ClientListImpl implements ClientList {
	Scanner sc = new Scanner(System.in);
	ArrayList<Client> clientList = new ArrayList<Client>();

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
		for (Client client : clientList) {
			if (clientID.equals(client.getId()) & password.equals(client.getPassword()))
				return client;
		}
		return null;
	}
}