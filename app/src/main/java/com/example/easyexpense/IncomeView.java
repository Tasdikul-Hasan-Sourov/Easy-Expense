package com.example.easyexpense;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class IncomeView extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private IncomeViewModel incomeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_view);
        Button buttonAddNote = findViewById(R.id.adinFloat);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncomeView.this, AddEditIncomeActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recIncome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final IncomeAdapter adapter = new IncomeAdapter();
        recyclerView.setAdapter(adapter);
        incomeViewModel = ViewModelProviders.of(this).get(IncomeViewModel.class);
        incomeViewModel.getAllIncome().observe(this, new Observer<List<Income>>() {
            @Override
            public void onChanged(@Nullable List<Income> incomes) {
                adapter.setIncomes(incomes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                incomeViewModel.delete(adapter.getIncomeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(IncomeView.this, "Income deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new IncomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Income income) {
                Intent intent = new Intent(IncomeView.this, AddEditIncomeActivity.class);
                intent.putExtra(AddEditIncomeActivity.EXTRA_ID, income.getId());
                intent.putExtra(AddEditIncomeActivity.EXTRA_TITLE, income.getDescription());
                intent.putExtra(AddEditIncomeActivity.EXTRA_DESCRIPTION, income.getDatetime());
                intent.putExtra(AddEditIncomeActivity.EXTRA_PRIORITY, income.getMoney());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String description = data.getStringExtra(AddEditIncomeActivity.EXTRA_TITLE);
            String money = data.getStringExtra(AddEditIncomeActivity.EXTRA_PRIORITY);
            String date  = data.getStringExtra(AddEditIncomeActivity.EXTRA_DESCRIPTION);
            Income income = new Income(description, money, date);
            incomeViewModel.insert(income);
            Toast.makeText(this, "Income saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditIncomeActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String description = data.getStringExtra(AddEditIncomeActivity.EXTRA_TITLE);
            String money = data.getStringExtra(AddEditIncomeActivity.EXTRA_PRIORITY);
            String date = data.getStringExtra(AddEditIncomeActivity.EXTRA_DESCRIPTION);
            Income income = new Income(description,money, date);
            income.setId(id);
            incomeViewModel.update(income);
            Toast.makeText(this, "Income updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Income not saved", Toast.LENGTH_SHORT).show();
        }
    }
   // @Override
    ///public boolean onCreateOptionsMenu(Menu menu) {
       // MenuInflater menuInflater = getMenuInflater();
        //menuInflater.inflate(R.menu.main_menu, menu);
        //return true;
    //}
    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
      //  switch (item.getItemId()) {
        //    case R.id.delete_all_notes:
          //      noteViewModel.deleteAllNotes();
            //    Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
              //  return true;
            //default:
              //  return super.onOptionsItemSelected(item);
        //}
    }

