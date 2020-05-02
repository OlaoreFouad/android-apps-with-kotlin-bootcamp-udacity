package dev.olaore.realestateonmars.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import dev.olaore.realestateonmars.R
import dev.olaore.realestateonmars.databinding.FragmentDetailsBinding
import dev.olaore.realestateonmars.viewmodels.DetailsViewModel
import dev.olaore.realestateonmars.viewmodels.DetailsViewModelFactory

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        val property = DetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty

//        create the viewmodel factory
        val detailsViewModelFactory = DetailsViewModelFactory(property, activity?.application!!)

//        create the viewmodel
        val detailsViewModel = ViewModelProviders.of(
            this, detailsViewModelFactory
        ).get(DetailsViewModel::class.java)

    }

}
