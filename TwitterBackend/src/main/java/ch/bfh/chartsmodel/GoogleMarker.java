package ch.bfh.chartsmodel;


public class GoogleMarker {
    double lat;
    double lng;
    String title;

    public GoogleMarker(String title, double lat,double lng) {
        this.lat = lat;
        this.lng = lng;
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
