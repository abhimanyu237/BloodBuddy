package com.example.bloodbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bloodbuddy.authentication.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;



    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
//    private FirebaseAuth auth;
//
//    private FirebaseDatabase db;
//    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        db=FirebaseDatabase.getInstance();
//
//        ref=db.getReference();
//
//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        navController = Navigation.findNavController(this, R.id.fragment_layout);


//        auth=FirebaseAuth.getInstance();
//
//        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView = findViewById(R.id.navigation_view);
//
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//
//        navigationView.setNavigationItemSelectedListener(this);
//
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.option_menu, menu);
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if(toggle.onOptionsItemSelected(item))
//            return true;
//
//        if(item.getItemId()==R.id.logout) {
//            auth.signOut();
//            openLogin();
//
//        }
//
//        return true;
//
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//
//            case R.id.navigation_developer:
//                Toast.makeText(this, "Developers", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_video:
//                Toast.makeText(this, "Video", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_rate:
//                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_ebook:
//                startActivity(new Intent(MainActivity.this, EbookActivity.class));
//                break;
//            case R.id.navigation_theme:
//                Toast.makeText(this, "Theme", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_website:
//                Toast.makeText(this, "Websites", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_share:
//                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//
//        return true;
//    }

//    @Override
//    public void onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START))
//        {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else
//            super.onBackPressed();
//    }


//    private void openLogin() {
//
//        startActivity(new Intent(this, LoginActivity.class));
//        finish();
//
//
//    }

}