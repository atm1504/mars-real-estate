package com.atm1504.marsrealestate.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.atm1504.marsrealestate.R
import com.atm1504.marsrealestate.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
   // private lateinit var binding: FragmentOverviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val binding = FragmentOverviewBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        val viewModelFactory = OverviewViewModelFactory(application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(OverviewViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}