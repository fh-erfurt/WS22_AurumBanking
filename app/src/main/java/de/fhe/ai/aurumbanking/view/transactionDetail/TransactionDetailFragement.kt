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
 * create an instance of this fragment.
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
        // Inflate the layout for this fragment
        this.root = inflater.inflate(R.layout.fragment_transaction_detail, container, false)

        this.viewModel = ViewModelProvider(this)[TransactionDetailViewModel::class.java]

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

    private fun updateViewBankName(bankName: String) {
        this.root.findViewById<TextView?>(R.id.bankName).text = bankName
    }

    private fun updateViewBIC(bic: String) {
        if (bic.isEmpty()){
            this.root.findViewById<TextView?>(R.id.bic).text = "leer"
        } else{
            this.root.findViewById<TextView?>(R.id.bic).text = bic.toString()
        }
    }

    private fun updateViewDate(date: String) {
        this.root.findViewById<TextView?>(R.id.Date).text = date
    }

    private fun updateViewPurposeOfUse(purposeOfUse: String) {
        this.root.findViewById<TextView?>(R.id.purposeOfUse).text = purposeOfUse
    }

    private fun updateViewIban(iban: String) {
        if (iban.isEmpty()){
            this.root.findViewById<TextView?>(R.id.iban).text  = "leer"
        } else{
            this.root.findViewById<TextView?>(R.id.iban).text = iban
        }
    }

    private fun updateViewIconForLatestTransaction(deductionFlag : Boolean){
        if (deductionFlag){
            this.root.findViewById<ImageButton>(R.id.cicle)
                .setImageResource(R.drawable.redcirle)
        }else{
            this.root.findViewById<ImageButton>(R.id.cicle)
                .setImageResource(R.drawable.greencirle)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun updateViewValueForLatestTransaction(moneyValue : String){
        this.root.findViewById<TextView>(R.id.lastTransaction).text = moneyValue.toString().replace(",", "\n\n") + " Euro"

    }


}