package com.example.filenew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.filenew.databinding.ActivityMainBinding;
import com.example.filenew.utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(v -> save());
        binding.btnLoad.setOnClickListener(v -> load());
    }

    private void save() {
        try {
            FileOutputStream fos = openFileOutput("memo.txt", Context.MODE_PRIVATE);
            fos.write(binding.etxtMemo.getText().toString().getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        String memo = FileUtils.readFile(this, "memo.txt");
        binding.etxtMemo.setText(memo);
    }


    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }


}