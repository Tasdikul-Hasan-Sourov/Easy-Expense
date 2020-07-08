package com.example.easyexpense;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Income.class},version = 1)
public abstract  class IncomeDatabase extends RoomDatabase {
    public static IncomeDatabase instance;
    public abstract IncomeDao incomeDao();

    public static synchronized IncomeDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    IncomeDatabase.class, "income_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    public static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private IncomeDao incomeDao;
        private PopulateDbAsyncTask(IncomeDatabase db) {
            incomeDao = db.incomeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
