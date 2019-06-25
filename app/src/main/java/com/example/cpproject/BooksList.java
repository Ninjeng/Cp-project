package com.example.cpproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BooksList extends ArrayAdapter {
    private Activity context;
    private List<Books> booksList;
    public BooksList(Activity context,List<Books> booksList) {
        super(context,R.layout.adapter_pdf,booksList);
        this.context=context;
        this.booksList=booksList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewitem = inflater.inflate(R.layout.adapter_pdf,null,true);
        TextView textViewTitle = listViewitem.findViewById(R.id.tv_title);
        TextView textViewAuthor = listViewitem.findViewById(R.id.tv_author);
        Books books = booksList.get(position);
        textViewTitle.setText(books.getTitle());
        textViewAuthor.setText(books.getAuthor());
        return listViewitem;
    }
}
