package com.example.filenew;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.filenew.databinding.ActivityBooksBinding;
import com.example.filenew.models.BookItem;
import com.example.filenew.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class BooklistActivity extends AppCompatActivity {
    private ActivityBooksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBooksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        GSON();

//        try {
//            String book = readFromAssets("abook.json");
//            JSONObject bookItem = new JSONObject(book);
//            String text = "" + bookItem.getString("title");
//            binding.txtTitle.setText(text);
//            String author = "" + bookItem.getString("author");
//            binding.txtAuthor.setText(author);
//            String description = "" + bookItem.getString("description");
//            binding.txtDescription.setText(Html.fromHtml(description));
//
//            Glide.with(this)
//                    .load(bookItem.getString("image"))
//                    .into(binding.imgBook);
//        } catch (IOException e) {
//            Log.i("DEBUG", "READ FILE FAIL");
//        } catch (
//        JSONException e) {
//            Log.i("DEBUG", "JSON FILE ERROR");
//        }

        try {
            String jsonString = readFromAssets("books.json");
            Gson gson = new Gson();
            BookItem bookItem = gson.fromJson(jsonString, BookItem.class);
            List<BookItem> array = gson.fromJson(String.valueOf(bookItem), new TypeToken<List<BookItem>>() {
            }.getType());
            binding.txtTitle.setText(bookItem.getTitle());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private void GSON() throws IOException {
//        Gson gson = new Gson();
//        String jsonString = readFromAssets("books.json");
//        BookItem bookItem = gson.fromJson(jsonString, BookItem.class);
//        List<BookItem> array = gson.fromJson(bookItem, new TypeToken<List<BookItem>>() {
//        }.getType());
//        binding.txtTitle.setText(bookItem.getTitle());
//    }

    public String readFromAssets(String name) throws IOException {
        InputStream inputStream = getAssets().open(name);
        return FileUtils.readStream(inputStream);
    }
}
