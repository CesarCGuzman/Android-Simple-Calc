package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView textViewNumOne;
    TextView textViewNumTwo;
    TextView textViewOperation;
    TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Result Viewer");

        textViewNumOne = findViewById(R.id.textViewNumOne);
        textViewNumTwo = findViewById(R.id.textViewNumTwo);
        textViewOperation = findViewById(R.id.textViewOperation);
        textViewTotal = findViewById(R.id.textViewTotal);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(MainActivity.CALC_KEY)){
            Calculation calculation = (Calculation)intent.getSerializableExtra(MainActivity.CALC_KEY);
            textViewNumOne.setText(String.valueOf(calculation.numA));
            textViewNumTwo.setText(String.valueOf(calculation.numB));
            textViewOperation.setText(calculation.operation);
            textViewTotal.setText(String.valueOf(calculation.total));
        }

        findViewById(R.id.buttonClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}