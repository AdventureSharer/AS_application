package development.gtgo.adventuresharer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;


/**
 *  Customised adapter for Google Map's built in InfoWindow
 *
 *  This has been changed so that it will display an image as well as the title and description
 *  of the Point of Interest (POI).
 *
 */
public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;


    public CustomInfoWindowGoogleMap(Context context){
        this.context = context;
    }

    // Needed as Class implements InfoWindowAdapter
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     *  Overrides the original InfoWindow function to implement a way to populate the View
     *  This functions takes all the information stored in the Marker object to populate
     *  the Views within this Custom InfoWindow.
     *
     * @param marker
     * @return view
     */
    @Override
    public View getInfoContents(Marker marker) {

        // Creates View object based on the custom_info_window layout file
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.custom_info_window, null);

        // Instantiates the Text and Image Views within the Layout
        TextView name = view.findViewById(R.id.infoWindowName);
        TextView desc = view.findViewById(R.id.infoWindowSnippet);
        ImageView img = view.findViewById(R.id.infoWindowImage);

        // Retrieves the Image from the Marker and sets it to the ImageView
        if(marker.getTag() != null ) {
            Bitmap bm = (Bitmap) marker.getTag();
            img.setImageBitmap(bm);
        }

        // Sets TextViews
        name.setText(marker.getTitle());
        desc.setText(marker.getSnippet());

        // Returns the created View object
        return view;
    }
}
