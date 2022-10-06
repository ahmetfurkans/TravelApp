package com.empedocles.travelapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import com.empedocles.travelapp.presentation.search.SearchState
import com.empedocles.travelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase
) : ViewModel() {
    private val _pageState = MutableLiveData<GuideState>(GuideState())
    val pageState: LiveData<GuideState> = _pageState

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
                        it.toppick =
                            result.data?.filter { item -> item.category == "toppick" } ?: emptyList()
                        it.mightneed =
                            result.data?.filter { item -> item.category == "mightneed" }
                                ?: emptyList()
                    }
                }
            }
        }
    }
}