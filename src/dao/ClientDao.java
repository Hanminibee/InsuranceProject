package dao;

import entity.Client;

public interface ClientDao {
	
	public boolean add(Client client);
	public boolean delete(Client client);
	public Client search(String clientId);
	public Client search(String clientId, String password);
}
