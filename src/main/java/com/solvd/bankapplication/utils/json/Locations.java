package com.solvd.bankapplication.utils.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.bankapplication.domain.Location;

import java.util.List;

public class Locations {
    @JsonProperty("locations")
    List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
