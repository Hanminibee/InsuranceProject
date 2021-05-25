package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Client;
import type.ClientJobType;

public class ClientDaoImpl implements ClientDao{

	private StringBuffer query;
	private Connection conn;
	private PreparedStatement ptmt;
	private ResultSet resultSet;

	public ClientDaoImpl() {
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
	public boolean add(Client client) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("INSERT INTO clients");
			query.append("(name, client_id, client_password, age, email, gender, phone_number, bank_account_number, resident_registrationNumber, address, job) ");
			query.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, client.getName());
			ptmt.setString(2, client.getId());
			ptmt.setString(3, client.getPassword());
			ptmt.setInt(4, client.getAge());
			ptmt.setString(5, client.getEmail());
			ptmt.setInt(6, client.isGender()? 1 : 0);
			ptmt.setString(7, client.getPhoneNumber());
			ptmt.setString(8, client.getBankAccountNumber());
			ptmt.setString(9, client.getResidentRegistrationNumber());
			ptmt.setString(10, client.getAddress());
			ptmt.setString(11, client.getJob().toString());
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
	public boolean delete(Client client) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("DELETE FROM clients ");
			query.append("WHERE client_id = ? AND client_password = ?");

			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, client.getId());
			ptmt.setString(2, client.getPassword());
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
	public Client search(String clientId) {
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM clients ");
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

	@Override
	public Client search(String clientId, String password) {
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM clients ");
			query.append("WHERE client_id = ? AND client_password = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, clientId);
			ptmt.setString(2, password);
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

	private Client createObject() throws SQLException {
		Client client = new Client();
		client.setName(resultSet.getString("name"));
		client.setId(resultSet.getString("client_id"));
		client.setPassword(resultSet.getString("client_password"));
		client.setAge(resultSet.getInt("age"));
		client.setEmail(resultSet.getString("email"));
		client.setGender(resultSet.getInt("gender")==1? true : false);
		client.setPhoneNumber(resultSet.getString("phone_number"));
		client.setBankAccountNumber(resultSet.getString("resident_registrationNumber"));
		client.setResidentRegistrationNumber(resultSet.getString("resident_registrationNumber"));
		client.setAddress(resultSet.getString("address"));
		client.setJob(ClientJobType.valueOf(resultSet.getString("job")));
		return client;
	}

}