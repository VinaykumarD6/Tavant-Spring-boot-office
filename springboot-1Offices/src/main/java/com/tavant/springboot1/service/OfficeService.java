package com.tavant.springboot1.service;

import java.util.List;
import java.util.Optional;

import com.tavant.springboot1.exceptions.InvalidNameException;
import com.tavant.springboot1.exceptions.InvalidSalaryException;
import com.tavant.springboot1.model.Office;

public interface OfficeService {
	public boolean addOffice(Office office);
	public Optional<Office> updateOffice(Integer officeCode , Office office) 
			throws InvalidSalaryException , InvalidNameException;
	public Optional<List<Office>> getOffices();
	public Optional<Office> deleteOffice(Integer officeCode);
	public Optional<Office> getOfficeById(Integer officeCode);
	public boolean isExists(Integer officeCode);
}
