package com.example.simple_calculator;

import java.io.Serializable;

public class Calculation implements Serializable {
    double numA;
    double numB;
    String operation;
    double total;

    public Calculation(double numA, double numB, String operation, double total) {
        this.numA = numA;
        this.numB = numB;
        this.operation = operation;
        this.total = total;
    }
}
