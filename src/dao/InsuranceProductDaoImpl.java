package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.InsuranceProduct;

public class InsuranceProductDaoImpl implements InsuranceProductDao{

	private StringBuffer query;
	private Connection conn;
	private PreparedStatement ptmt;
	private ResultSet resultSet;
	
	public InsuranceProductDaoImpl() {
		this.query = null;
		this.conn = null;
		this.ptmt = null;
		this.resultSet = null;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public boolean add(InsuranceProduct insuranceProduct) {
		try {
			query = new StringBuffer();
			query.append("INSERT INTO insurance_product");
			query.append("(insurance_product_name, basic_insurance_premium, insurance_money, "
					+ "insurance_product_type, payment_cycle, payment_period, approval)");
			query.append("VALUES(?, ?, ?, ?, ?, ?, ?);");
			
			conn = this.getConnection();
			
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, insuranceProduct.getProductName());
			ptmt.setInt(2, insuranceProduct.getBasicInsurancePremium());
			ptmt.setInt(3, insuranceProduct.getInsuranceMoney());
			ptmt.setString(4, insuranceProduct.getInsuranceProductType().toString());
			ptmt.setInt(5, insuranceProduct.getPaymentCycle());
			ptmt.setInt(6, insuranceProduct.getPaymentPeriod());
			ptmt.setInt(7, insuranceProduct.getApproval());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(InsuranceProduct insuranceProduct) {
		
		return false;
	}

	@Override
	public InsuranceProduct search(String productName) {
		
		return null;
	}

}
