package development.gtgo.adventuresharer.datamodels;

/**
 *  This class is used by FireBase to save locations
 *  Future updates can include replacing this with a LatLng object or Location object
 */
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
