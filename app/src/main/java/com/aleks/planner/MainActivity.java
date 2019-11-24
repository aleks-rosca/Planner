package com.aleks.planner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    TextView login_t_reg;
    CardView log_card;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                if (mFirebaseUser == null) {

                    Toast.makeText(MainActivity.this, "Please Log In!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        log_card = findViewById(R.id.login_card);
        login_t_reg = findViewById(R.id.login_text_reg);
        mFireBaseAuth = FirebaseAuth.getInstance();
        log_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaill = email.getText().toString();
                String pass = password.getText().toString();
                if (emaill.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in the fields!", Toast.LENGTH_SHORT).show();
                } else if (emaill.isEmpty()) {
                    email.setError("Email required!");
                    email.requestFocus();
                } else if (pass.isEmpty()) {
                    password.setError("Password required!");
                    password.requestFocus();
                } else if (!(emaill.isEmpty() && pass.isEmpty())) {
                    mFireBaseAuth.signInWithEmailAndPassword(emaill, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            } else {
                                gotoScreenIntent();
                            }
                        }
                    });


                } else {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        login_t_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(MainActivity.this, Register.class);
                startActivity(r);
            }
        });


    }



    public void gotoScreenIntent(){
        Intent screen = new Intent(this, Screen.class);
        startActivity(screen);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(authStateListener);

    }


}
