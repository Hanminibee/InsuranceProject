package service;
import entity.Client;
public interface ClientService {
	public boolean register(Client client);
	public Client login(String id, String pw);
	public boolean delete(String id, String pw);
	public Client checkClientID(String clientID);
}