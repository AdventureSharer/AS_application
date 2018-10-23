package development.gtgo.adventuresharer;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import development.gtgo.adventuresharer.adapters.JourneyRecyclerAdapter;
import development.gtgo.adventuresharer.clicklisteners.RecyclerClickListener;
import development.gtgo.adventuresharer.datamodels.HikingRoute;

public class AllJourneysActivity extends AppCompatActivity implements RecyclerClickListener.OnRecyclerClickListener {

    private static final String TAG = "AllJourneysActivity";

    // FireBase Database
    private DatabaseReference mHikingRef;
    private ValueEventListener mValueEventListener;
    private Query mQuery;

    // FireBase Auth
    private String userID;

    // RecyclerView Objects
    protected RecyclerView recyclerView;
    protected JourneyRecyclerAdapter recyclerAdapter;
    protected RecyclerView.LayoutManager layoutManager;


    // Lists of Hiking Routes
    protected List<HikingRoute> mRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_journeys);

        // FireBase Auth
        FirebaseAuth mFireBaseAuth = FirebaseAuth.getInstance();
        if(mFireBaseAuth.getCurrentUser() != null) {
            userID = mFireBaseAuth.getCurrentUser().getUid();
        }

        // FireBase Database
        mHikingRef = FirebaseDatabase.getInstance().getReference().child("HikingRoutes");

        // Route List
        mRoutes = new ArrayList<>();

        // Links RecyclerView
        recyclerView = findViewById(R.id.recycler_all_journeys);
        // Sets RecyclerView Layout
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adds the RecyclerClickListener
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, recyclerView, this));

        // Creates recyclerAdapter for content
        recyclerAdapter = new JourneyRecyclerAdapter(mRoutes);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // attaches listener whenever the activity is in focus
        attachDatabaseReadListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // detaches database whenever activity is out of focus
        detachDatabaseReadListener();
    }

    @Override
    public void onClick(View view, int position) {

        String routeID = mRoutes.get(position).getRouteID();

        Intent intent = new Intent(AllJourneysActivity.this, MapsActivity.class);
        intent.putExtra("routeID", routeID);
        setResult(Activity.RESULT_OK);
        startActivity(intent);


    }

    @Override
    public void onLongClick(View view, int position) {
        // Planned for an option menu to appear on long press
    }

    // Database listener retrieves offers from FireBase and sets data to RecyclerView recyclerAdapter
    private void attachDatabaseReadListener() {
        mQuery = mHikingRef;

        if(mValueEventListener == null) {
            mValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.i(TAG, "onDataChange started");
                    if(dataSnapshot.exists()) {
                        recyclerAdapter.notifyDataSetChanged();
                        Log.i(TAG, "onDataChange: data change detected");
                        mRoutes.clear();
                        for(DataSnapshot routeSnapshot : dataSnapshot.getChildren()) {
                            HikingRoute route = routeSnapshot.getValue(HikingRoute.class);
                            Log.i(TAG, "UserID: " + userID);

                            mRoutes.add(route);
                        }
                        recyclerAdapter.setRoutes(mRoutes);
                    } else {
                        // Debugging
                        Log.i(TAG, "dataSnapshot doesn't exists");
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            };
        } else {
            // Debugging
            Log.i(TAG, "mValueListener is not null");
        }
        // Adds this entire listening process to the Query
        Log.i(TAG, "addValueEventListener added to mQuery");
        mQuery.addValueEventListener(mValueEventListener);
    }

    /**
     *  Detaches the database from the activity
     */
    private void detachDatabaseReadListener() {
        if(mValueEventListener != null) {
            Log.i(TAG, "DETACHED");
            mQuery.removeEventListener(mValueEventListener);
            mValueEventListener = null;
        }
    }
}
