package com.example.cpproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {
    ImageButton library,download,person;
    public static String USERNAME ="com.example.cpproject.USERNAME";
    private String uname;
    TextView usernameTextview;
    Button addButoon,signoutButton,helpCenterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        library= findViewById(R.id.PlibraryBtn);
        download= findViewById(R.id.PdownloadBtn);
        person= findViewById(R.id.PpersonBtn);
        addButoon =(Button)findViewById(R.id.AddbookBtn);
        signoutButton=findViewById(R.id.signOutBtn);
        helpCenterButton=findViewById(R.id.helpCenterBtn);
        usernameTextview=findViewById(R.id.namiDText);
        Intent i = getIntent();
        uname =i.getStringExtra(DashboardActivity.USERNAME);
        usernameTextview.setText(uname);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BookselfActivity.class);
                intent.putExtra(USERNAME,uname);
                startActivity(intent);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                intent.putExtra(USERNAME,uname);
                startActivity(intent);
            }
        });
        addButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddBookActivity.class);
                intent.putExtra(USERNAME,uname);
                startActivity(intent);
            }
        });
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        helpCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HelpCenter.class);
                intent.putExtra(USERNAME,uname);
                startActivity(intent);
            }
        });
    }
}
