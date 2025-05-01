package com.example.projectadmindashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.*;

public class OrderReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter adapter;
    List<Order> orderList = new ArrayList<>();
    DatabaseReference ordersRef;

    private final String ADMIN_EMAIL = "varacompany12@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null || !ADMIN_EMAIL.equalsIgnoreCase(user.getEmail())) {
            Toast.makeText(this, "Access Denied: Not an admin", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        recyclerView = findViewById(R.id.orderRecyclerView);
        adapter = new OrderAdapter(this, orderList, true);  // Pass true for isAdmin
        recyclerView.setAdapter(adapter);

        ordersRef = FirebaseDatabase.getInstance().getReference("Orders");
        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderList.clear();
                for (DataSnapshot orderSnap : snapshot.getChildren()) {
                    Order order = orderSnap.getValue(Order.class);
                    if (order != null) {
                        order.setId(orderSnap.getKey());
                        orderList.add(order);
                    }
                }
                Collections.reverse(orderList);
                adapter.notifyDataSetChanged();

                if (orderList.isEmpty()) {
                    Toast.makeText(OrderReportActivity.this, "No orders found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderReportActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
            }
        });
    }
}