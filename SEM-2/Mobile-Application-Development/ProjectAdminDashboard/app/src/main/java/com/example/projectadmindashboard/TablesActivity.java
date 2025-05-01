package com.example.projectadmindashboard;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TablesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Table> tableList;
    private TableAdapter tableAdapter;
    private DatabaseReference tableRef;
    private String selectedTableKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

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
                        tableList.add(table);
                    }
                }
                setupAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TablesActivity.this, "Failed to load tables: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAdapter() {
        tableAdapter = new TableAdapter(this, tableList, table -> {
            if (table.getStatus().equalsIgnoreCase("occupied")) {
                findTableKeyAndShowDialog(table);
            } else {
                Toast.makeText(this, "Table is already available.", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(tableAdapter);
    }

    private void findTableKeyAndShowDialog(Table clickedTable) {
        tableRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot tableSnapshot : snapshot.getChildren()) {
                    String dbTableName = tableSnapshot.child("tableName").getValue(String.class);
                    if (dbTableName != null && dbTableName.equalsIgnoreCase(clickedTable.getTableName())) {
                        selectedTableKey = tableSnapshot.getKey(); // e.g., "Table1"
                        showStatusChangeDialog(clickedTable, selectedTableKey);
                        return;
                    }
                }
                Toast.makeText(TablesActivity.this, "Table key not found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TablesActivity.this, "Failed to find table key: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showStatusChangeDialog(Table table, String tableKey) {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Update Table")
                .setMessage("Mark this table as Available and set the associated order as Served?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // 1. Update table status
                    tableRef.child(tableKey).child("status").setValue("available")
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(this, "Table marked as available", Toast.LENGTH_SHORT).show();
                                // 2. Update corresponding order to 'Served'
                                updateOrderStatusToServed(table.getTableName());
                            })
                            .addOnFailureListener(e -> Toast.makeText(this, "Failed to update table: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void updateOrderStatusToServed(String tableName) {
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("Orders");

        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot latestOrderSnapshot = null;
                long latestTimestamp = Long.MIN_VALUE;

                for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                    String orderTableName = orderSnapshot.child("tableName").getValue(String.class);
                    String status = orderSnapshot.child("status").getValue(String.class);
                    Long timestamp = orderSnapshot.child("timestamp").getValue(Long.class);

                    if (orderTableName != null && orderTableName.equalsIgnoreCase(tableName)
                            && status != null && status.equalsIgnoreCase("pending")
                            && timestamp != null && timestamp > latestTimestamp) {
                        latestTimestamp = timestamp;
                        latestOrderSnapshot = orderSnapshot;
                    }
                }

                if (latestOrderSnapshot != null) {
                    latestOrderSnapshot.getRef().child("status").setValue("Served");
                    Toast.makeText(TablesActivity.this, "Latest order marked as Served", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TablesActivity.this, "No pending order found for this table", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TablesActivity.this, "Failed to update order: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}