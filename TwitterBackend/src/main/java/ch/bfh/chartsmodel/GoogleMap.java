package ch.bfh.chartsmodel;

import be.ceau.chart.Chart;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for Google Maps Chart
 */
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

    /**
     * @param marker
     * Adds Marker to map
     */
    public void addMarker(GoogleMarker marker){
        this.markers.add(marker);
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return longitude
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * @return latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return list of markers
     */
    public List<GoogleMarker> getMarkers() {
        return markers;
    }

    /**
     * @param markers
     */
    public void setMarkers(List<GoogleMarker> markers) {
        this.markers = markers;
    }

    /**
     * @return not used for google maps
     */
    @Override
    public String getType() {
        return null;
    }

    /**
     * @return not used for google maps
     */
    @Override
    public String toJson() {
        return null;
    }

    /**
     * @return not used for google maps
     */
    @Override
    public boolean isDrawable() {
        return false;
    }
}
