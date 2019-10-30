package dev.foodie.guessit.viewmodels

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

//    Variables for score and current word.
    var word = MutableLiveData<String>()
    var score = MutableLiveData<Int>()
    var gameState = MutableLiveData<Boolean>()
    var timerPoint = MutableLiveData<Int>()

    companion object {
        val NUM_OF_SECONDS = 60000L
        val INTERVAL = 1000L
    }

    private lateinit var wordsList: MutableList<String>
    private var timer: CountDownTimer

    init {
        Log.i("GameFragment", "GameFragment created for the first time!!!")
        resetList()
        nextWord()
        score.value = 0
        gameState.value = false
        timerPoint.value = 60

        timer = object : CountDownTimer(NUM_OF_SECONDS, INTERVAL) {
            override fun onFinish() {
                gameState.value = true
            }

            override fun onTick(p0: Long) {
                timerPoint.value = p0.div(1000).toInt()
            }
        }
        timer.start()
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
            resetList()
        }
        word.value = wordsList.removeAt(0)
    }

    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }

    // function that resets the value held by the gameFinished live data to false, once it has been set to true.
    // This is so it happens once

    fun onGameFinished() {
        gameState.value = false
    }
}