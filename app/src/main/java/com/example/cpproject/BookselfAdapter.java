package com.example.cpproject;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookselfAdapter extends BaseAdapter {

    Context context;
    ArrayList<PdfDoc> pdfDocs;


    public BookselfAdapter(Context context,  ArrayList<PdfDoc> pdfDocs) {
        this.context = context;
        this.pdfDocs = pdfDocs;
    }


    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int position) {
        return pdfDocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.bookself_adapter,parent,false);
        }
        final  PdfDoc pdfDoc = (PdfDoc) this.getItem(position);
        TextView nameText = (TextView)convertView.findViewById(R.id.tv_name);
        ImageView img = (ImageView)convertView.findViewById(R.id.iv_image);
//        Button btn = (Button)convertView.findViewById(R.id.deleteBtn);
        nameText .setText(pdfDoc.getName());
        img.setImageResource(R.drawable.pdf);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String deletepath =pdfDoc.getPath();
//                Intent i = new Intent(context,BookselfActivity.class);
//                i.putExtra("deletePath",deletepath);
//                context.startActivity(i);
//            }
//        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPdfView(pdfDoc.getPath());
            }
        });
        return convertView;
    }

    private void openPdfView(String path) {
        Intent i = new Intent(context,ActivityPdf.class);
        i.putExtra("path",path);
        context.startActivity(i);
    }
}
