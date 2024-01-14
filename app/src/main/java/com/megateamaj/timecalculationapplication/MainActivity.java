package com.megateamaj.timecalculationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.megateamaj.timecalculationapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    TimeFragment timeFragment = new TimeFragment();
    HoursFragment hoursFragment = new HoursFragment();
    DateFragment dateFragment = new DateFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.time);

        // Display the initial fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, timeFragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.time) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, timeFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.hours) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, hoursFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.date) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, dateFragment)
                    .commit();
            return true;
        }

        return false;
    }
}
