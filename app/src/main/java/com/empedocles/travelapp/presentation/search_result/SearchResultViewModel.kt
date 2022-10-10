package com.empedocles.travelapp.presentation.search_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.SearchAllTravelUseCase
import com.empedocles.travelapp.presentation.detail.DetailState
import com.empedocles.travelapp.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val searchAllTravelUseCase: SearchAllTravelUseCase,
) : ViewModel() {

    private val _pageState = MutableLiveData<SearchResultState>(SearchResultState())
    val pageState: LiveData<SearchResultState> = _pageState

    fun loadAllTravelItem(searchQuery: String) {
        viewModelScope.launch {
            when (val result = searchAllTravelUseCase.invoke(searchQuery)) {
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
                        it.searchResults = result.data ?: listOf()
                    }
                }
            }
        }
    }
}
