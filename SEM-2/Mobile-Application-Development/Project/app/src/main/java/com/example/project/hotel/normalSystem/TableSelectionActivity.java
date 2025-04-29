package com.example.project.hotel.normalSystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.hotel.LoginSignupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TableSelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Table> tableList;
    private TableAdapter tableAdapter;
    private DatabaseReference tableRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_selection);

        recyclerView = findViewById(R.id.tableRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        tableList = new ArrayList<>();

        tableRef = FirebaseDatabase.getInstance().getReference("Tables");
        fetchTablesFromFirebase();
    }

    private void fetchTablesFromFirebase() {
        tableRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tableList.clear();
                for (DataSnapshot tableSnapshot : snapshot.getChildren()) {
                    Table table = tableSnapshot.getValue(Table.class);
                    if (table != null) {
                        Log.d("TableDebug", "Table: " + table.getTableName() +
                                ", Status: " + table.getStatus() +
                                ", Number: " + table.getTableNumber());
                        tableList.add(table);
                    }
                }
                setupAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TableSelectionActivity.this, "Failed to load tables: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAdapter() {
        tableAdapter = new TableAdapter(this, tableList, table -> {
            if (table.getStatus().equalsIgnoreCase("available")) {
                // Go to next screen MenuSelectionActivity
                Intent intent = new Intent(TableSelectionActivity.this, MenuSelectionActivity.class);
                intent.putExtra("tableName", table.getTableName());
                intent.putExtra("tableNumber", table.getTableNumber());
                startActivity(intent);
            } else {
                Toast.makeText(this, "This table is occupied! Choose another.", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(tableAdapter);
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