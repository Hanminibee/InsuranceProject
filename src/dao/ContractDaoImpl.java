package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Client;
import entity.Contract;
import entity.InsuranceProduct;
import entity.Manager;
import entity.SalesPerson;

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
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("INSERT INTO contracts");
			query.append("(client_id, insurance_product_name, insurance_contract_date, insurance_expiry_date, manager_id, approval) ");
			query.append("VALUES(?, ?, ?, ?, ?, ?);");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, contract.getClient().getId());
			ptmt.setString(2, contract.getInsuranceProduct().getProductName());
			ptmt.setDate(3, new Date(contract.getInsuranceContractDate().getTime()));
			ptmt.setDate(4, new Date(contract.getInsuranceExpiryDate().getTime()));
			ptmt.setString(5, contract.getSalesPerson().getId());
			ptmt.setInt(6, contract.isApproval()? 1 : 0);
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
	public boolean delete(Contract contract) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("DELETE FROM contracts ");
			query.append("WHERE client_id = ? AND insurance_product_name");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, contract.getClient().getId());
			ptmt.setString(2, contract.getInsuranceProduct().getProductName());
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
	public Contract search(String clientID, String productName) {
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM contracts ");
			query.append("WHERE client_id = ? AND insurance_product_name = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, clientID);
			ptmt.setString(2, productName);
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
	public ArrayList<Contract> searchByClient(String clientID) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM contracts ");
			query.append("WHERE client_id = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, clientID);
			resultSet = ptmt.executeQuery();
			while(resultSet.next()) {
				Contract contract = this.createObject();
				list.add(contract);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}

	@Override
	public ArrayList<Contract> searchBySalesPerson(String salesPerson) {
		ArrayList<Contract> list = new ArrayList<Contract>();
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM contracts ");
			query.append("WHERE manager_id = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, salesPerson);
			resultSet = ptmt.executeQuery();
			while(resultSet.next()) {
				Contract contract = this.createObject();
				list.add(contract);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}
	
	@Override
	public boolean update(Contract contract) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("UPDATE contracts ");
			query.append("SET insurance_contract_date = ?, insurance_expiry_date = ?, manager_id = ?, approval = ?");
			query.append("WHERE client_id = ? AND insurance_product_name = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setDate(1, new Date(contract.getInsuranceContractDate().getTime()));
			ptmt.setDate(2, new Date(contract.getInsuranceExpiryDate().getTime()));
			ptmt.setString(3, contract.getSalesPerson().getId());
			ptmt.setInt(4, contract.isApproval()? 1 : 0);
			ptmt.setString(5, contract.getClient().getId());
			ptmt.setString(6, contract.getInsuranceProduct().getProductName());
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
	
	public ArrayList<Contract> findAll(){
		ArrayList<Contract> list = new ArrayList<Contract>();
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM contracts;");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			resultSet = ptmt.executeQuery();
			while(resultSet.next()) {
				Contract contract = this.createObject();
				list.add(contract);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}
	
	private Contract createObject() throws SQLException {
		Contract contract = new Contract();
		Client client = new Client();
		client.setId(resultSet.getString("client_id"));
		contract.setClient(client);
		InsuranceProduct insuranceProduct = new InsuranceProduct();
		insuranceProduct.setProductName(resultSet.getString("insurance_product_name"));
		contract.setInsuranceProduct(insuranceProduct);
		contract.setInsuranceContractDate(resultSet.getDate("insurance_contract_date"));
		contract.setInsuranceExpiryDate(resultSet.getDate("insurance_expiry_date"));
		Manager salesPerson = new SalesPerson();
		salesPerson.setId(resultSet.getString("manager_id"));
		contract.setSalesPerson(salesPerson);
		contract.setApproval(resultSet.getInt("approval") == 1? true : false);
		return contract;
	}

}