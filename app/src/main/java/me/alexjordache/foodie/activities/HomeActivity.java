package me.alexjordache.foodie.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.alexjordache.foodie.R;
import me.alexjordache.foodie.utils.LocationProvider;

public class HomeActivity extends AppCompatActivity implements LocationProvider.LocationCallback {

    private static final String TAG = "HomeActivity";
    public static final String CURRENT_LATITUDE = "CURRENT_LATITUDE";
    public static final String CURRENT_LONGITUDE = "CURRENT_LONGITUDE";

    private LocationProvider mLocationProvider;
    private double mLatitude;
    private double mLongitude;
    private Button mFindRestaurantsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLocationProvider = new LocationProvider(this, this);
        mFindRestaurantsButton = (Button) findViewById(R.id.find_restaurants_btn);

        //Initialize Listeners
        mFindRestaurantsButton.setOnClickListener(HandleFindRestaurantsClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationProvider.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationProvider.disconnect();
    }



    // Click Listeners

    View.OnClickListener HandleFindRestaurantsClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.find_restaurants_btn) {
                Intent i = new Intent(HomeActivity.this, RestaurantsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble(CURRENT_LATITUDE,mLatitude);
                bundle.putDouble(CURRENT_LONGITUDE,mLongitude);
                i.putExtras(bundle);
                startActivity(i);
                overridePendingTransition(R.anim.next_slide_in_left, R.anim.current_slide_out_left);
                //finish();

            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 111: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted

                } else {
                    // permission was denied
                }
                return;
            }
        }
    }

    @Override
    public void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
        setLatitude(location.getLatitude());
        setLongitude(location.getLongitude());
    }

    //---------------------------------
    //     Getters and Setters
    //---------------------------------

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }
}
