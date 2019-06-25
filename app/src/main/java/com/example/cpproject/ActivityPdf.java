package com.example.cpproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;

public class ActivityPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        ScrollView scrollView=(ScrollView)findViewById(R.id.scrollbar);

        Intent i =this.getIntent();
        String path= i.getExtras().getString("path");
        File file = new File(path);
        
        if(file.canRead())
        {
            pdfView.fromFile(file).defaultPage(1).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(ActivityPdf.this, "File", Toast.LENGTH_SHORT).show();   
                }
            }).load();
        }
    }
}
