package com.example.easyexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddEditIncomeActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.codinginflow.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.codinginflow.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.codinginflow.architectureexample.EXTRA_PRIORITY";
    private EditText editTitle;
    private EditText editAmount;
    private TextView date;
    private Button save,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_income);
        editTitle = findViewById(R.id.addDes);
        editAmount = findViewById(R.id.addMoney);
        date = findViewById(R.id.addDate);
        save=findViewById(R.id.saveExpense);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editAmount.setText(intent.getStringExtra(EXTRA_PRIORITY));
            date.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String description = editTitle.getText().toString();
        String money = editAmount.getText().toString();
        String datetime = date.getText().toString();
        if (description.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert description,amount and date", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, description);
        data.putExtra(EXTRA_PRIORITY, money);
        data.putExtra(EXTRA_DESCRIPTION, datetime);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

   // @Override
    //public boolean onCreateOptionsMenu(Menu menu) {
      //  MenuInflater menuInflater = getMenuInflater();
        //menuInflater.inflate(R.menu.add_note_menu, menu);
        //return true;
    //}

  //  @Override
   // public boolean onOptionsItemSelected(MenuItem item) {
     //   switch (item.getItemId()) {
         //   case R.id.saveExpense:
       //         saveNote();
           //     return true;
            //default:
              //  return super.onOptionsItemSelected(item);
        //}
    //}
    public void getSave(View view){
        saveNote();
    }

}