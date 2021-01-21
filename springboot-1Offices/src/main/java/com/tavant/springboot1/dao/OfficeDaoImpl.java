package com.tavant.springboot1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tavant.springboot1.exceptions.InvalidNameException;
import com.tavant.springboot1.exceptions.InvalidSalaryException;
import com.tavant.springboot1.model.Employee;
import com.tavant.springboot1.model.Office;
import com.tavant.springboot1.utils.DBUtils;

@Repository("employeeDaoImpl")
public class OfficeDaoImpl implements OfficeDao {
	@Autowired
	private DBUtils dbUtils;

	public boolean addOffice(Office office) {
		Employee employee = null;
		Connection connection = dbUtils.getConnection();

		try {
			String query = "INSERT into offices" + " (officeCode , city , phone , addressLine1 , "
					+ "addressLine2 , state , country , postalCode , territory) values (? , ? , ? , ? , ?, ? , ? , ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, office.getOfficeCode());
			statement.setString(2, office.getCity());
			statement.setString(3,office.getPhone());
			statement.setString(4, office.getAddressLine1());
			statement.setString(5, office.getAddressLine2());
			statement.setString(6, office.getState());
			statement.setString(7, office.getCountry());
			statement.setString(8, office.getPostalCode());
			statement.setString(9, office.getTerritory());

			int updated = statement.executeUpdate();

			return (updated == 1) ? true : false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return false;
	}

	@Override
	public Optional<Office> updateOffice(Integer officeCode, Office office)
			throws InvalidSalaryException, InvalidNameException {

		String query = "UPDATE offices SET city = ?, phone = ?, addressLine1 = ?,addressLine2 = ?, state = ?, country = ?, postalCode = ?, territory = ? WHERE officeCode = ?";

		Connection connection = dbUtils.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, office.getCity());
			statement.setString(2, office.getPhone());

			statement.setString(3, office.getAddressLine1());
			statement.setString(4, office.getAddressLine1());
			statement.setString(5, office.getState());
			statement.setString(6, office.getCountry());
			statement.setString(7, office.getPostalCode());
			statement.setString(8, office.getTerritory());
			statement.setInt(9, officeCode);

			int updated = statement.executeUpdate();
			if (updated > 0) {
				return getOfficeById(officeCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}

		return Optional.empty();
	}

	@Override
	public Optional<List<Office>> getOffices() {
		List<Office> offices = new ArrayList<>();
		Connection connection = dbUtils.getConnection();
		try {

			Statement statement = connection.createStatement();
			String query = "SELECT * FROM offices ";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Office office = new Office();
				office.setOfficeCode(resultSet.getInt("officeCode"));
				office.setCity(resultSet.getString("city"));
				office.setPhone(resultSet.getString("phone"));
				office.setAddressLine1(resultSet.getString("addressLine1"));
				office.setAddressLine2(resultSet.getString("addressLine2"));
				office.setState(resultSet.getString("state"));
				office.setCountry(resultSet.getString("country"));
				office.setPostalCode(resultSet.getString("postalCode"));
				office.setTerritory(resultSet.getString("territory"));

				offices.add(office);
			}

			return Optional.of(offices);
		} catch (Exception e) {

		} finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Office> deleteOffice(Integer officeCode) {
		Office office = null;
		Connection connection = dbUtils.getConnection();

		try {
			Optional<Office> optional = getOfficeById(officeCode);
			if (!optional.isPresent()) {
				return Optional.ofNullable(office);
			} else {
				office = optional.get();
			}
			PreparedStatement statement = connection.prepareStatement("DELETE FROM employees where employeeNumber = ?");
			statement.setInt(1, officeCode);
			int updated = statement.executeUpdate();

			if (updated > 0) {
				return Optional.ofNullable(office);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.ofNullable(office);
	}

	@Override
	public Optional<Office> getOfficeById(Integer officeCode) {

		Office office = null;
		Connection connection = dbUtils.getConnection();

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM offices where officeCode = ?");
			statement.setInt(1, officeCode);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				office = new Office();
				office.setOfficeCode(resultSet.getInt("officeCode"));
				office.setCity(resultSet.getString("city"));
				office.setPhone(resultSet.getString("phone"));
				office.setAddressLine1(resultSet.getString("addressLine1"));
				office.setAddressLine2(resultSet.getString("addressLine2"));
				office.setState(resultSet.getString("state"));
				office.setCountry(resultSet.getString("country"));
				office.setPostalCode(resultSet.getString("postalCode"));
				office.setTerritory(resultSet.getString("territory"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.ofNullable(office);

	}

	@Override
	public boolean isExists(Integer officeCode) {
		return getOfficeById(officeCode).isPresent();
	}

}
