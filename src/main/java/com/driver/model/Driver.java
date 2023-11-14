package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(value = AccessType.FIELD)
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int DriverId;
     private String mobileno;
     private String password;
     @OneToMany(mappedBy = "tripdriver",cascade = CascadeType.ALL)
     private List<TripBooking> tripbookinglist;
    @OneToOne(mappedBy = "cabdriver",cascade=CascadeType.ALL)
     private Cab cab;
    public Driver() {
    }

    public Driver(String mobileno, String password) {
        this.mobileno = mobileno;
        this.password = password;
        List<TripBooking> tripBookingList=new ArrayList<>();
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public int getDriverId() {
        return DriverId;
    }

    public void setDriverId(int driverId) {
        DriverId = driverId;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TripBooking> getTripbookinglist() {
        return tripbookinglist;
    }

    public void setTripbookinglist(List<TripBooking> tripbookinglist) {
        this.tripbookinglist = tripbookinglist;
    }
}
