package development.gtgo.adventuresharer.datamodels;

import java.util.List;

/**
 *  This is the object that is used to retrieve and save a route inside of database.
 *  It saves a list of each location points (latitude and longitude) gathered from tracking the route
 *  It also saves a list of all the Points of Interest that a user may have taken
 *  It also has other details that are vital to the description or saving/retrieval of the data
 */
public class HikingRoute {
    private String routeID;
    private String name;
    private String description;
    private float difficulty;
    private String userID;
    private List<LocationModel> route;
    private List<POIModel> poi;

    // Empty constructor for FireBase
    public HikingRoute() {}

    // Default constructor with POI
    public HikingRoute(String rID, String name, String description, float difficulty, String uID, List<LocationModel> route, List<POIModel> poi){
        this.routeID = rID;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.userID = uID;
        this.route = route;
        this.poi = poi;
    }

    // Default constructor without POI
    public HikingRoute(String rID, String name, String description, float difficulty, String uID, List<LocationModel> route){
        this.routeID = rID;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.userID = uID;
        this.route = route;
    }

    /** GETTERS **/
    public String getRouteID() {
        return routeID;
    }
    public String getName() {
        return name;
    }
    public List<LocationModel> getRoute() {
        return route;
    }
    public List<POIModel> getPoi() {
        return poi;
    }
    public String getDescription() {
        return description;
    }
    public String getUserID() {
        return userID;
    }

}
