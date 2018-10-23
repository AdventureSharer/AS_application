package development.gtgo.adventuresharer.datamodels;

public class LocationModel {

    private double lat, lng;

    // Empty constructor for FireBase
    public LocationModel(){}

    // Default constructor
    public LocationModel(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // GETTERS
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }
}
