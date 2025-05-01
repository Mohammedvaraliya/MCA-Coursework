package com.example.projectadmindashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    List<Order> orderList;

    Context context;
    DatabaseReference tableRef = FirebaseDatabase.getInstance().getReference("Tables");
    DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
    boolean isAdmin = false;

    public OrderAdapter(Context context, List<Order> orders, boolean isAdmin) {
        this.context = context;
        this.orderList = orders;
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        String dateTime = sdf.format(new Date(order.getTimestamp()));

        // Stylish "Order Date"
        String styledDate = "<b><font color='#333333'>Ordered on:</font></b> <font color='#555555'>" + dateTime + "</font>";
        holder.orderDate.setText(Html.fromHtml(styledDate, Html.FROM_HTML_MODE_COMPACT));

        if (isAdmin && order.getUserEmail() != null) {
            holder.tableName.setText("Table: " + order.getTableName() + "\nUser: " + order.getUserEmail());
        } else {
            holder.tableName.setText("Table: " + order.getTableName());
        }

        // Styled items list
        StringBuilder itemDesc = new StringBuilder();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                itemDesc.append("🍽️  <b>").append(item.getName()).append("</b>")
                        .append(" <small>(x").append(item.getQuantity()).append(")</small>")
                        .append("<br><font color='#4299E1'>₹").append(item.getTotalPrice()).append("</font>")
                        .append("<br><br>");
            }
        }
        holder.orderItems.setText(Html.fromHtml("<ul>" + itemDesc.toString() + "</ul>", Html.FROM_HTML_MODE_COMPACT));

        // Styled total
        String totalText = "<b><font color='#000000'>Total:</font></b> ₹" + order.getTotalAmount();
        holder.orderTotal.setText(Html.fromHtml(totalText, Html.FROM_HTML_MODE_COMPACT));

        // Status tag
        String status = order.getStatus();
        holder.orderStatus.setText(status);

        switch (status.toLowerCase()) {
            case "pending":
                holder.orderStatus.setBackgroundResource(R.drawable.bg_status_pending);
                holder.orderCard.setCardBackgroundColor(context.getColor(R.color.status_pending_bg));
                break;
            case "in progress":
                holder.orderStatus.setBackgroundResource(R.drawable.bg_status_in_progress);
                holder.orderCard.setCardBackgroundColor(context.getColor(R.color.status_in_progress_bg));
                break;
            case "served":
                holder.orderStatus.setBackgroundResource(R.drawable.bg_status_served);
                holder.orderCard.setCardBackgroundColor(context.getColor(R.color.status_served_bg));
                break;
            case "cancelled":
                holder.orderStatus.setBackgroundResource(R.drawable.bg_status_cancelled);
                holder.orderCard.setCardBackgroundColor(context.getColor(R.color.status_cancelled_bg));
                break;
        }

        holder.itemView.setOnClickListener(v -> {
            if (!isAdmin) return;

            String[] statusOptions = {"Pending", "In Progress", "Served", "Cancelled"};

            new AlertDialog.Builder(context)
                    .setTitle("Update Order Status")
                    .setItems(statusOptions, (dialog, which) -> {
                        String selectedStatus = statusOptions[which];
                        String orderId = order.getId();
                        String tableName = order.getTableName();

                        // Update order status in Firebase
                        orderRef.child(orderId).child("status").setValue(selectedStatus);

                        // Logic for updating associated table status
                        if (selectedStatus.equalsIgnoreCase("Cancelled") ||
                                selectedStatus.equalsIgnoreCase("Served")) {

                            // Make table available
                            tableRef.orderByChild("tableName")
                                    .equalTo(tableName)
                                    .get()
                                    .addOnSuccessListener(snapshot -> {
                                        if (snapshot.exists()) {
                                            for (DataSnapshot tableSnap : snapshot.getChildren()) {
                                                tableSnap.getRef().child("status").setValue("available");
                                            }
                                        }
                                    });
                        } else if (order.getStatus().equalsIgnoreCase("Cancelled") ||
                                order.getStatus().equalsIgnoreCase("Served")) {

                            // If changing from Cancelled/Served back to other state, mark table as occupied
                            tableRef.orderByChild("tableName")
                                    .equalTo(tableName)
                                    .get()
                                    .addOnSuccessListener(snapshot -> {
                                        if (snapshot.exists()) {
                                            for (DataSnapshot tableSnap : snapshot.getChildren()) {
                                                tableSnap.getRef().child("status").setValue("occupied");
                                            }
                                        }
                                    });
                        }

                        // Update UI
                        order.setStatus(selectedStatus);
                        notifyItemChanged(holder.getAdapterPosition());
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });



    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDate, orderItems, orderTotal, orderStatus, tableName;
        MaterialCardView orderCard;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.orderDate);
            tableName = itemView.findViewById(R.id.table_name);
            orderItems = itemView.findViewById(R.id.orderItems);
            orderTotal = itemView.findViewById(R.id.orderTotal);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderCard = itemView.findViewById(R.id.orderCard);
        }
    }
}
