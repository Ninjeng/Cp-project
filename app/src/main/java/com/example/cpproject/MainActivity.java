package com.example.cpproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    public static String USERNAME ="com.example.cpproject.USERNAME";
    Button btnLogin;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.userText);
        password = findViewById(R.id.passText);
        btnLogin= findViewById(R.id.loginBtn);
        reference= FirebaseDatabase.getInstance().getReference().child("Users");
    }
    public void signup_Click(View v)
    {
        if(v.getId()==R.id.signupBtn)
        {
            Intent i = new Intent(this ,SignupActivity.class);
            startActivity(i);
        }
    }
    public void login_Click(View view)
    {

        final String user= username.getText().toString();
        final String pass = password.getText().toString();
        reference.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child(user).exists()) {

                    Users users = dataSnapshot.getValue(Users.class);
                    if (pass.equals(users.getPassword())) {
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent dashboard = new Intent(MainActivity.this, DashboardActivity.class);
                        dashboard.putExtra(USERNAME,user);
                        startActivity(dashboard);
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();

                    }
                }
                else 
                {
                    Toast.makeText(MainActivity.this, "User is doesnot exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
