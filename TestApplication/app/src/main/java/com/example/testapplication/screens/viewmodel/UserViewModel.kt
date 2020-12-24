package com.example.testapplication.screens.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class UserViewModel : ViewModel() {
    var mAge: MutableLiveData<String> = MutableLiveData()
    var mHeight: MutableLiveData<String> = MutableLiveData()
    var mWeight: MutableLiveData<String> = MutableLiveData()

    fun setAge(age: String) {
        mAge.value = age
    }

    fun setWeight(weight: String) {
        mWeight.value = weight
    }

    fun setHeight(height: String) {
        mHeight.value = height
    }
}