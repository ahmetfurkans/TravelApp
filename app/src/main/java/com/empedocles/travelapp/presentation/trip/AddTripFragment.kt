package com.empedocles.travelapp.presentation.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.empedocles.travelapp.data.local.TripEntity
import com.empedocles.travelapp.databinding.FragmentAddTripBinding
import com.empedocles.travelapp.domain.model.TravelModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTripFragment : DialogFragment() {
    private val viewModel by viewModels<AddTripFragmentViewModel>()
    private lateinit var binding: FragmentAddTripBinding
    private var cityNameList = arrayListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTripBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createUi()
        observeLiveData()
    }

    private fun createUi(){
        binding.cancelButton.setOnClickListener{
            this.dismiss()
        }
        binding.addButton.setOnClickListener{
            var selectedTravelModel : TravelModel? = null
            viewModel.loadAllTravelItem().value?.let {
                selectedTravelModel = it.firstOrNull{ travelModel -> travelModel.city == binding.autoTextView.text.toString() }
            }
            if(selectedTravelModel == null){
                Toast.makeText(requireContext(), "We couldn't find city in our database", Toast.LENGTH_SHORT).show()
            }else{
                selectedTravelModel?.let {
                    viewModel.addTrip(
                        TripEntity(
                            selectedTravelModel!!.id,
                            selectedTravelModel!!.city,
                            selectedTravelModel!!.images.count(),
                            selectedTravelModel!!.images.get(1).url,
                            binding.startDate.drawingTime,
                            binding.endDate.drawingTime
                        )
                    )
                }
                Toast.makeText(requireContext(),  "Trip added to database", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.loadAllTravelItem().observe(viewLifecycleOwner) {
            cityNameList = it.map { it.city } as ArrayList<String>
            adapter = ArrayAdapter<String>(
                requireContext().applicationContext,
                android.R.layout.simple_list_item_1, cityNameList
            )
            binding.autoTextView.setAdapter(adapter)

        }
    }

}