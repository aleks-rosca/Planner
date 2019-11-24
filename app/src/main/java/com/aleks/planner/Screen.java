package com.aleks.planner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Screen extends AppCompatActivity {

    private DatabaseReference dataref;
    private RecyclerView listAll;
    private List<Workday> list;
    private Adapter adapter;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authState;
    FloatingActionButton addTo;
    FloatingActionButton logOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        list = new ArrayList<>();
        dataref = FirebaseDatabase.getInstance().getReference("work_days");
        listAll = findViewById(R.id.day_list);
        addTo = findViewById(R.id.add);
        logOut = findViewById(R.id.logout_b);
        RecyclerView.LayoutManager m =new LinearLayoutManager(this);
        this.listAll.setLayoutManager(m);


        addTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Screen.this, AddToList.class);
                startActivity(a);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.getInstance().signOut();
                Intent l = new Intent(Screen.this, MainActivity.class);
                startActivity(l);
            }
        });

    }
    protected void onStart(){
        super.onStart();

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot workdaySnapshot: dataSnapshot.getChildren()){
                    Workday w = workdaySnapshot.getValue(Workday.class);
                    list.add(w);

                    adapter = new Adapter( list);
                    listAll.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}

