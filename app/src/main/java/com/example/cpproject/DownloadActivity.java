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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {
    ImageButton library,download,person;
    public static String USERNAME ="com.example.cpproject.USERNAME";
    private String uname;
    ListView lv_pdf;
    public static ArrayList<File> fileList = new ArrayList<>();
    DownloadPdfAdapter obj_adapter;
    public static int REQUEST_PERMISSION=1;
    boolean bool_permission;
    File dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        library= findViewById(R.id.DlibraryBtn);
        download= findViewById(R.id.DdownloadBtn);
        person= findViewById(R.id.DpersonBtn);
        lv_pdf = findViewById(R.id.downloadListView);
        dir = new File(Environment.getExternalStorageDirectory().toString());
        permission();
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
    }

    private void permission() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if((ActivityCompat.shouldShowRequestPermissionRationale(DownloadActivity.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE))){

            }
            else
            {
                ActivityCompat.requestPermissions(DownloadActivity.this ,new String[]
                                {android.Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION);
            }
        }
        else
        {
            bool_permission = true;
            getfile(dir);
            obj_adapter=new DownloadPdfAdapter(getApplicationContext(),fileList);
            lv_pdf.setAdapter(obj_adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                bool_permission = true;
                getfile(dir);
                obj_adapter=new DownloadPdfAdapter(getApplicationContext(),fileList);
                lv_pdf.setAdapter(obj_adapter);
            }
            else
            {
                Toast.makeText(this, "Please allow permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<File> getfile(File dir) {
        File listFile[]=dir.listFiles();
        if(listFile!=null && listFile.length>0){
            for(int i =0; i<listFile.length;i++)
            {
                if(listFile[i].isDirectory())
                {
                    getfile(listFile[i]);
                }
                else
                {
                    boolean boolPDF = false;
                    if(listFile[i].getName().endsWith(".pdf")){
                        for (int j =0;j<fileList.size(); j++)
                        {
                            if (fileList.get(j).getName().equals(listFile[i].getName()))
                            {
                                boolPDF=true;
                            }
                            else
                            {

                            }
                        }
                        if(boolPDF)
                        {
                            boolPDF=false;
                        }
                        else
                        {
                            fileList.add(listFile[i]);
                        }
                    }
                }
            }
        }
        return fileList;
    }
}
