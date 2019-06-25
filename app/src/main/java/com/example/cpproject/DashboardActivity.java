package com.example.cpproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
  ImageButton library,download,person;
  private static String TAG ="DashboardActivity";
    public static String USERNAME ="com.example.cpproject.USERNAME";
    private String uname;
    public static ListView listView;
    DatabaseReference databaseReference;
    public static List<Books> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        library = findViewById(R.id.libraryBtn);
        download = findViewById(R.id.downloadBtn);
        person = findViewById(R.id.personBtn);
        final Intent indent = getIntent();
        uname = indent.getStringExtra(MainActivity.USERNAME);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookselfActivity.class);
                intent.putExtra(USERNAME, uname);
                startActivity(intent);
            }
        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
                intent.putExtra(USERNAME, uname);
                startActivity(intent);
            }
        });
        bookList = new ArrayList<>();
        listView = findViewById(R.id.bookListview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Books/"+"ninzen");

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(),ViewPdfFiles.class);
//                intent.putExtra("position",position);
//                startActivity(intent) ;
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Books books = bookList.get(position);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setType("application/pdf");
////                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
////                startActivity(intent);
                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(books.getUrl()));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookList.clear();
                for(DataSnapshot artistSnapshot:dataSnapshot.getChildren()){
                    Books books = artistSnapshot.getValue(Books.class);
                    bookList.add(books);
                }
                BooksList adapter1 = new BooksList(DashboardActivity.this, bookList);
                listView.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
