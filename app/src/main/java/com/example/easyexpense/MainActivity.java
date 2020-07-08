package com.example.easyexpense;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private CardView incomeCard;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton=findViewById(R.id.addIncome);
        incomeCard=findViewById(R.id.incomeCard);
        actionBar=getSupportActionBar();
        actionBar.hide();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddEditIncomeActivity.class);
                startActivity(intent);
            }
        });
        incomeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),IncomeView.class);
                startActivity(intent);
            }
        });
    }
}