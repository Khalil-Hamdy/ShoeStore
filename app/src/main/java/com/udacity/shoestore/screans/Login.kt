package com.udacity.shoestore.screans

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class Login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding :FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        setBackPressedConfiguration()

        binding.buttonSignIn.setOnClickListener{
            val action = LoginDirections.actionLoginFragmentToWelcomeFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.buttonSignUp.setOnClickListener{
            val action = LoginDirections.actionLoginFragmentToWelcomeFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }

    private fun setBackPressedConfiguration() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val i = Intent()
                i.action = Intent.ACTION_MAIN
                i.addCategory(Intent.CATEGORY_HOME)
                startActivity(i)
            }
        })
    }

}