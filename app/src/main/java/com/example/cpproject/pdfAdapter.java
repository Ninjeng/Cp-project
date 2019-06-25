package com.example.cpproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class pdfAdapter extends ArrayAdapter<Books> {
   private static final String TAG ="BookListAdapter";
    private Context context;
    ViewHolder  viewHolder;
    ArrayList<String[]> al_pdf;
    ArrayList<Books> objects;
    int resourse;

    public pdfAdapter(Context context, int resource, Books[] objects, ArrayList<String[]> al_pdf) {
        super(context, resource, objects);
        this.context = context;
        this.al_pdf = al_pdf;
        this.resourse=resource;
    }

    @Override
    public View getView(final int position,View convertView, ViewGroup parent) {
       String title= getItem(position).getTitle();
        String Author= getItem(position).getTitle();
        String url= getItem(position).getUrl();
        String year= getItem(position).getYear();
        String publisher= getItem(position).getPublisher();

        Books books = new Books(title,Author,year,publisher,url);
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        convertView= layoutInflater.inflate(resourse,parent,false);
        TextView txtTitle = convertView.findViewById(R.id.tv_title);
        TextView txtAuthor = convertView.findViewById(R.id.tv_author);
        txtTitle.setText(title);
        txtAuthor.setText(Author);
        return convertView;
    }

    class ViewHolder
 {
     TextView textViewname;
 }

}
