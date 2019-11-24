package com.aleks.planner;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerHolder extends RecyclerView.ViewHolder {
    public final View view;
    public final TextView date;
    public final TextView payment;
    public final TextView hours;
    public final TextView total;
    public final RelativeLayout l;
    public final ImageView image;


    public RecyclerHolder(@NonNull View view) {
        super(view);
        this.view = view;
        image = view.findViewById(R.id.logo);
        date = view.findViewById(R.id.date);
        payment = view.findViewById(R.id.payment);
        hours = view.findViewById(R.id.hours);
        total = view.findViewById(R.id.total);
        l = view.findViewById(R.id.relativeLayout);

    }
}
