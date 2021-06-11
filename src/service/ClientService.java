package service;
import dao.ClientDao;
import dao.MedicalHistoryDao;
import entity.Client;
public interface ClientService {
	public boolean register(Client client);
	public Client login(String id, String pw);
	public boolean delete(String id, String pw);
	public Client checkClientID(String clientID);
	public ClientDao getClientList();
	public MedicalHistoryDao getMedicalHistoryList();
	public boolean addMedicalHistory(Client clientLogin);
	public boolean modifyMedicalHistory(Client clientLogin);
}