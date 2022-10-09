package com.empedocles.travelapp.presentation.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import com.empedocles.travelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase
) : ViewModel() {

    private val _pageState = MutableLiveData<TripState>(TripState())
    val pageState: LiveData<TripState> = _pageState

    init {
        loadAllTravelItem()
    }

    private fun loadAllTravelItem(
    ) {
        viewModelScope.launch {
            when (val result = allTravelItemUseCase.invoke()) {
                is Resource.Error -> {
                    _pageState.value = _pageState.value?.also {
                        it.isLoading = false
                        it.isError = false
                    }
                }
                is Resource.Loading -> {
                    _pageState.value = _pageState.value?.also {
                        it.isLoading = true
                    }
                }
                is Resource.Success -> {
                    _pageState.value = _pageState.value?.also {
                        it.isLoading = false
                        it.isError = false
                        it.trip = result.data ?: emptyList()
                        it.bookmark =
                            result.data?.filter { item -> item.isBookmark } ?: emptyList()
                    }
                }
            }
        }
    }
}