package com.doctoremr.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _error = MutableLiveData<ErrorWrapper>()
    val error: LiveData<ErrorWrapper>
        get() = _error
}