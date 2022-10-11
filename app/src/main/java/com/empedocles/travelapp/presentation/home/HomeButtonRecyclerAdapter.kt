package com.empedocles.travelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.databinding.FragmentHomeButtonRecyclerItemBinding

class HomeButtonRecyclerAdapter :
    RecyclerView.Adapter<HomeButtonRecyclerAdapter.ItemHolder>() {

    val buttonList = listOf(
        HomeButtonConstants.FLIGHTS,
        HomeButtonConstants.HOTELS,
        HomeButtonConstants.CARS,
        HomeButtonConstants.TAXI
    )
        class ItemHolder(private val binding : FragmentHomeButtonRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
                fun bind(buttonModel: ButtonModel) {
                    binding.buttonImage.setImageResource(buttonModel.id)
                    binding.buttonText.text = buttonModel.text
                }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val itemBinding = FragmentHomeButtonRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
            return ItemHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            holder.bind(buttonList[position])
        }

        override fun getItemCount(): Int {
            return  buttonList.size
        }
}