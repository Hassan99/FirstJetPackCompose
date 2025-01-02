package com.example.myfirstjetpackcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val counter = MutableLiveData<Int>()

    init {
        counter.value = 0
    }

    var increment = {
        counter.value = counter.value?.plus(1)
    }
    var decrement = {
        counter.value = counter.value?.minus(1)
    }
}