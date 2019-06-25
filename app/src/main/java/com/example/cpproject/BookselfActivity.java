package com.example.cpproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class BookselfActivity extends AppCompatActivity {
    Button loadBtn;
    ImageButton library,download,person;
    public static String USERNAME ="com.example.cpproject.USERNAME";
    private String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookself);
        final ListView lv = findViewById(R.id.listView_pdf);
//        final Button btn = findViewById(R.id.deleteBtn);
        library= findViewById(R.id.BlibraryBtn);
        download= findViewById(R.id.BdownloadBtn);
        person= findViewById(R.id.BpersonBtn);
        loadBtn=findViewById(R.id.Loadbtn);
        Intent indent = getIntent();
        uname =indent.getStringExtra(DashboardActivity.USERNAME);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                intent.putExtra(USERNAME,uname);
                startActivity(intent);
            }
        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PersonActivity.class);
                intent.putExtra(USERNAME,uname);
                startActivity(intent);
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(new BookselfAdapter(BookselfActivity.this,getPDF()));
            }
        });
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = getIntent();
//                String path=pd
//                File file = new File(getFilesDir(),path);
//                if(file.exists())
//                {
//                    deleteFile(path);
//                    Toast.makeText(BookselfActivity.this, "File has been deleted", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(BookselfActivity.this, "File not Deleted", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

    private ArrayList<PdfDoc> getPDF() {
        ArrayList<PdfDoc> pdfDocs = new ArrayList<>();
        File downloadFolder =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        PdfDoc pdfDoc;
        if(downloadFolder.exists())
        {
            File[] files = downloadFolder.listFiles();
            for(int i =0; i<files.length;i++)
            {
                 File file = files[i];
                 if(file.getPath().endsWith(".pdf"))
                 {
                     pdfDoc=new PdfDoc();
                     pdfDoc.setName(file.getName());
                     pdfDoc.setPath(file.getAbsolutePath());
                     pdfDocs.add(pdfDoc);
                 }
            }
        }
        return pdfDocs;

    }

}
