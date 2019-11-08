package dev.iamfoodie.sleeptracker2.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import dev.iamfoodie.sleeptracker2.R
import dev.iamfoodie.sleeptracker2.databinding.FragmentSleepQualityBinding

class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_sleep_quality,
                container,
                false
        )

        return binding.root
    }


}
