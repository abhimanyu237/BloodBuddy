package com.example.bloodbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bloodbuddy.authentication.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FirebaseAuth auth=FirebaseAuth.getInstance();
//
//    private FirebaseDatabase db;
//    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      db=FirebaseDatabase.getInstance();
//
//        ref=db.getReference();
//
//
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment_layout);


//        auth=FirebaseAuth.getInstance();
//
        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView = findViewById(R.id.navigation_view);
//
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
////
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
////
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
////
//        navigationView.setNavigationItemSelectedListener(this);
////
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
    @Override
    protected void onResume() {
        super.onResume();
        closeDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
            return true;

        if(item.getItemId()==R.id.logout) {
            auth.signOut();
            openLogin();

        }

        return true;
    }

    public void onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.navigation_profile:
                startActivity(new Intent(MainActivity.this, Navigation_Profile.class));
                break;
            case R.id.navigation_request:
                startActivity(new Intent(MainActivity.this, Navigation_Request_History.class));
                break;
            case R.id.navigation_rate_us:
                startActivity(new Intent(MainActivity.this, Navigation_RateUs.class));
                break;
            case R.id.navigation_contact_us:
                startActivity(new Intent(MainActivity.this, Navigation_ContactUs.class));
                break;
            case R.id.navigation_about_us:
                startActivity(new Intent(MainActivity.this, Navigation_AboutUs.class));
                break;
            case R.id.navigation_help:
                startActivity(new Intent(MainActivity.this, Navigation_Help.class));
                break;

        }
    }
    public void OnclickLogout(MenuItem item){
       // Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to Logout");
        builder.setTitle("Bloodbuddy");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            auth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    private void openLogin() {

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}