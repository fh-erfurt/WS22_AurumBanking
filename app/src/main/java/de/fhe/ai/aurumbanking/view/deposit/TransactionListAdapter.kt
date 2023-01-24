package de.fhe.ai.aurumbanking.view.deposit

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.model.TransactionList

public class TransactionListAdapter(context : Context, transactionListListener : TransactionListListener):
    RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {


    interface TransactionListListener {
        fun onClick(transactionListId: Long)
    }

    var counter = 0

    // A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    inner class TransactionViewHolder(itemView : View, transactionListListener : TransactionListListener) : ViewHolder(itemView){
        var valueForTransaction : TextView
        var iconKindOfTransaction: ImageView

        var currentListId: Long = -1

        init {
            this.valueForTransaction = itemView.findViewById(R.id.lastTransaction)
            this.iconKindOfTransaction = itemView.findViewById(R.id.iconKindOfTransaction)

            // set setOnClickListener for icon or value, when init viewHolder
            itemView.setOnClickListener {
                transactionListListener.onClick(this.currentListId)
            }
        }
    }


    private lateinit var inflater : LayoutInflater
    private lateinit var transactionList: List<TransactionList>
    private lateinit var transactionListListener: TransactionListListener


    init{
        this.inflater = LayoutInflater.from(context)
        this.transactionListListener = transactionListListener
    }


    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView: View = inflater.inflate(R.layout.transaction_list_design, parent, false)
        Log.i(
            "OnCreateViewHolder", "Count: $counter"
        )
        return TransactionViewHolder(itemView, this.transactionListListener)
    }


    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        if(this.transactionList.isNotEmpty()){

            val currentObj : TransactionList = this.transactionList[position]
            holder.currentListId = currentObj.transactionListId!!

            holder.valueForTransaction.text = currentObj.transactionListId.toString()

            if (currentObj.deductionFlag == true){
                holder.iconKindOfTransaction.setImageResource(R.drawable.redcirle)
            }else{
                holder.iconKindOfTransaction.setImageResource(R.drawable.greencirle)
            }
        }
    }

    override fun getItemCount(): Int {
        if (this.transactionList.isNotEmpty()){
            return this.transactionList.count()
        }else{
            return 0;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTransactionList(transactionList: List<TransactionList>) {
        this.transactionList = transactionList
        notifyDataSetChanged()
    }

}