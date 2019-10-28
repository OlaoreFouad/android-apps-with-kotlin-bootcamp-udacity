package dev.foodie.guessit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import dev.foodie.guessit.R
import dev.foodie.guessit.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_title,
                container,
                false
        )

        binding.playButton.setOnClickListener {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragment2ToGameFragment())
        }

        return binding.root
    }


}
