package com.driver.services.impl;

import java.util.List;
import java.util.Optional;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository1;

	@Autowired
	DriverRepository driverRepository1;

	@Autowired
	CustomerRepository customerRepository1;

	@Override
	public void adminRegister(Admin admin) {
		//Save the admin in the database
		adminRepository1.save(admin);
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) throws AdminNotPresentException{
		//Update the password of admin with given id
        Optional<Admin> admin = adminRepository1.findById(adminId);
		if(!admin.isPresent()){
			throw new AdminNotPresentException("Admin Not Present in DB");
		}
		Admin adminforupdate = admin.get();
		adminforupdate.setPassword(password);
		adminRepository1.save(adminforupdate);
        return adminforupdate;
	}

	@Override
	public void deleteAdmin(int adminId) throws AdminNotPresentException{
		// Delete admin without using deleteById function
       Optional<Admin> adminOptional= adminRepository1.findById(adminId);
	   if(!adminOptional.isPresent()){
         throw new AdminNotPresentException("Admin Not Present in DB");
	   }
	   Admin adminfordelete = adminOptional.get();
	   adminRepository1.deleteById(adminId);
	}

	@Override
	public List<Driver> getListOfDrivers() throws DriverNotPresentException{
		//Find the list of all drivers
      List<Driver> drivers=driverRepository1.findAll();
	  if(drivers == null){
       throw new DriverNotPresentException("Driver Not Exist");
	  }

	  return drivers;
	}

	@Override
	public List<Customer> getListOfCustomers() throws CustomerNotExistException{
		//Find the list of all customers
        List<Customer> customerList=customerRepository1.findAll();
		if(customerList == null){
          throw new CustomerNotExistException("Customer Not Exist");
		}
		return customerList;
	}

}
