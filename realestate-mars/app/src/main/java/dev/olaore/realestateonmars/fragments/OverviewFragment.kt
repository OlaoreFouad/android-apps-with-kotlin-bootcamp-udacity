package dev.olaore.realestateonmars.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import dev.olaore.realestateonmars.R
import dev.olaore.realestateonmars.databinding.FragmentOverviewBinding
import dev.olaore.realestateonmars.viewmodels.OverviewViewModel

/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater, container ,false)
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel?.response?.observe(viewLifecycleOwner, Observer {
            binding.apiResponseText.text = it
        })
    }

}
