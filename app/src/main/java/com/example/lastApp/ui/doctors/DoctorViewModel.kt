package com.example.lastApp.ui.doctors

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.lastApp.data.repository.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(private val doctorRepository: DoctorRepository) :
    ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val doctors = currentQuery.switchMap { queryString ->
        doctorRepository.getDoctors(queryString).cachedIn(viewModelScope)
    }

    fun getDoctors(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "أسيوط"
    }
}

