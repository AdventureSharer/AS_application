package development.gtgo.adventuresharer.clicklisteners;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private static final String TAG = "RecyclerClickListener";

    // Interface implemented by Fragment/Activity containing Recycler
    public interface OnRecyclerClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    private final OnRecyclerClickListener mListener;
    private final GestureDetectorCompat mGestureDetector;

    public RecyclerClickListener(Context context, final RecyclerView recyclerView, OnRecyclerClickListener listener){
        mListener = listener;
        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener(){

            // Creates the click listener
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(childView != null && mListener != null) {
                    mListener.onClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
                return true;
            }

            // Create the long press listener
            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(childView != null && mListener != null) {
                    mListener.onLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        if(mGestureDetector != null) {
            boolean result = mGestureDetector.onTouchEvent(e);
            Log.d(TAG, "onInterceptTouchEvent: returned " + result );
            return result;
        } else {
            Log.d(TAG, "onInterceptTouchEvent: returned false" );
            return false;
        }
    }
}
