package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Accident;
import entity.Client;
import entity.InsuranceProduct;

public class AccidentDaoImpl implements AccidentDao{
	
	private StringBuffer query;
	private Connection conn;
	private PreparedStatement ptmt;
	private ResultSet resultSet;

	public AccidentDaoImpl() {
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
	public boolean add(Accident accident) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("INSERT INTO accidents");
			query.append("(client_Id, insurance_product_name, accident_detail, reception_date) ");
			query.append("VALUES(?, ?, ?, ?);");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, accident.getClient().getId());
			ptmt.setString(2, accident.getInsuranceProduct().getProductName());
			ptmt.setString(3, accident.getAccidentDetail());
			ptmt.setDate(4, (Date)accident.getReceptionDate());
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
	public boolean delete(Accident accident) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("DELETE FROM accidents ");
			query.append("WHERE accident_num = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setInt(1, accident.getAccidentNum());
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
	public Accident search(int accidentNum) {
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM accidents ");
			query.append("WHERE accident_num = ?");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setInt(1, accidentNum);
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
	public ArrayList<Accident> findAll() {
		ArrayList<Accident> list = new ArrayList<Accident>();
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM accidents;");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			resultSet = ptmt.executeQuery();
			while(resultSet.next()) {
				Accident accident = this.createObject();
				list.add(accident);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return list;
	}
	
	private Accident createObject() throws SQLException {
		Accident accident = new Accident();
		accident.setAccidentNum(resultSet.getInt("accident_num"));
		accident.setAccidentDetail(resultSet.getString("accident_detail"));
		accident.setReceptionDate((Date)resultSet.getDate("reception_date"));
		Client client = new Client();
		client.setId(resultSet.getString("client_Id"));
		accident.setClient(client);
		InsuranceProduct insuranceProduct = new InsuranceProduct();
		insuranceProduct.setProductName(resultSet.getString("insurance_product_name"));
		accident.setInsuranceProduct(insuranceProduct);
		return accident;
	}

}