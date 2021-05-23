package list;
import entity.Client;
public interface ClientList {
	public boolean add(Client client);
	public boolean delete(Client client);
	public Client search(String clientID);
	public Client search(String id, String pw);
}