package com.solvd.bankapplication.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty("location_id")
    private long locationID;
    @JsonProperty("street_address")
    private String streetAddress;
    private String city;
    private String country;

    public long getLocationID() {
        return locationID;
    }

    public void setLocationID(long locationID) {
        this.locationID = locationID;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Location ID: ").append(locationID).append(System.lineSeparator());
        sb.append("Street address: ").append(streetAddress).append(System.lineSeparator());
        sb.append("City: ").append(city).append(System.lineSeparator());
        sb.append("Country: ").append(country).append(System.lineSeparator());
        return sb.toString();
    }
}
