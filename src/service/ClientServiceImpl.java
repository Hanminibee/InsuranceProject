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
		return clientList.search(clientID);
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
	
	public boolean addMedicalHistory(Client clientLogin) {
		return medicalHistoryList.add(clientLogin.getId(), clientLogin.getMedicalHistory());
	}
	
	public boolean modifyMedicalHistory(Client clientLogin) {
		return medicalHistoryList.update(clientLogin.getId(), clientLogin.getMedicalHistory());
	}
	
	private Client insertMedicalHistory(Client client) {
		if(client != null) {
			MedicalHistory medicalHistory = medicalHistoryList.search(client.getId());
			if(medicalHistory != null) {
				client.getMedicalHistory().setClientCancerCareer(medicalHistory.getClientCancerCareer());
				client.getMedicalHistory().setFamilyCancerCareer(medicalHistory.getClientCancerCareer());
				client.getMedicalHistory().setNumberOfHospitalizations(medicalHistory.getNumberOfHospitalizations());
				client.getMedicalHistory().setNumberOfHospitalVisits(medicalHistory.getNumberOfHospitalVisits());
			}
		}
		return client;
	}
}