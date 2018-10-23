package development.gtgo.adventuresharer.datamodels;

import com.google.android.gms.maps.model.LatLng;

import java.util.UUID;

/**
 * This model is used to save and retrieve Points of Interests.
 * It saves a title, small description and location
 */
public class POIModel {

    private String poiID;
    public String title;
    public String description;
    private LocationModel location;

    // Empty constructor for FireBase
    public POIModel(){}

    // Default constructor
    public POIModel(String title, String desc, LocationModel loc) {
        this.poiID = UUID.randomUUID().toString();
        this.title = title;
        this.description = desc;
        this.location = loc;
    }

    // Getters
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getPoiID() {
        return poiID;
    }
    public LatLng getLocation() {
        return new LatLng(location.getLat(), location.getLng());
    }

    // Setters
    public void setLocation(LocationModel location) {
        this.location = location;
    }
}
