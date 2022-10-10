package com.empedocles.travelapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import com.empedocles.travelapp.domain.usecase.BookMarkUseCase
import com.empedocles.travelapp.presentation.home.HomeState
import com.empedocles.travelapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase,
    private val bookMarkUseCase: BookMarkUseCase
) : ViewModel() {
    private val _pageState = MutableLiveData<SearchState>(SearchState())
    val pageState: LiveData<SearchState> = _pageState

    fun loadAllTravelItem(
    ) : LiveData<List<TravelModel>> {
        allTravelItemUseCase.apply {
            getAllTravelItem()
            return allTravelList
        }
    }

    fun bookMarkHandler(id: String, isBookmark: Boolean) {
        viewModelScope.launch {
            when (val result = bookMarkUseCase.changeBookMark(
                id, isBookmark)){
                is Resource.Success -> {
                    allTravelItemUseCase.apply {
                        getAllTravelItem()
                        allTravelList.value?.find { it.id == id }?.isBookmark = isBookmark
                    }
                }
                else -> {
                    println(result.message)
                }
            }
        }
    }
}