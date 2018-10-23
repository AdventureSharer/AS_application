package development.gtgo.adventuresharer.datamodels;

import java.util.List;

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
