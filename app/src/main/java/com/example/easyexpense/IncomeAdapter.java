package com.example.easyexpense;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeHolder> {
    private List<Income> incomes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public IncomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.income_item, parent, false);
        return new IncomeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeHolder holder, int position) {
        Income currentIncome = incomes.get(position);
        holder.description.setText(currentIncome.getDescription());
        holder.dateTime.setText(currentIncome.getDatetime());
        holder.inMoney.setText(String.valueOf(currentIncome.getMoney()));

    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }
    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
        notifyDataSetChanged();
    }
    public Income getIncomeAt(int position) {
        return incomes.get(position);
    }
    class IncomeHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private TextView dateTime;
        private TextView inMoney;
        public IncomeHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.desIncome);
            dateTime = itemView.findViewById(R.id.dateIncome);
            inMoney = itemView.findViewById(R.id.inMoney);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(incomes.get(position));
                    }

                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Income income);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
