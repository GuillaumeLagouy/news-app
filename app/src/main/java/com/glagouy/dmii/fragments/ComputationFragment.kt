package com.glagouy.dmii.fragments

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.glagouy.dmii.Operation
import com.glagouy.dmii.R
import com.glagouy.dmii.extensions.toDouble
import com.glagouy.dmii.viewModels.ComputeViewModel
import kotlinx.android.synthetic.main.computation_fragment.*
import java.nio.file.WatchEvent

class ComputationFragment : Fragment() {
    private lateinit var computeViewModel: ComputeViewModel
    var nb1: Double = 0.0
    var nb2: Double = 0.0

    val operation: Operation by lazy {
        arguments?.getParcelable(ARGS_OPERATION) ?: Operation.SUM
    }

    companion object {
        const val ARGS_OPERATION = "ARGS_OPERATION"
        fun newInstance(operation: Operation): ComputationFragment {
            return ComputationFragment().apply {
                arguments = bundleOf(ARGS_OPERATION to operation)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        computeViewModel = ViewModelProvider(this).get(ComputeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.computation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        number1.addTextChangedListener(watcher)
        number2.addTextChangedListener(watcher)

        compute.setOnClickListener {
            compute()
        }
    }

    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            compute()
        }

    }

    private fun compute() {
        nb1 = number1.toDouble() ?: return
        nb2 = number2.toDouble() ?: return
        computeViewModel.compute(
            nb1 = nb1,
            nb2 = nb2,
            operation = operation
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        computeViewModel.resultatLiveData.observe(viewLifecycleOwner, Observer {

            when (operation) {
                Operation.SUM -> result.text = getString(
                    R.string.result,
                    getString(R.string.addition),
                    nb1.doubleFormat(),
                    nb2.doubleFormat(),
                    it.doubleFormat()
                ).capitalize()
                Operation.MINUS -> result.text = getString(
                    R.string.result,
                    getString(R.string.soustraction),
                    nb1.doubleFormat(),
                    nb2.doubleFormat(),
                    it.doubleFormat()
                ).capitalize()
                Operation.PRODUCT -> result.text = getString(
                    R.string.result,
                    getString(R.string.multiplication),
                    nb1.doubleFormat(),
                    nb2.doubleFormat(),
                    it.doubleFormat()
                ).capitalize()
                Operation.DIVISE -> result.text = getString(
                    R.string.result,
                    getString(R.string.division),
                    nb1.doubleFormat(),
                    nb2.doubleFormat(),
                    it.doubleFormat()
                ).capitalize()
            }
        })
    }

    private fun Double.doubleFormat() = String.format("%.2f", this)
}

