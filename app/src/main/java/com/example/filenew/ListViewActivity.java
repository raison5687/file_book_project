package com.example.filenew;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.filenew.models.BookItem;
import com.example.filenew.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity
{

    ArrayList<BookItem> bookItemlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        this.StartListView();

        ListView listView = (ListView)findViewById(R.id.listview1);
        final BooklistAdapter adapter = new BooklistAdapter(this, bookItemlist);

        listView.setAdapter(adapter);

    }

    public ArrayList<BookItem> StartListView()
    {
        bookItemlist = new ArrayList<BookItem>();
        try {
            String jsonString = readFromAssets("books.json");
            Gson gson = new Gson();
            bookItemlist = gson.fromJson(jsonString, new TypeToken<List<BookItem>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookItemlist;
    }
    public String readFromAssets(String name) throws IOException {
        InputStream inputStream = getAssets().open(name);
        return FileUtils.readStream(inputStream);
    }
}
