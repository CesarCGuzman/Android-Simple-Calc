package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    RadioGroup radioGroupOps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Pick Operation");

        radioGroupOps = findViewById(R.id.radioGroupOps);

        findViewById(R.id.buttonSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String op = getOp(radioGroupOps.getCheckedRadioButtonId());
                intent.putExtra(MainActivity.OP_KEY, op);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String getOp(int checkedID){
        if(checkedID == R.id.radioButtonAdd){
            return "+";
        } else if (checkedID == R.id.radioButtonSubtract) {
            return "-";
        } else if (checkedID == R.id.radioButtonMultiply) {
            return "*";
        } else if (checkedID == R.id.radioButtonDivide) {
            return "/";
        }
        Toast.makeText(this, "Operation Not Chosen", Toast.LENGTH_SHORT).show();
        return "";
    }
}