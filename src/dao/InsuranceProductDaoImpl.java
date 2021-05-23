package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ActualExpense;
import entity.Cancer;
import entity.InsuranceProduct;
import entity.Life;
import entity.Pension;
import type.ActualExpenseType;
import type.CancerType;
import type.InsuranceProductType;

public class InsuranceProductDaoImpl implements InsuranceProductDao {

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
	public boolean add(InsuranceProduct insuranceProduct) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("INSERT INTO insurance_product");
			query.append(
					"(insurance_product_name, basic_insurance_premium, insurance_money, "
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
			
			query = new StringBuffer();
			switch(insuranceProduct.getInsuranceProductType()) {
			case ACTUALEXPENSE:
				ActualExpense actualExpense = (ActualExpense)insuranceProduct;
				query.append("INSERT INTO actual_expenses");
				query.append("(insurance_product_name, actual_expense_type, limit_of_indemnity, "
								+ "limit_age, self_payment)");
				query.append("VALUES(?, ?, ?, ?, ?);");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, actualExpense.getProductName());
				ptmt.setString(2, actualExpense.getActualExpenseType().toString());
				ptmt.setInt(3, actualExpense.getLimitOfIndemnity());
				ptmt.setInt(4, actualExpense.getLimitAge());
				ptmt.setInt(5, actualExpense.getSelfPayment());
				break;
			case CANCER:
				Cancer cancer = (Cancer)insuranceProduct;
				query.append("INSERT INTO cancers");
				query.append("(insurance_product_name, guaranteed_type, limit_age)");
				query.append("VALUES(?, ?, ?);");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, cancer.getProductName());
				ptmt.setString(2, cancer.getGuaranteedType().toString());
				ptmt.setInt(3, cancer.getLimitAge());
				break;
			case PENSION:
				Pension pension = (Pension)insuranceProduct;
				query.append("INSERT INTO pensions");
				query.append("(insurance_product_name, guaranteed_period)");
				query.append("VALUES(?, ?);");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, pension.getProductName());
				ptmt.setInt(2, pension.getGuaranteedPeriod());
				break;
			case LIFE:
				Life life = (Life)insuranceProduct;
				query.append("INSERT INTO lifes");
				query.append("(insurance_product_name, required_payment_period)");
				query.append("VALUES(?, ?);");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, life.getProductName());
				ptmt.setInt(2, life.getRequiredPaymentPeriod());
				break;
			}
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
	public boolean update(InsuranceProduct insuranceProduct) {
//		boolean success = false;
//		try {
//			query = new StringBuffer();
//			query.append("UPDATE insurance_product SET ");
//			query.append("insurance_product_name = ?, basic_insurance_premium = ?, insurance_money = ?, "
//							+ "payment_cycle = ?, payment_period = ?, approval = ?");
//			query.append("WHERE ;");
//
//			conn = this.getConnection();
//
//			ptmt = conn.prepareStatement(query.toString());
//			ptmt.setString(1, insuranceProduct.getProductName());
//			ptmt.setInt(2, insuranceProduct.getBasicInsurancePremium());
//			ptmt.setInt(3, insuranceProduct.getInsuranceMoney());
//			ptmt.setString(4, insuranceProduct.getInsuranceProductType().toString());
//			ptmt.setInt(5, insuranceProduct.getPaymentCycle());
//			ptmt.setInt(6, insuranceProduct.getPaymentPeriod());
//			ptmt.setInt(7, insuranceProduct.getApproval());
//			ptmt.executeUpdate();
//			
//			query = new StringBuffer();
//			switch(insuranceProduct.getInsuranceProductType()) {
//			case ACTUALEXPENSE:
//				ActualExpense actualExpense = (ActualExpense)insuranceProduct;
//				query.append("INSERT INTO actual_expenses");
//				query.append("(insurance_product_name, actual_expense_type, limit_of_indemnity, "
//								+ "limit_age, self_payment)");
//				query.append("VALUES(?, ?, ?, ?, ?);");
//				conn = this.getConnection();
//				ptmt = conn.prepareStatement(query.toString());
//				ptmt.setString(1, actualExpense.getProductName());
//				ptmt.setString(2, actualExpense.getActualExpenseType().toString());
//				ptmt.setInt(3, actualExpense.getLimitOfIndemnity());
//				ptmt.setInt(4, actualExpense.getLimitAge());
//				ptmt.setInt(5, actualExpense.getSelfPayment());
//				break;
//			case CANCER:
//				Cancer cancer = (Cancer)insuranceProduct;
//				query.append("INSERT INTO cancers");
//				query.append("(insurance_product_name, guaranteed_type, limit_age)");
//				query.append("VALUES(?, ?, ?);");
//				conn = this.getConnection();
//				ptmt = conn.prepareStatement(query.toString());
//				ptmt.setString(1, cancer.getProductName());
//				ptmt.setString(2, cancer.getGuaranteedType().toString());
//				ptmt.setInt(3, cancer.getLimitAge());
//				break;
//			case PENSION:
//				Pension pension = (Pension)insuranceProduct;
//				query.append("INSERT INTO pensions");
//				query.append("(insurance_product_name, guaranteed_period)");
//				query.append("VALUES(?, ?);");
//				conn = this.getConnection();
//				ptmt = conn.prepareStatement(query.toString());
//				ptmt.setString(1, pension.getProductName());
//				ptmt.setInt(2, pension.getGuaranteedPeriod());
//				break;
//			case LIFE:
//				Life life = (Life)insuranceProduct;
//				query.append("INSERT INTO lifes");
//				query.append("(insurance_product_name, required_payment_period)");
//				query.append("VALUES(?, ?);");
//				conn = this.getConnection();
//				ptmt = conn.prepareStatement(query.toString());
//				ptmt.setString(1, life.getProductName());
//				ptmt.setInt(2, life.getRequiredPaymentPeriod());
//				break;
//			}
//			ptmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			this.close();
//		}
//		return success;
		return false;
	}

	@Override
	public boolean delete(InsuranceProduct insuranceProduct) {
		boolean success = false;
		try {
			query = new StringBuffer();
			query.append("DELETE FROM insurance_product ");
			query.append("WHERE insurance_product_name = ?;");

			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, insuranceProduct.getProductName());
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
	public InsuranceProduct search(String productName) {
		try {
			query = new StringBuffer();
			query.append("SELECT insurance_product_type, insurance_product_name FROM insurance_product");
			query.append("WHERE insurance_product_name = ?;");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setString(1, productName);
			
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

	private InsuranceProduct createObject() throws SQLException {
		InsuranceProductType productType = InsuranceProductType.valueOf(resultSet.getString("insurance_product_type"));
		String productName = resultSet.getString("insurance_product_name");
		switch (productType) {
			case ACTUALEXPENSE:
				query = new StringBuffer();
				query.append("SELECT * FROM insurance_product JOIN actual_expenses");
				query.append("ON insurance_product_name = ?;");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, productName);
				resultSet = ptmt.executeQuery();
				return createActualExpense();
			case CANCER:
				query = new StringBuffer();
				query.append("SELECT * FROM insurance_product JOIN cancers");
				query.append("ON insurance_product_name = ?;");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, productName);
				resultSet = ptmt.executeQuery();
				return createCancer();
			case PENSION:
				query = new StringBuffer();
				query.append("SELECT * FROM insurance_product JOIN pensions");
				query.append("ON insurance_product_name = ?;");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, productName);
				resultSet = ptmt.executeQuery();
				return createPension();
			case LIFE:
				query = new StringBuffer();
				query.append("SELECT * FROM insurance_product JOIN lifes");
				query.append("ON insurance_product_name = ?;");
				conn = this.getConnection();
				ptmt = conn.prepareStatement(query.toString());
				ptmt.setString(1, productName);
				resultSet = ptmt.executeQuery();
				return createLife();
		}
		return null;
	}

	private ActualExpense createActualExpense() throws SQLException {
		ActualExpense actualExpense = new ActualExpense();
		actualExpense.setInsuranceProductType(InsuranceProductType.ACTUALEXPENSE);
		actualExpense.setProductName(resultSet.getString("insurance_product_name"));
		actualExpense.setBasicInsurancePremium(resultSet.getInt("basic_insurance_premium"));
		actualExpense.setInsuranceMoney(resultSet.getInt("insurance_money"));
		actualExpense.setPaymentCycle(resultSet.getInt("payment_cycle"));
		actualExpense.setPaymentPeriod(resultSet.getInt("payment_period"));
		actualExpense.setApproval((resultSet.getInt("approval")==1)? true : false);
		
		actualExpense.setActualExpenseType(ActualExpenseType.valueOf(resultSet.getString("actual_expense_type")));
		actualExpense.setLimitOfIndemnity(resultSet.getInt("limit_of_indemnity"));
		actualExpense.setLimitAge(resultSet.getInt("limit_age"));
		actualExpense.setSelfPayment(resultSet.getInt("self_payment"));
		return actualExpense;
	}

	private InsuranceProduct createCancer() throws SQLException {
		Cancer cancer = new Cancer();
		cancer.setInsuranceProductType(InsuranceProductType.ACTUALEXPENSE);
		cancer.setProductName(resultSet.getString("insurance_product_name"));
		cancer.setBasicInsurancePremium(resultSet.getInt("basic_insurance_premium"));
		cancer.setInsuranceMoney(resultSet.getInt("insurance_money"));
		cancer.setPaymentCycle(resultSet.getInt("payment_cycle"));
		cancer.setPaymentPeriod(resultSet.getInt("payment_period"));
		cancer.setApproval((resultSet.getInt("approval")==1)? true : false);
		
		cancer.setGuaranteedType(CancerType.valueOf(resultSet.getString("guaranteed_type")));
		cancer.setLimitAge(resultSet.getInt("limit_age"));
		return cancer;
	}

	private InsuranceProduct createPension() throws SQLException {
		Pension pension = new Pension();
		pension.setInsuranceProductType(InsuranceProductType.ACTUALEXPENSE);
		pension.setProductName(resultSet.getString("insurance_product_name"));
		pension.setBasicInsurancePremium(resultSet.getInt("basic_insurance_premium"));
		pension.setInsuranceMoney(resultSet.getInt("insurance_money"));
		pension.setPaymentCycle(resultSet.getInt("payment_cycle"));
		pension.setPaymentPeriod(resultSet.getInt("payment_period"));
		pension.setApproval((resultSet.getInt("approval")==1)? true : false);
		
		pension.setGuaranteedPeriod(resultSet.getInt("guaranteed_period"));
		return pension;
	}

	private InsuranceProduct createLife() throws SQLException {
		Life life = new Life();
		life.setInsuranceProductType(InsuranceProductType.ACTUALEXPENSE);
		life.setProductName(resultSet.getString("insurance_product_name"));
		life.setBasicInsurancePremium(resultSet.getInt("basic_insurance_premium"));
		life.setInsuranceMoney(resultSet.getInt("insurance_money"));
		life.setPaymentCycle(resultSet.getInt("payment_cycle"));
		life.setPaymentPeriod(resultSet.getInt("payment_period"));
		life.setApproval((resultSet.getInt("approval")==1)? true : false);
		
		life.setRequiredPaymentPeriod(resultSet.getInt("required_payment_period"));
		return life;
	}
	
	@Override
	public ArrayList<InsuranceProduct> searchListByApproval(boolean approval) {
		ArrayList<InsuranceProduct> list = new ArrayList<InsuranceProduct>();
		try {
			query = new StringBuffer();
			query.append("SELECT * FROM insurance_product");
			query.append("WHERE approval = ?;");
			conn = this.getConnection();
			ptmt = conn.prepareStatement(query.toString());
			ptmt.setInt(1, approval? 1 : 0);
			resultSet = ptmt.executeQuery();
			while(resultSet.next()) {
				InsuranceProduct insuranceProduct = this.createObject();
				list.add(insuranceProduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return list;
	}
}
