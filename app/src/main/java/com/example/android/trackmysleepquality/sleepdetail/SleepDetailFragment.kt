package com.example.android.trackmysleepquality.sleepdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SleepDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding:FragmentSleepDetailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sleep_detail, container,false)

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(arguments!!)

        /**
         *  create instance for the ViewModelFactory
         */
            // get the Datasource
        val dataSource =SleepDatabase.getInstance(application).sleepDatabaseDao
            // instantiate the viewModelFactory
        val viewModelFactory = SleepDetailViewModelFactory(arguments.sleepNightKey,dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val sleepDetailViewModel = ViewModelProvider(this,
            viewModelFactory).get(SleepDetailViewModel::class.java)

        //tie the viewModel to binding
        binding.sleepDetailViewModel = sleepDetailViewModel

        binding.lifecycleOwner = this

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if(it == true){
                this.findNavController().navigate(SleepDetailFragmentDirections
                    .actionSleepDetailFragmentToSleepTrackerFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                sleepDetailViewModel.doneNavigating()
            }
        })
        return binding.root
    }

}