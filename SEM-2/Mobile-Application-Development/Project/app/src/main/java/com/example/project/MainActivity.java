package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project.databinding.ActivityMainBinding;
import com.example.project.hotel.LoginSignupActivity;
import com.example.project.hotel.normalSystem.OrderReportActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    protected ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.setContentView(binding.getRoot());

        binding.appBarMain.getStartedButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginSignupActivity.class);
            startActivity(intent);
        });

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                binding.appBarMain.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_session2) {
                startActivity(new Intent(this, com.example.project.sessions.Session2MultiActivityApp.Session2Activity1.class));
            } else if (id == R.id.nav_session3) {
                startActivity(new Intent(this, com.example.project.sessions.Session3AdvanceFeaturesApp.Session3Activity1.class));
            } else if (id == R.id.nav_session4) {
                startActivity(new Intent(this, com.example.project.sessions.Session4DatabaseCRUDApp.Session4Activity1.class));
            } else if (id == R.id.nav_session5) {
                startActivity(new Intent(this, com.example.project.sessions.Session5MenusToolbar.Session5Activity1.class));
            } else if (id == R.id.nav_session6) {
                startActivity(new Intent(this, com.example.project.sessions.Session6UIControls.Session6Activity1.class));
            } else if (id == R.id.nav_session7) {
                startActivity(new Intent(this, com.example.project.sessions.Session7LanguageLocalization.Session7Activity1.class));
            } else if (id == R.id.nav_session8) {
                startActivity(new Intent(this, com.example.project.sessions.Session8FirebaseDatabase.Session8Activity1.class));
            }

            drawer.closeDrawers();
            return true;
        });
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, binding.appBarMain.getRoot(), true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginSignupActivity.class));
            finish();
            return true;
        } else if (id == R.id.action_show_orders) {
            startActivity(new Intent(this, OrderReportActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}