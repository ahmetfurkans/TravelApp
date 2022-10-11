package com.empedocles.travelapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.empedocles.travelapp.data.local.TripDatabase
import com.empedocles.travelapp.data.local.TripEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTravelItemFromRoomUseCase @Inject constructor(
    private val database: TripDatabase
) {

    private var _allTravelList = MutableLiveData<List<TripEntity>>()
    val allTravelList: LiveData<List<TripEntity>> = _allTravelList

    fun getAllTravelItem(
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            _allTravelList.postValue(database.dao.getAll())
        }
    }
}