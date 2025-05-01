package com.example.pocandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(val getObjectUsecase: GetObjectUsecase) : ViewModel() {
    val TAG = MainViewModel::class.java.simpleName


    private val _objects = MutableStateFlow<List<Product>?>(null)
     val objects: StateFlow<List<Product>?> get() = _objects



    init {
        fetchObjects()
    }

    private fun fetchObjects() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrievedObjects = getObjectUsecase()
            _objects.value=retrievedObjects
        }
    }


    /*val objects = liveData(Dispatchers.IO) {
        val retrievedObjects = getObjectUsecase()
        emit(retrievedObjects)
        }*/


}