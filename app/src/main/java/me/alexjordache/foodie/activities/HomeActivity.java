package me.alexjordache.foodie.activities;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.alexjordache.foodie.R;
import me.alexjordache.foodie.utils.LocationProvider;

public class HomeActivity extends AppCompatActivity implements LocationProvider.LocationCallback {

    private static final String TAG = "HomeActivity";
    private LocationProvider mLocationProvider;
    private TextView mLatitudeText;
    private TextView mLongitudeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //UI Elements
        mLatitudeText = (TextView) findViewById(R.id.latitude_text);
        mLongitudeText = (TextView) findViewById(R.id.longitude_text);

        Button findRestaurantsButton = (Button) findViewById(R.id.find_restaurants_btn);

        mLocationProvider = new LocationProvider(this, this);

        //Initialize Listeners
        findRestaurantsButton.setOnClickListener(HandleFindRestaurantsClick);

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
            Intent intent = new Intent(HomeActivity.this, RestaurantsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.next_slide_in_left, R.anim.current_slide_out_left);
        }
    };

    @Override
    public void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
    }
}
