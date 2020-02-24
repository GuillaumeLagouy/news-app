package com.glagouy.dmii.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glagouy.dmii.Operation

class ComputeViewModel: ViewModel() {
    private val _resultatLiveData = MutableLiveData<Double>()
    val resultatLiveData: LiveData<Double>
        get() = _resultatLiveData

    fun compute(nb1:Double, nb2:Double, operation:Operation){

        _resultatLiveData.value = when(operation){
            Operation.SUM -> nb1 + nb2
            Operation.MINUS -> nb1 - nb2
            Operation.PRODUCT -> nb1 * nb2
            Operation.DIVISE -> nb1 / nb2
            Operation.PERCENT -> nb1 % nb2
        }
    }
}