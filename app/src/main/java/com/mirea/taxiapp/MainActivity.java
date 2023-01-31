package com.mirea.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mirea.taxiapp.databinding.ActivityMainBinding;
import com.mirea.taxiapp.view.TaxiNoteActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TaxiNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }
}