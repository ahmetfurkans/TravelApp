package com.empedocles.travelapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import com.empedocles.travelapp.presentation.home.HomeState
import com.empedocles.travelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase
) : ViewModel() {
    private val _pageState = MutableLiveData<SearchState>(SearchState())
    val pageState: LiveData<SearchState> = _pageState

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
                        it.nearby =
                            result.data?.filter { item -> item.category == "nearby" } ?: emptyList()
                        it.topDestination =
                            result.data?.filter { item -> item.category == "topdestination" }
                                ?: emptyList()
                    }
                }
            }
        }
    }
}