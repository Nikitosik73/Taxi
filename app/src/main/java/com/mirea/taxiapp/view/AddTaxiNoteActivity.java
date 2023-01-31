package com.mirea.taxiapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mirea.taxiapp.databinding.ActivityAddTaxiNoteBinding;
import com.mirea.taxiapp.data.CarNote;
import com.mirea.taxiapp.viewmodel.AddTaxiNoteViewModel;

public class AddTaxiNoteActivity extends AppCompatActivity {

    private ActivityAddTaxiNoteBinding binding;

    private AddTaxiNoteViewModel addTaxiNoteViewModel;

    private int priority;
    private String titleCar;
    private String saleCar;
    private String speedCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTaxiNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addTaxiNoteViewModel = new ViewModelProvider(this).get(AddTaxiNoteViewModel.class);

        addTaxiNoteViewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldScreen) {
                if (shouldScreen){
                    finish();
                }
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCarNote();
            }
        });
    }

    private void saveCarNote() {

        titleCar = binding.editTextTitleCar.getText().toString().trim();
        saleCar = binding.editTextSaleCar.getText().toString().trim();
        speedCar = binding.editTextSpeedCar.getText().toString().trim();
        priority = getPriority();

        CarNote carNote = new CarNote(titleCar, saleCar, speedCar, priority);
        addTaxiNoteViewModel.add(carNote);
    }

    private int getPriority() {
        if (binding.radioButtonLowCar.isChecked()) {
            priority = 0;
        } else if (binding.radioButtonMediumCar.isChecked()) {
            priority = 1;
        } else if (binding.radioButtonHighCar.isChecked()) {
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddTaxiNoteActivity.class);
    }
}