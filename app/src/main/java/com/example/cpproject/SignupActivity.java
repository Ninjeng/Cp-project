package com.example.cpproject;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {
    EditText name,email,dob,username,password;
    Button btnRegister;
     private Users users;
     DatabaseReference reference;
     FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(findViewById(R.id.nameText));
        email=(findViewById(R.id.emailText));
        dob=(findViewById(R.id.dateText));
        username=(findViewById(R.id.usernameText));
        password=(findViewById(R.id.passwordText));
        users = new Users();
        database = FirebaseDatabase.getInstance();
        reference=database.getReference().child("Users");
        btnRegister=(findViewById(R.id.registerBtn));
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser()
    {

        users.setName(name.getText().toString());
        users.setDob(dob.getText().toString());
        users.setEmail(email.getText().toString());
        users.setUsername(username.getText().toString());
        users.setPassword(password.getText().toString());
        final String user = username.getText().toString();
        if(TextUtils.isEmpty(users.getName()))
        {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(users.getDob()))
        {
            Toast.makeText(this, "Please enter date of birth", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(users.getEmail()))
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(users.getUsername()))
        {
            Toast.makeText(this, "Please enter username ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(users.getPassword()))
        {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        reference.child(users.getUsername()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user).exists())
                {
                    Toast.makeText(SignupActivity.this, "The username already exists. Please enter a new user ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else 
                {
                    reference.child(users.getUsername()).setValue(users);
                    Toast.makeText(SignupActivity.this, "User has been registered", Toast.LENGTH_SHORT).show();
                }
                    
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
