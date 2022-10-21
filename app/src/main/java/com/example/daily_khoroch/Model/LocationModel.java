package com.example.daily_khoroch.Model;

public class LocationModel {
    String locationName;
    String lat;
    String lang;

    public LocationModel(String locationName, String lat, String lang) {
        this.locationName = locationName;
        this.lat = lat;
        this.lang = lang;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
