package com.solvd.bankapplication.domain;

import java.math.BigDecimal;

public class Atm {
    private long atmID;
    private long locationID;
    private BigDecimal cashAmount;

    public long getAtmID() {
        return atmID;
    }

    public void setAtmID(long atmID) {
        this.atmID = atmID;
    }

    public long getLocationID() {
        return locationID;
    }

    public void setLocationID(long locationID) {
        this.locationID = locationID;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }
}
