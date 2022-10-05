package com.empedocles.travelapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AllTravelItemRepository
) :ViewModel() {
    private val _allTravelList = MutableLiveData<List<TravelModel>>()
    val allTravelList: LiveData<List<TravelModel>> = _allTravelList

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hotels = MutableLiveData<List<TravelModel>>()
    val hotels: LiveData<List<TravelModel>> = _hotels

    private val _flights = MutableLiveData<List<TravelModel>>()
    val flights: LiveData<List<TravelModel>> = _flights

    private val _transportation = MutableLiveData<List<TravelModel>>()
    val transportation: LiveData<List<TravelModel>> = _transportation

    init {
        getAllTravelItem()
    }

    private fun getAllTravelItem(
    ) {
        viewModelScope.launch {
            when(val result = repository.getAllTravelItem()) {
                is Resource.Error -> {
                    _isLoading.value = false
                    _error.value = true
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                    _error.value = false
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _error.value = false
                    result.data?.let { data ->
                        _allTravelList.value = data
                        _hotels.value = data.filter { it.category == "hotel" }
                        _flights.value = data.filter { it.category == "flight" }
                        _transportation.value = data.filter { it.category == "transportation" }
                    }
                }
            }
        }
    }
}