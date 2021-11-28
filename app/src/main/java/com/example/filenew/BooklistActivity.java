package com.example.filenew;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.filenew.databinding.ActivityBooksBinding;
import com.example.filenew.models.BookItem;
import com.example.filenew.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class BooklistActivity extends AppCompatActivity {
    private ActivityBooksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityBooksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            String jsonString = readFromAssets("books.json");
            Gson gson = new Gson();
            List<BookItem> bookitemList = gson.fromJson(jsonString, new TypeToken<List<BookItem>>(){}.getType());
            binding.txtTitle.setText(bookitemList.get(0).getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromAssets(String name) throws IOException {
        InputStream inputStream = getAssets().open(name);
        return FileUtils.readStream(inputStream);
    }




}
