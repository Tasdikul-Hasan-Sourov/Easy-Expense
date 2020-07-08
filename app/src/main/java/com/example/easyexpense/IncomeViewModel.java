package com.example.easyexpense;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IncomeViewModel extends AndroidViewModel {
    private IncomeRepository repository;
    private LiveData<List<Income>> allIncome;
    public IncomeViewModel(@NonNull Application application) {
        super(application);
        repository = new IncomeRepository(application);
        allIncome = repository.getAllIncome();
    }
    public void insert(Income income) {
        repository.insert(income);
    }
    public void update(Income income) {
        repository.update(income);
    }
    public void delete(Income income) {
        repository.delete(income);
    }
    public void deleteAllIncome() {
        repository.deleteAllIncome();
    }
    public LiveData<List<Income>> getAllIncome() {
        return allIncome;
    }
}
