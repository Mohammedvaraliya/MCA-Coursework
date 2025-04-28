package com.example.project.hotel.normalSystem;

import android.view.View;
import android.widget.AdapterView;

public abstract class SimpleItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public abstract void onItemSelected(String selectedItem);

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onItemSelected(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
