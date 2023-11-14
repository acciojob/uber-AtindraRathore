package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "CabTable")
@Access(value = AccessType.FIELD)
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int CabId;
    @Column(nullable = true)
    private int perkmRate;

    @Column(nullable = true)
    private boolean Available;
    @OneToOne
    @JoinColumn
    private Driver cabdriver;
    public Cab() {
    }

    public Driver getCabdriver() {
        return cabdriver;
    }

    public void setCabdriver(Driver cabdriver) {
        this.cabdriver = cabdriver;
    }

    public Cab(int perkmRate, boolean available) {
        this.perkmRate = perkmRate;
        Available = available;
    }

    public int getCabId() {
        return CabId;
    }

    public void setCabId(int cabId) {
        CabId = cabId;
    }

    public int getPerkmRate() {
        return perkmRate;
    }

    public void setPerkmRate(int perkmRate) {
        this.perkmRate = perkmRate;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }
}
