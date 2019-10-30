package dev.foodie.guessit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import dev.foodie.guessit.R
import dev.foodie.guessit.databinding.FragmentScoreBinding
import dev.foodie.guessit.viewmodels.ScoreViewModel
import dev.foodie.guessit.viewmodels.ScoreViewModelFactory

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding: FragmentScoreBinding = DataBindingUtil.inflate(
               inflater,
               R.layout.fragment_score,
               container,
               false
       )
       viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments).score)
       viewModel = ViewModelProviders.of(this, viewModelFactory)
           .get(ScoreViewModel::class.java)

        val args = ScoreFragmentArgs.fromBundle(arguments)
        binding.scoreText.text = args.score.toString()

        binding.playAgainButton.setOnClickListener {
            it.findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
        }

        return binding.root
    }


}
