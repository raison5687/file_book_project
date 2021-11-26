package com.example.filenew;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.filenew.databinding.ActivityJsonBinding;
import com.example.filenew.utils.FileUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class JsonActivity extends AppCompatActivity {
    private ActivityJsonBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJsonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            String book = readFromAssets("abook.json");
            JSONObject bookItem = new JSONObject(book);
            String title = "제목: " + bookItem.getString("title");
            binding.title.setText(title);
            String author = "저자: " + bookItem.getString("author");
            binding.author.setText(author);
            String price = "가격: " + bookItem.getString("price");
            binding.price.setText(price);
            String tag = "" + bookItem.getString("description");
//            String tag = "<b>Apply tag</b> Like this!";
            binding.tag.setText(Html.fromHtml(tag));

            Glide.with(this)
                    .load(bookItem.getString("image"))
                    .into(binding.image);

        } catch (IOException e) {
            Log.i("DEBUG", "READ FILE FAIL");
        } catch (JSONException e) {
            Log.i("DEBUG", "JSON FILE ERROR");
        }


//        String book = readFromAssets("abook.json");
//        JSONObject bookItem = new JSONObject(book);
//        String text = "제목: " + bookItem.getString("title") +
//                ", 가격: " + bookItem.getInt("price") + "\n" +
//                "책 이미지 url: " + bookItem.getString("image");
//        binding.text.setText(text);
//
//        Glide.with(this)
//                .load(bookItem.getString("image"))
//                .into(binding.image);



    }

    public String readFromAssets(String name) throws IOException {
        InputStream inputStream = getAssets().open(name);
        return FileUtils.readStream(inputStream);
    }


}
