package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.MedicalHistory;
import type.CancerType;

public class MedicalHistoryDaoImpl implements MedicalHistoryDao {
	
	private StringBuffer query;
	private Connection conn;
	private PreparedStatement ptmt;
	private ResultSet resultSet;

	public MedicalHistoryDaoImpl() {
		this.query = null;
		this.conn = null;
		this.ptmt = null;
		this.resultSet = null;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	private void close() {
		try {
			if (ptmt != null)
				ptmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean add(String clientId, MedicalHistory medicalHistory) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("INSERT INTO medical_historys");
			query.append("(client_id, client_cancer_career, family_cancer_career, number_of_hospitalizations, number_of_hospitalVisits) ");
			query.append("VALUES(?, ?, ?, ?, ?);");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, clientId);
			CancerType clientCancer = medicalHistory.getClientCancerCareer();
			CancerType familyCancer = medicalHistory.getFamilyCancerCareer();
			ptmt.setString(2, clientCancer == null? CancerType.HEALTHY.toString() : clientCancer.toString());			
			ptmt.setString(3, familyCancer == null? CancerType.HEALTHY.toString() : familyCancer.toString());
			ptmt.setInt(4, medicalHistory.getNumberOfHospitalizations());
			ptmt.setInt(5, medicalHistory.getNumberOfHospitalVisits());
			int rowAmount = ptmt.executeUpdate();
			if(rowAmount > 0)
				success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return success;
	}

	@Override
	public boolean update(String clientId, MedicalHistory medicalHistory) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("UPDATE medical_historys ");
			query.append("SET client_cancer_career = ?, family_cancer_career = ?, number_of_hospitalizations = ?, number_of_hospitalVisits = ? ");
			query.append("WHERE client_id = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			CancerType clientCancer = medicalHistory.getClientCancerCareer();
			CancerType familyCancer = medicalHistory.getFamilyCancerCareer();
			ptmt.setString(1, clientCancer == null? CancerType.HEALTHY.toString() : clientCancer.toString());			
			ptmt.setString(2, familyCancer == null? CancerType.HEALTHY.toString() : familyCancer.toString());
			ptmt.setInt(3, medicalHistory.getNumberOfHospitalizations());
			ptmt.setInt(4, medicalHistory.getNumberOfHospitalVisits());
			ptmt.setString(5, clientId);
			int rowAmount = ptmt.executeUpdate();
			if(rowAmount > 0)
				success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return success;
	}

	@Override
	public MedicalHistory search(String clientId) {
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM medical_historys ");
			query.append("WHERE client_id = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, clientId);
			resultSet = ptmt.executeQuery();
			if(resultSet.next()) {
				return this.createObject();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}

	private MedicalHistory createObject() throws SQLException {
		MedicalHistory medicalHistory = new MedicalHistory();
		medicalHistory.setClientCancerCareer(CancerType.valueOf(resultSet.getString("client_cancer_career")));
		medicalHistory.setFamilyCancerCareer(CancerType.valueOf(resultSet.getString("family_cancer_career")));
		medicalHistory.setNumberOfHospitalizations(resultSet.getInt("number_of_hospitalizations"));
		medicalHistory.setNumberOfHospitalVisits(resultSet.getInt("number_of_hospitalVisits"));
		return medicalHistory;
	}

}
