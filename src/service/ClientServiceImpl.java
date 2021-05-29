package service;
import dao.ClientDao;
import dao.ClientDaoImpl;
import dao.MedicalHistoryDao;
import dao.MedicalHistoryDaoImpl;
import entity.Client;
import entity.MedicalHistory;

public class ClientServiceImpl implements ClientService {
	private ClientDao clientList;
	private MedicalHistoryDao medicalHistoryList;
	
	public ClientServiceImpl() {
		this.clientList = new ClientDaoImpl();
		this.medicalHistoryList = new MedicalHistoryDaoImpl();
	}
	
	public Client checkClientID(String clientID) {
		Client client = clientList.search(clientID);
		return this.insertMedicalHistory(client);
	}

	public void association() {
	}

	public boolean register(Client client) {
		return clientList.add(client);
	}

	public Client login(String id, String pw) {
		Client client = clientList.search(id, pw);
		return this.insertMedicalHistory(client);
	}

	public boolean delete(String id, String pw) {
		return clientList.delete(clientList.search(id, pw));
	}

	public ClientDao getClientList() {
		return this.clientList;
	}
	
	public MedicalHistoryDao getMedicalHistoryList() {
		return this.medicalHistoryList;
	}
	
	private Client insertMedicalHistory(Client client) {
		MedicalHistory medicalHistory = medicalHistoryList.search(client.getId());
		client.getMedicalHistory().setClientCancerCareer(medicalHistory.getClientCancerCareer());
		client.getMedicalHistory().setFamilyCancerCareer(medicalHistory.getClientCancerCareer());
		client.getMedicalHistory().setNumberOfHospitalizations(medicalHistory.getNumberOfHospitalizations());
		client.getMedicalHistory().setNumberOfHospitalVisits(medicalHistory.getNumberOfHospitalVisits());
		return client;
	}
}