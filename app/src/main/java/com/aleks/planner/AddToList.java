package com.aleks.planner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToList extends AppCompatActivity {
    private Workday day;
    private TextView date;
    private TextView hours;
    private TextView money;
    private CardView add_t;
    private CardView cancel;
    private DatabaseReference databaseUtils;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);

        date = findViewById(R.id.add_date);
        hours = findViewById(R.id.add_hours);
        money = findViewById(R.id.add_money);
        add_t = findViewById(R.id.add_card);
        cancel = findViewById(R.id.cancel_card);
        database = FirebaseDatabase.getInstance();
        databaseUtils = database.getReference("work_days");

        add_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDay(v);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainScreen();
            }
        });
    }



    public void addDay(View v){
        if(!TextUtils.isEmpty(date.getText().toString())){
            String d = date.getText().toString();
            double h = Double.valueOf(hours.getText().toString());
            double m = Double.valueOf(money.getText().toString());
            String id = databaseUtils.push().getKey();

            day = new Workday(d, h, m);
            databaseUtils.child(id).setValue(day);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            MainScreen();

        } else {
            Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();
        }

    }

    public void MainScreen(){
        Intent h = new Intent(this, Screen.class);
        startActivity(h);

    }
}
