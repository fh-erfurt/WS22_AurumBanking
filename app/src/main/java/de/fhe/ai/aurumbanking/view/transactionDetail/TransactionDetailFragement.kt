package de.fhe.ai.aurumbanking.view.transactionDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.view.deposit.DepositViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [transactionDetail.newInstance] factory method to
 * create an instance of this fragment. This class include the detail view layout of an transaction.
 */
class TransactionDetailFragment : Fragment() {

    private lateinit var root: View
    private lateinit var viewModel: TransactionDetailViewModel
    private val ARG_TRANSACTIONLISTID_ID = "transactionListId"
    private var transactionListId : Long? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        this.root = inflater.inflate(R.layout.fragment_transaction_detail, container, false)

        this.viewModel = ViewModelProvider(this)[TransactionDetailViewModel::class.java]

        /**
         * get transactionListId from args bundle, which was save in the Deposit Fragment 
         */
        requireArguments().let { args ->
            this.transactionListId = args.getLong(ARG_TRANSACTIONLISTID_ID)
        }
        return this.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        this.viewModel.getBanknameByTransactionListIdId(transactionListId)
            .observe(requireActivity(), this::updateViewBankName)

        this.viewModel.getIbanByTransactionListIdId(transactionListId)
            .observe(requireActivity(), this::updateViewIban)

        this.viewModel.getBicTransactionListIdId(transactionListId)
            .observe(requireActivity(), this::updateViewBIC)

        this.viewModel.getPurposeOfUseTransactionListIdId(transactionListId)
            .observe(requireActivity(), this::updateViewPurposeOfUse)

        this.viewModel.getTransactionDateByTransactionListId(transactionListId)
            .observe(requireActivity(), this::updateViewDate)

        this.viewModel.getLatestOutputFlagByTransactionListId(transactionListId)
            .observe(this.requireActivity(), this::updateViewIconForLatestTransaction)

        this.viewModel.getLatestMoneyValueFromTransactionListByTransactionListId(transactionListId)
            .observe(this.requireActivity(), this::updateViewValueForLatestTransaction)
    }

    override fun onPause() {
        super.onPause()

        this.viewModel.getBanknameByTransactionListIdId(transactionListId)
            .removeObserver(this::updateViewBankName)

        this.viewModel.getIbanByTransactionListIdId(transactionListId)
            .removeObserver(this::updateViewIban)

        this.viewModel.getBicTransactionListIdId(transactionListId)
            .removeObserver(this::updateViewBIC)

        this.viewModel.getPurposeOfUseTransactionListIdId(transactionListId)
            .removeObserver(this::updateViewPurposeOfUse)

        this.viewModel.getTransactionDateByTransactionListId(transactionListId)
            .removeObserver(this::updateViewDate)

    }

    /**
     * set bankname to view layout
     *
     * @param bankName
     */
    private fun updateViewBankName(bankName: String) {
        this.root.findViewById<TextView?>(R.id.bankName).text = bankName
    }

    /**
     * set bic to view layout
     *
     * @param bic
     */
    private fun updateViewBIC(bic: String) {
        if (bic.isEmpty()){
            this.root.findViewById<TextView?>(R.id.bic).text = "leer"
        } else{
            this.root.findViewById<TextView?>(R.id.bic).text = bic.toString()
        }
    }

    /**
     * set transaction date to view layout
     *
     * @param date
     */
    private fun updateViewDate(date: String) {
        this.root.findViewById<TextView?>(R.id.Date).text = date
    }

    /**
     * set purpose of use date to view layout
     *
     * @param purposeOfUse
     */
    private fun updateViewPurposeOfUse(purposeOfUse: String) {
        this.root.findViewById<TextView?>(R.id.purposeOfUse).text = purposeOfUse
    }

    /**
     * set iban to view layout
     *
     * @param iban
     */
    private fun updateViewIban(iban: String) {
        if (iban.isEmpty()){
            this.root.findViewById<TextView?>(R.id.iban).text  = "leer"
        } else{
            this.root.findViewById<TextView?>(R.id.iban).text = iban
        }
    }

    /**
     * check kind of transaction and set icon to view layout 
     *
     * @param deductionFlag
     */
    private fun updateViewIconForLatestTransaction(deductionFlag : Boolean){
        if (deductionFlag){
            this.root.findViewById<ImageButton>(R.id.cicle)
                .setImageResource(R.drawable.redcirle)
        }else{
            this.root.findViewById<ImageButton>(R.id.cicle)
                .setImageResource(R.drawable.greencirle)
        }

    }

    /**
     * set transaction value to view layout
     *
     * @param moneyValue
     */
    @SuppressLint("SetTextI18n")
    private fun updateViewValueForLatestTransaction(moneyValue : String){
        this.root.findViewById<TextView>(R.id.lastTransaction).text = moneyValue.toString().replace(",", "\n\n") + " Euro"

    }


}