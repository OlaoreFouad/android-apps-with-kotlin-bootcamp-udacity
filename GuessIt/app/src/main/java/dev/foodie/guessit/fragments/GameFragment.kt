package dev.foodie.guessit.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import dev.foodie.guessit.R
import dev.foodie.guessit.databinding.FragmentGameBinding
import dev.foodie.guessit.viewmodels.GameViewModel


class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game,
                container,
                false
        )

        Log.i("GameFragment", "GameViewModel created!")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.gotItButton.setOnClickListener { viewModel.onCorrect() }
        binding.skipButton.setOnClickListener { viewModel.onSkip() }

        viewModel.score.observe(this, Observer { binding.currentScoreText.text = "Current score is: ${ it }" })
        viewModel.word.observe(this, Observer { binding.wordText.text = "\"${ it }\"" })
        viewModel.timerPoint.observe(this, Observer {
            binding.timerText.text = "00:${ if (it < 10) "0$it" else it }"
        })
        viewModel.gameState.observe(this, Observer {
            if (it) {
                gameFinished(viewModel.score.value ?: 0)
                viewModel.onGameFinished()
            }
        })

        return binding.root
    }

    private fun gameFinished(score: Int) {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(score)
        this.findNavController().navigate(action)
    }


}
