package com.aleks.planner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<com.aleks.planner.RecyclerHolder> {

    private List<Workday> days;
    public Adapter(List<Workday> days)
    {
        this.days = days;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Workday day = days.get(position);
        holder.date.setText(day.getDate());
        holder.hours.setText(String.valueOf(day.getHours()));
        holder.payment.setText(String.valueOf(day.getMoney()));
        holder.total.setText(String.valueOf(day.getTotal()));

    }

    @Override
    public int getItemCount() {
        return days.size();
    }
}
