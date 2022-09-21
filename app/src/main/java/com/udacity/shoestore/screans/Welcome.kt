package com.udacity.shoestore.screans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class Welcome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_welcome, container, false)

        binding.nextButton.setOnClickListener{
            val action = WelcomeDirections.actionWelcomeFragmentToInstructions()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }

}