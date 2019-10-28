package dev.foodie.guessit.viewmodels

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

//    Variables for score and current word.
    var word = MutableLiveData<String>()
    var score = MutableLiveData<Int>()
    var gameState = MutableLiveData<Boolean>()

    private lateinit var wordsList: MutableList<String>

    init {
        Log.i("GameFragment", "GameFragment created for the first time!!!")
        resetList()
        nextWord()
        score.value = 0
        word.value = ""
        gameState.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameFragment", "GameFragment destroyed!")
    }

    private fun resetList() {
        wordsList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordsList.shuffle()
    }

    private fun nextWord() {
        if (wordsList.isEmpty()) {
            gameState.value = true
        } else {
            word.value = wordsList.removeAt(0)
        }
    }

    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }

}