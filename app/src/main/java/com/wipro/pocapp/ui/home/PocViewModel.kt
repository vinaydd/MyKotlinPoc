package com.wipro.pocapp.ui.home

import androidx.lifecycle.ViewModel
import com.doctoremr.data.repositories.PocRepository

class PocViewModel(
    private val repository: PocRepository
) : ViewModel() {

    suspend fun getAllergiesTypeList(
    ) = repository.getAllergiesList()

}