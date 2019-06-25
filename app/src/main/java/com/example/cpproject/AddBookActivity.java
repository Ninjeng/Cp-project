package com.example.cpproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddBookActivity extends AppCompatActivity {
    public static String USERNAME ="com.example.cpproject.USERNAME";
    private String uname;
    EditText title,author,year,publisher;
    Button uploadpdf;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        title=findViewById(R.id.titletxt);
        author=findViewById(R.id.authortxt);
        year=findViewById(R.id.yeartxt);
        publisher=findViewById(R.id.publishertxt);
        uploadpdf=findViewById(R.id.uploadBtn);
        Intent indent =getIntent();
        uname =indent.getStringExtra(PersonActivity.USERNAME);
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference=FirebaseDatabase.getInstance().getReference("Books").child("ninzen");

        uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPdf();
            }
        });
    }

    private void selectPdf() {
    Intent intent = new Intent();
    intent.setType("application/pdf");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent,"Select pdf files"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            uploadPDFFile(data.getData());
        }
    }

    private void uploadPDFFile(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading....");
        progressDialog.show();
        StorageReference reference= storageReference.child("Books/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while(!uri.isComplete());
                Uri url=uri.getResult();
                Books books = new Books(title.getText().toString(),author.getText().toString(),year.getText().toString(),publisher.getText().toString(),url.toString());
                databaseReference.child(books.getTitle()).setValue(books);
                Toast.makeText(AddBookActivity.this, "Book has been added", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
//                Intent intent = new Intent(AddBookActivity.this,DashboardActivity.class);
//                intent.putExtra(USERNAME,uname);
//                startActivity(intent);
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress =(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded"+(int)progress+"%");
            }
        });
    }
}
