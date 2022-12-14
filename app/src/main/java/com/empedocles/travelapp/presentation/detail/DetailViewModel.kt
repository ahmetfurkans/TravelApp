package com.empedocles.travelapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import com.empedocles.travelapp.domain.usecase.BookMarkUseCase
import com.empedocles.travelapp.domain.usecase.SingleTravelItemUseCase
import com.empedocles.travelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val singleTravelItemUseCase: SingleTravelItemUseCase,
    private val allTravelItemUseCase: AllTravelItemUseCase,
    private val bookMarkUseCase: BookMarkUseCase
) : ViewModel() {

    private val _pageState = MutableLiveData<DetailState>(DetailState())
    val pageState: LiveData<DetailState> = _pageState

    fun bookMarkHandler(id: String, isBookmark: Boolean) {
        viewModelScope.launch {
            when (val result = bookMarkUseCase.changeBookMark(
                id, isBookmark
            )) {
                is Resource.Success -> {
                    allTravelItemUseCase.apply {
                        getAllTravelItem()
                        allTravelList.value?.find { it.id == id }?.isBookmark = isBookmark
                    }
                }
                else -> {
                    _pageState.value?.isError = true
                }
            }
        }
    }


    fun loadSelectedItem(id: String) {
        viewModelScope.launch {
            when (val result = singleTravelItemUseCase.invoke(id)) {
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
                        it.selectedItem = result.data
                    }
                }
            }
        }
    }
}

