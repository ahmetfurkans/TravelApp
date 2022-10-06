package com.empedocles.travelapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import com.empedocles.travelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase
) : ViewModel() {

    private val _pageState = MutableLiveData<HomeState>(HomeState())
    val pageState: LiveData<HomeState> = _pageState

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
                        it.allTravelItem = result.data ?: emptyList()
                        it.hotels =
                            result.data?.filter { item -> item.category == "hotel" } ?: emptyList()
                        it.flights =
                            result.data?.filter { item -> item.category == "flight" } ?: emptyList()
                        it.transportation =
                            result.data?.filter { item -> item.category == "transportation" }
                                ?: emptyList()
                    }
                }
            }
        }
    }
}