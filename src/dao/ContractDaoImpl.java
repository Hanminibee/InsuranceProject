package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Contract;

public class ContractDaoImpl implements ContractDao{

	private StringBuffer query;
	private Connection conn;
	private PreparedStatement ptmt;
	private ResultSet resultSet;

	public ContractDaoImpl() {
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
	public boolean add(Contract contract) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Contract contract) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Contract search(String clientID, String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Contract> searchByClient(String clientID) {
		// TODO Auto-generated method stub
		return null;
	}

}