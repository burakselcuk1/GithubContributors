package com.example.contributors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contributors.databinding.ContributorItemBinding
import com.example.contributors.model.ContributorsResponse



class ContributorAdapter(private val dataSet: ContributorsResponse) :
    RecyclerView.Adapter<ContributorAdapter.ViewHolder>() {
    private lateinit var binding: ContributorItemBinding

    class ViewHolder(view: ContributorItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ContributorItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val usersResponse  = dataSet.get(position)

        binding.usersInformation = usersResponse.author

    }
    override fun getItemCount() = dataSet.size

}