package development.gtgo.adventuresharer.datamodels;

import android.net.Uri;

public class POIImage {

    private Uri imageUri;
    private String poiID;

    // Empty constructor for FireBase
    public POIImage(){}

    // Default constructor
    public POIImage(Uri imURI, String imID){
        this.imageUri = imURI;
        this.poiID = imID;
    }

    // GETTERS
    public Uri getImageUri() {
        return imageUri;
    }
    public String getPoiID() {
        return poiID;
    }

}
