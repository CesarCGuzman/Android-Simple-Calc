package com.example.simple_calculator;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextNumOne;
    EditText editTextNumTwo;
    TextView textViewOp;
    final static public String OP_KEY = "OP";
    final static public String CALC_KEY = "CALC_KEY";
    String op = "";
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Simple  Calculator");

        editTextNumOne = findViewById(R.id.editTextNumOne);
        editTextNumTwo = findViewById(R.id.editTextNumTwo);
        textViewOp = findViewById(R.id.textViewOp);

        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result != null && result.getResultCode() == RESULT_OK){
                            if(result.getData() != null && result.getData().hasExtra(MainActivity.OP_KEY)){
                                Intent data = result.getData();
                                op = data.getStringExtra(MainActivity.OP_KEY);
                                textViewOp.setText(op);
                            }
                        }
                    }
                });

                findViewById(R.id.buttonPickOp).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startForResult.launch(intent);
                    }
                });

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputNumOne = editTextNumOne.getText().toString();
                String inputNumTwo = editTextNumTwo.getText().toString();
                if(inputNumOne.isEmpty()){
                    Toast.makeText(MainActivity.this, "Number A must not be empty", Toast.LENGTH_SHORT).show();
                } else if (inputNumTwo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Number B must not be empty", Toast.LENGTH_SHORT).show();
                } else if (op.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Operation must be chosen", Toast.LENGTH_SHORT).show();
                } else if (Double.valueOf(editTextNumTwo.getText().toString()) == 0 && op.equals("/")) {
                    Toast.makeText(MainActivity.this, "Cannot Divide by 0", Toast.LENGTH_SHORT).show();
                } else {
                    double numOne = Double.valueOf(editTextNumOne.getText().toString());
                    double numTwo = Double.valueOf(editTextNumTwo.getText().toString());
                    total = calculate(Double.valueOf(editTextNumOne.getText().toString()), Double.valueOf(editTextNumTwo.getText().toString()), op);
                    Calculation calculation = new Calculation(numOne, numTwo, op, total);
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra(CALC_KEY, calculation);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNumOne.setText("");
                editTextNumTwo.setText("");
                textViewOp.setText("?");
            }
        });
    }

    public double calculate(double numA, double numB, String operation){
        double calcTotal = 0.0;
        if(operation.equals("+")){
            calcTotal = numA + numB;
        } else if (operation.equals("-")) {
            calcTotal = numA - numB;
        } else if (operation.equals("*")) {
            calcTotal = numA * numB;
        } else if (operation.equals("/")) {
            calcTotal = numA / numB;
        }
        return calcTotal;
    }
}