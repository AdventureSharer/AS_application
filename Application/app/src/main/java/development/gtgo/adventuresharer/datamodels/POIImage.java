package development.gtgo.adventuresharer.datamodels;

import android.net.Uri;

/**
 *  This object it used to save and retrieve an image for the Points of Interests
 *  it uses a unique ID to help save and retrieve it from FireBase storage
 */
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
