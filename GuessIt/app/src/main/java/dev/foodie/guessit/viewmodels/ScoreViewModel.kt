package dev.foodie.guessit.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    val score: Int = finalScore
}