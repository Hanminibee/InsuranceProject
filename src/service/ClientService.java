package service;

import entity.Client;

public interface ClientService {
	
	public boolean register();
	public Client login();
	public boolean delete();
	public Client search(String clientID);

}
