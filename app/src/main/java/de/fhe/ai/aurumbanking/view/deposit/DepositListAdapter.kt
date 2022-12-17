package de.fhe.ai.aurumbanking.view.deposit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import de.fhe.ai.aurumbanking.R

private class DepositListAdapter ( context: Context): RecyclerView.Adapter<DepositListAdapter.DepositViewHolder>() {

    internal class DepositViewHolder (itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var expensesIncomeInformation: TextView
        private lateinit var expensesIncomeImage: ImageView

        private fun ContactViewHolder() {
            this.expensesIncomeInformation =
                itemView.findViewById(R.id.listExpensesIncomeInformation);
            this.expensesIncomeImage = itemView.findViewById(R.id.listExpensesIncomeImage);
        }
    }


    private lateinit var inflater : LayoutInflater ;

    // TODO: Init Later
    // private lateinit var contactList : List<transaction>; // Cached Copy of Contacts


    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): DepositViewHolder {
        // TODO LANDSCAPE MODE
        val itemView : View = this.inflater.inflate(R.layout.list_item_deposit, parent, false);
        return DepositViewHolder(itemView)

    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: DepositViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}