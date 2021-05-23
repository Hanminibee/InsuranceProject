package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Manager;
import type.ManagerType;

public class ManagerDaoImpl implements ManagerDao{
	
	private StringBuffer query;
	private Connection conn;
	private PreparedStatement ptmt;
	private ResultSet resultSet;

	public ManagerDaoImpl() {
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
	public boolean add(Manager manager) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("INSERT INTO managers");
			query.append("(name, manager_id, manager_password, age, phone_number, manager_type)");
			query.append("VALUES(?, ?, ?, ?, ?, ?);");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, manager.getName());
			ptmt.setString(2, manager.getId());
			ptmt.setString(3, manager.getId());
			ptmt.setInt(4, manager.getAge());
			ptmt.setString(5, manager.getPhoneNumber());
			ptmt.setString(6, manager.getJobPosition().toString());
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
	public boolean delete(Manager manager) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("DELETE FROM managers ");
			query.append("WHERE manager_id = ? AND manager_password = ?");

			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, manager.getId());
			ptmt.setString(2, manager.getPassword());
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
	public Manager search(String managerId, String password) {
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM managers ");
			query.append("WHERE manager_id = ? AND manager_password = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, managerId);
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
	
	private Manager createObject() throws SQLException {
		ManagerType managerType = ManagerType.valueOf(resultSet.getString("manager_type"));
		Manager manager = managerType.getManager().clone();
		manager.setName(resultSet.getString("name"));
		manager.setId(resultSet.getString("manager_id"));
		manager.setPassword(resultSet.getString("manager_password"));
		manager.setAge(resultSet.getInt("age"));
		manager.setPhoneNumber(resultSet.getString("phone_number"));
		manager.setJobPosition(managerType);
		return manager;
	}

}
