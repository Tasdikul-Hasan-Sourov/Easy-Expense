package com.example.easyexpense;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class IncomeRepository {
    private IncomeDao incomeDao;
    private LiveData<List<Income>> allIncome;
    public IncomeRepository(Application application) {
        IncomeDatabase database = IncomeDatabase.getInstance(application);
        incomeDao = database.incomeDao();
        allIncome = incomeDao.getAllIncome();
    }
    public void insert(Income income) {
        new InsertIncomeAsyncTask(incomeDao).execute(income);
    }
    public void update(Income income) {
        new UpdateIncomeAsyncTask(incomeDao).execute(income);
    }
    public void delete(Income income) {
        new DeleteIncomeAsyncTask(incomeDao).execute(income);
    }
    public void deleteAllIncome() {
        new DeleteAllIncomesAsyncTask(incomeDao).execute();
    }
    public LiveData<List<Income>> getAllIncome() {
        return allIncome;
    }
    private static class InsertIncomeAsyncTask extends AsyncTask<Income, Void, Void> {
        private IncomeDao incomeDao;
        private InsertIncomeAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }
        @Override
        protected Void doInBackground(Income... incomes) {
            incomeDao.insert(incomes[0]);
            return null;
        }
    }
    private static class UpdateIncomeAsyncTask extends AsyncTask<Income, Void, Void> {
        private IncomeDao incomeDao;
        private UpdateIncomeAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }
        @Override
        protected Void doInBackground(Income... incomes) {
            incomeDao.update(incomes[0]);
            return null;
        }
    }
    private static class DeleteIncomeAsyncTask extends AsyncTask<Income, Void, Void> {
        private IncomeDao incomeDao;
        private DeleteIncomeAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }
        @Override
        protected Void doInBackground(Income... incomes) {
            incomeDao.delete(incomes[0]);
            return null;
        }
    }
    private static class DeleteAllIncomesAsyncTask extends AsyncTask<Void, Void, Void> {
        private IncomeDao incomeDao;
        private DeleteAllIncomesAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            incomeDao.deleteAllIncome();
            return null;
        }
    }
}
