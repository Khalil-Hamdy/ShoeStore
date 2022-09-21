package com.udacity.shoestore.screans

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemDataBinding
import com.udacity.shoestore.models.ViewModel
import timber.log.Timber


class ShoeList : Fragment() {
    private val viewModel: ViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel.shoeList.observe(this as LifecycleOwner, Observer {

            for (shoe in viewModel.shoeList.value!!) {
                val inBinding = ItemDataBinding.inflate(layoutInflater)
                inBinding.shoeData = shoe
                binding.list.addView(inBinding.root)
            }
        })

        binding.buttonAdd.setOnClickListener {
            Timber.i("in addShoeButton listener")
            Timber.i(viewModel.shoeList.value?.joinToString(separator = "\n"))
            val action = ShoeListDirections.actionShoeListToDetails()
            NavHostFragment.findNavController(this).navigate(action)
        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val action = ShoeListDirections.actionShoeListToLoginFragment()
                findNavController().navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


       return when (item.itemId) {
            R.id.logout -> {
                val action = ShoeListDirections.actionShoeListToLoginFragment()
                NavHostFragment.findNavController(this).navigate(action)
                true
            }
           else -> super.onOptionsItemSelected(item)
       }
    }

}