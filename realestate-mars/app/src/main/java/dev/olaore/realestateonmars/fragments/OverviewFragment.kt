package dev.olaore.realestateonmars.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import dev.olaore.realestateonmars.PhotoGridAdapter

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

        binding.lifecycleOwner = this
        binding.propertiesList.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.onPropertySelected(it)
        })

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(it)
        })

    }

}
