package com.sullivan.support;

public class Transaction {
    public Transaction() {
    }

    public static double withdrawal(double bal, double w) {
        double new_bal = bal - w;
        if (new_bal < 0.0D) {
            System.out.println("Insufficient funds to complete transaction");
        }

        return new_bal;
    }

    public static double deposit(double bal, double d) {
        double new_bal = bal + d;
        return new_bal;
    }
}
