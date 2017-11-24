package ch.bfh.chartsmodel;

import be.ceau.chart.Chart;

public class MapsGeolocation implements Chart {
    double longitude;
    double latitude;

    public MapsGeolocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public boolean isDrawable() {
        return false;
    }
}
