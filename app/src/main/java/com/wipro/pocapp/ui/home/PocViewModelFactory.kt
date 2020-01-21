package com.wipro.pocapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doctoremr.data.repositories.PocRepository

@Suppress("UNCHECKED_CAST")
class PocViewModelFactory(
    private val repository: PocRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PocViewModel(repository) as T
    }

}