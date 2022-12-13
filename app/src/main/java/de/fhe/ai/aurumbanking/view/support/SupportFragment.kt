package de.fhe.ai.aurumbanking.view.support


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import de.fhe.ai.aurumbanking.R

class SupportFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_support, container, false)


        val request = resources.getStringArray(R.array.kind_of_request)

        val spinner = root.findViewById<Spinner>(R.id.spinnerSelectRequest)
        if (spinner != null) {
            val adapter = ArrayAdapter( requireActivity(), R.layout.spinner_kind_of_request, request)
            adapter.setDropDownViewResource(R.layout.spinner_kind_of_request)
            spinner.adapter = adapter
        }
        return root
    }
}