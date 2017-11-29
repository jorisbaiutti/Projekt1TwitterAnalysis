package ch.bfh.chartsmodel;

import be.ceau.chart.Chart;

import java.util.ArrayList;
import java.util.List;

public class GoogleMap implements Chart {
    String name;
    double lat;
    double lng;
    List<GoogleMarker> markers;

    public GoogleMap(String name, double lng, double lat) {
        this.name = name;
        this.lat = lng;
        this.lng = lat;
        markers = new ArrayList<>();
    }

    public void addMarker(GoogleMarker marker){
        this.markers.add(marker);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public List<GoogleMarker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<GoogleMarker> markers) {
        this.markers = markers;
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
