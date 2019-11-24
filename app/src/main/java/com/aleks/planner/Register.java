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



public class Register extends AppCompatActivity {

    EditText email, password;
    TextView reg;
    CardView reg_c;
    TextView LoginTextView;
    FirebaseAuth mFireBaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFireBaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        reg_c = findViewById(R.id.reg_card);
        LoginTextView = findViewById(R.id.reg_text_login);


        reg_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaill = email.getText().toString();
                String pass = password.getText().toString();
                if (emaill.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill in the fields!", Toast.LENGTH_SHORT).show();
                } else if (emaill.isEmpty()) {
                    email.setError("Email required!");
                    email.requestFocus();
                } else if (pass.isEmpty()) {
                    password.setError("Password required!");
                    password.requestFocus();
                } else if (!(emaill.isEmpty() && pass.isEmpty())) {
                    mFireBaseAuth.createUserWithEmailAndPassword(emaill, pass).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "Something went wrong, check the details and try again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Register.this, Screen.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(Register.this, MainActivity.class);
                startActivity(l);
            }
        });

    }
}
