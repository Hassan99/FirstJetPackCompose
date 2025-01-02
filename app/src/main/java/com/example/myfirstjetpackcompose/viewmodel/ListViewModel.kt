package com.example.myfirstjetpackcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private val _listOfEmployees = MutableLiveData<List<String>>(emptyList())
    val listOfEmployees: LiveData<List<String>> get() = _listOfEmployees

    fun addEmployee(name: String) {
        val currentList = _listOfEmployees.value.orEmpty()
        _listOfEmployees.value = currentList + name
    }
}
