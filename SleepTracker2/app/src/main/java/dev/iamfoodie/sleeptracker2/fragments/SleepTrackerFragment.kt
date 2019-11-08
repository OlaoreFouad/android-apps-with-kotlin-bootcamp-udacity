package dev.iamfoodie.sleeptracker2.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import dev.iamfoodie.sleeptracker2.R
import dev.iamfoodie.sleeptracker2.databases.SleepNightDatabase
import dev.iamfoodie.sleeptracker2.databinding.FragmentSleepTrackerBinding
import dev.iamfoodie.sleeptracker2.viewmodels.SleepTrackerViewModel
import dev.iamfoodie.sleeptracker2.viewmodels.SleepTrackerViewModelFactory

class SleepTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_sleep_tracker,
                container,
                false
        )

        val dataSource = SleepNightDatabase.getInstance(context!!).sleepDatabaseDao
        val application = requireNotNull(this.activity).application

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }


}
