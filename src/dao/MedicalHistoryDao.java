package dao;

import entity.MedicalHistory;

public interface MedicalHistoryDao {
	public boolean add(String clientId, MedicalHistory medicalHistory);
	public boolean update(String clientId, MedicalHistory medicalHistory);
	public MedicalHistory search(String clientId);
}
