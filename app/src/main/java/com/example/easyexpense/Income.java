package com.example.easyexpense;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "income_table")
public class Income {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private String money;
    private String datetime;

    public Income( String description, String money, String datetime) {
        this.description = description;
        this.money = money;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
