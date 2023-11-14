package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.driver.model.TripStatus.*;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) throws Exception {
		// Delete customer without using deleteById function

		Customer customer = customerRepository2.findById(customerId).get();
		if(customer == null){
			throw new Exception("Customer Not Exist");
		}
		customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
       TripBooking tripBooking=new TripBooking();
	   tripBooking.setFromLocation(fromLocation);
	   tripBooking.setToLocation(toLocation);
	   tripBooking.setDistanceInkm(distanceInKm);
	  tripBooking.setCustomer(customerRepository2.findById(customerId).get());
	   tripBooking.setStatus(CONFIRMED);

		 Iterable<Driver> drivers = driverRepository2.findAll();
		 Driver driver = null;
		 for( Driver d : drivers){
            if(d.getCab().isAvailable() == true){
				driver = d;
				break;
			}
		 }
		 if(driver == null){
			 throw new Exception("No cab available!");
		 }
		 Customer customer = customerRepository2.findById(customerId).get();
		 customer.getTripBookingList().add(tripBooking);
		 driver.getCab().setAvailable(false);
		 int bill = driver.getCab().getPerkmRate()*distanceInKm;
		 tripBooking.setBill(bill);
		tripBookingRepository2.save(tripBooking);
         return tripBooking;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
       TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
	   tripBooking.setStatus(CANCELED);
	   Driver driver = tripBooking.getTripdriver();
	   driver.getCab().setAvailable(true);
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(COMPLETED);
		Driver driver = tripBooking.getTripdriver();
		driver.getCab().setAvailable(true);
	}
}
