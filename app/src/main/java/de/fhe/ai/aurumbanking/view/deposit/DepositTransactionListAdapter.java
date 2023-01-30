package de.fhe.ai.aurumbanking.view.deposit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import de.fhe.ai.aurumbanking.R;
import de.fhe.ai.aurumbanking.model.TransactionList;

public class DepositTransactionListAdapter extends RecyclerView.Adapter<DepositTransactionListAdapter.TransactionListViewHolder> {
    static class TransactionListViewHolder extends RecyclerView.ViewHolder {
        private final TextView valueForTransaction;
        private final ImageView iconKindOfTransaction;

        private TransactionListViewHolder(View itemView) {
            super(itemView);
            this.valueForTransaction = itemView.findViewById(R.id.lastTransaction);
            this.iconKindOfTransaction = itemView.findViewById(R.id.iconKindOfTransaction);
        }


    }

    private final LayoutInflater inflater;
    private List<TransactionList> transactionList; // Cached Copy of Contacts

    public DepositTransactionListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TransactionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.transaction_list_design, parent, false);

        return new TransactionListViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TransactionListViewHolder holder, int position) {
        if( this.transactionList != null && !this.transactionList.isEmpty()){
            TransactionList currentTransactionListElement = this.transactionList.get(position);

            holder.valueForTransaction.setText((currentTransactionListElement.getBeneficiary() + "\n" + currentTransactionListElement.getTransactionDate() + "\n"
                    + currentTransactionListElement.getMoneyValue()).toString() + " Euro" ) ;

            if (Boolean.TRUE.equals(currentTransactionListElement.getOutputFlag())){
                holder.iconKindOfTransaction.setImageResource(R.drawable.redcirle);
            }else{
                holder.iconKindOfTransaction.setImageResource(R.drawable.greencirle);
            }


        }
    }

    @Override
    public int getItemCount() {
        if( this.transactionList != null && !this.transactionList.isEmpty() )
            return this.transactionList.size();
        return 0;
    }

    public void setTransactionList(List<TransactionList> transactionList){
        this.transactionList = transactionList;
        notifyDataSetChanged();
    }
}
