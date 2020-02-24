package com.glagouy.dmii.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.glagouy.dmii.Operation
import com.glagouy.dmii.R
import kotlinx.android.synthetic.main.choice_fragment.*

class ChoiceFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choice_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view as? ViewGroup)?.children?.filter {
            it is Button
        }?.forEach {
            it.setOnClickListener {
                val activity = activity ?: return@setOnClickListener

                val operation:Operation =  when(tag){
                    "sum" ->  Operation.SUM
                    "substract" -> Operation.MINUS
                    "multiply" -> Operation.PRODUCT
                    "divide" -> Operation.DIVISE
                    else -> Operation.SUM
                }

                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentContainer, ComputationFragment.newInstance(operation))
                    addToBackStack(null)
                }.commit()
            }
        }

        substractBtn.setOnClickListener {

            val activity = activity ?: return@setOnClickListener

            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, ComputationFragment.newInstance(Operation.MINUS))
                addToBackStack(null)
            }.commit()
        }

        multiplyBtn.setOnClickListener {

            val activity = activity ?: return@setOnClickListener

            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, ComputationFragment.newInstance(Operation.PRODUCT))
                addToBackStack(null)
            }.commit()
        }

        divideBtn.setOnClickListener {

            val activity = activity ?: return@setOnClickListener

            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, ComputationFragment.newInstance(Operation.DIVISE))
                addToBackStack(null)
            }.commit()
        }
    }
}