package ch.bfh.chartsmodel;


/**
 * Model for Markers used in Google Maps
 */
public class GoogleMarker {
    double lat;
    double lng;
    String title;

    public GoogleMarker(String title, double lat,double lng) {
        this.lat = lat;
        this.lng = lng;
        this.title = title;
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
     * @return titel of marker
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
