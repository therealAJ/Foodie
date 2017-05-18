package me.alexjordache.foodie.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.alexjordache.foodie.R;

/**
 * Created by Alex Jordache on 2017-05-11.
 */

public class RestaurantsActivity extends AppCompatActivity {
    private double mLatitude;
    private double mLongitude;
    private TextView mLatitudeText;
    private TextView mLongitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        //UI Elements
        mLatitudeText = (TextView) findViewById(R.id.latitude_text);
        mLongitudeText = (TextView) findViewById(R.id.longitude_text);

        mLatitudeText.setText(String.valueOf(getIntent().getExtras().getDouble(HomeActivity.CURRENT_LATITUDE)));
        mLongitudeText.setText(String.valueOf(getIntent().getExtras().getDouble(HomeActivity.CURRENT_LONGITUDE)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.previous_slide_in_right, R.anim.current_slide_in_right);
    }
}
