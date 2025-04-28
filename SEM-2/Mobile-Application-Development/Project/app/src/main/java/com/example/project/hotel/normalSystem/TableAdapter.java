package com.example.project.hotel.normalSystem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {

    private Context context;
    private ArrayList<Table> tableList;
    private OnTableClickListener listener;

    public interface OnTableClickListener {
        void onTableClick(Table table);
    }

    public TableAdapter(Context context, ArrayList<Table> tableList, OnTableClickListener listener) {
        this.context = context;
        this.tableList = tableList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_table, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        Table table = tableList.get(position);

        // Set table name and number
        holder.tableName.setText(table.getTableName() + " (Table " + table.getTableNumber() + ")");

        // Set background color based on status
        if (table.getStatus().equalsIgnoreCase("available")) {
            holder.tableName.setBackgroundColor(Color.parseColor("#4CAF50")); // Green
        } else {
            holder.tableName.setBackgroundColor(Color.parseColor("#F44336")); // Red
        }

        holder.tableName.setTextColor(Color.BLACK);
        holder.tableName.setTypeface(null, Typeface.BOLD);

        holder.itemView.setOnClickListener(v -> listener.onTableClick(table));
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder {
        TextView tableName;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tableName = itemView.findViewById(R.id.tables);
        }
    }
}