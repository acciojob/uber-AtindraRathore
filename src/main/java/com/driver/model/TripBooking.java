package com.driver.model;

import javax.persistence.*;

@Entity
@Access(value = AccessType.FIELD)
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int TripBookingId;

    private String FromLocation;

    private String toLocation;

    private int distanceInkm;

    private TripStatus Status;

    private int bill;
    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Driver tripdriver;

    public TripBooking() {
    }

    public TripBooking(String fromLocation, String toLocation, int distanceInkm, TripStatus status, int bill) {
        FromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInkm = distanceInkm;
        Status = status;
        this.bill = bill;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getTripdriver() {
        return tripdriver;
    }

    public void setTripdriver(Driver tripdriver) {
        this.tripdriver = tripdriver;
    }


    public int getTripBookingId() {
        return TripBookingId;
    }

    public void setTripBookingId(int tripBookingId) {
        TripBookingId = tripBookingId;
    }

    public String getFromLocation() {
        return FromLocation;
    }

    public void setFromLocation(String fromLocation) {
        FromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getDistanceInkm() {
        return distanceInkm;
    }

    public void setDistanceInkm(int distanceInkm) {
        this.distanceInkm = distanceInkm;
    }

    public TripStatus getStatus() {
        return Status;
    }

    public void setStatus(TripStatus status) {
        Status = status;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }
}
