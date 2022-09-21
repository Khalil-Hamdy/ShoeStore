package com.udacity.shoestore.screans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.models.SaveState
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ViewModel


class details : Fragment() {
    private val viewModel: ViewModel by activityViewModels()

    private val shoeData = Shoe("", "", "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this
        binding.shoeData = shoeData

        viewModel.saveState.observe(this as LifecycleOwner, Observer{ ss ->
            when(ss){
                SaveState.SAVE -> {
                    navigateToShoeList()
                    viewModel.onEventSaveComplete()
                }
                else -> {}
            }
        })

        binding.cancel.setOnClickListener {
            val action = detailsDirections.actionDetailsToShoeList2()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }

    private  fun navigateToShoeList() {
        val action = detailsDirections.actionDetailsToShoeList2()
        NavHostFragment.findNavController(this).navigate(action)


    }

}