package me.alexjordache.foodie.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.alexjordache.foodie.R;

/**
 * Created by Alex Jordache on 2017-05-11.
 */

public class RestaurantsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.previous_slide_in_right, R.anim.current_slide_in_right);
    }
}
