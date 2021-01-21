package com.tavant.springboot1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot1.dao.OfficeDao;
import com.tavant.springboot1.dao.OfficeDao;
import com.tavant.springboot1.exceptions.InvalidNameException;
import com.tavant.springboot1.exceptions.InvalidSalaryException;
import com.tavant.springboot1.model.Office;
import com.tavant.springboot1.model.Office;

@Service("OfficeServiceImpl")
public class OfficeServiceImpl implements OfficeService {
	
	@Autowired
	private OfficeDao officeDaoImpl;
	
	@Override
	public boolean addOffice(Office office) {
		return this.officeDaoImpl.addOffice(office);
	}

	@Override
	public Optional<Office> updateOffice(Integer officeCode, Office Office)
			throws InvalidNameException, InvalidSalaryException {
		return this.officeDaoImpl.updateOffice(officeCode, Office);
	}

	@Override
	public Optional<List<Office>> getOffices() {

		return this.officeDaoImpl.getOffices();
	}

	@Override
	public Optional<Office> deleteOffice(Integer officeCode) {
		return this.officeDaoImpl.deleteOffice(officeCode);
	}

	@Override
	public Optional<Office> getOfficeById(Integer officeCode) {
		return this.officeDaoImpl.getOfficeById(officeCode);
	}

	@Override
	public boolean isExists(Integer officeCode) {
		return this.officeDaoImpl.isExists(officeCode);
	}

}
