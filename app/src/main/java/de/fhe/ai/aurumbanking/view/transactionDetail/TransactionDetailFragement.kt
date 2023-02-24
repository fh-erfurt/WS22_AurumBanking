package de.fhe.ai.aurumbanking.view.transactionDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.root = inflater.inflate(R.layout.fragment_transaction_detail, container, false)

        this.viewModel = ViewModelProvider(this)[TransactionDetailViewModel::class.java]

        requireArguments().let { args ->
            val transactionListId = args.getLong(ARG_TRANSACTIONLISTID_ID)

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
        }

        return this.root
    }

    private fun updateViewBankName(bankName: String) {
        this.root.findViewById<TextView?>(R.id.bankName).text = bankName
    }

    private fun updateViewBIC(bic: String) {
        this.root.findViewById<TextView?>(R.id.bic).text = bic
    }

    private fun updateViewDate(date: String) {
        this.root.findViewById<TextView?>(R.id.Date).text = date
    }

    private fun updateViewPurposeOfUse(purposeOfUse: String) {
        this.root.findViewById<TextView?>(R.id.purposeOfUse).text = purposeOfUse
    }

    private fun updateViewIban(iban: String) {
        this.root.findViewById<TextView?>(R.id.iban).text = iban
    }


}