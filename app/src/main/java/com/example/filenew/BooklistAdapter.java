package com.example.filenew;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filenew.models.BookItem;

import java.util.ArrayList;

public class BooklistAdapter extends BaseAdapter
{

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<BookItem> bookitemList;


    public BooklistAdapter(Context context, ArrayList<BookItem> data){
        mContext = context;
        bookitemList = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return bookitemList.size();
    }

    @Override
    public BookItem getItem(int position) {
        return bookitemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.activity_books, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.img_book);
        TextView title = (TextView)view.findViewById(R.id.txt_title);
        TextView author = (TextView)view.findViewById(R.id.txt_author);
        TextView description = (TextView)view.findViewById(R.id.txt_description);

        Glide.with(view)
                .load(bookitemList.get(position).getImage())
                .into(imageView);
        title.setText(Html.fromHtml(bookitemList.get(position).getTitle()));
        author.setText(Html.fromHtml(bookitemList.get(position).getAuthor()));
        description.setText(Html.fromHtml(bookitemList.get(position).getDescription()));
        return view;
    }
}
