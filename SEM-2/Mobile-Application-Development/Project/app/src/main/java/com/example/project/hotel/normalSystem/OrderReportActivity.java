package com.example.project.hotel.normalSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.hotel.LoginSignupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.*;

public class OrderReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter adapter;
    List<Order> orderList = new ArrayList<>();
    DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report);

        recyclerView = findViewById(R.id.orderRecyclerView);
        adapter = new OrderAdapter(this, orderList);
        recyclerView.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ordersRef = FirebaseDatabase.getInstance().getReference("Orders");
        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderList.clear();
                for (DataSnapshot orderSnap : snapshot.getChildren()) {
                    Order order = orderSnap.getValue(Order.class);
                    if (order != null && order.getUserEmail() != null &&
                            order.getUserEmail().equalsIgnoreCase(user.getEmail())) {
                        order.setId(orderSnap.getKey());
                        orderList.add(order);
                    }
                }
                Collections.reverse(orderList);
                adapter.notifyDataSetChanged();

                if (orderList.isEmpty()) {
                    Toast.makeText(OrderReportActivity.this, "No orders found for this user", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderReportActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
            }
        });
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