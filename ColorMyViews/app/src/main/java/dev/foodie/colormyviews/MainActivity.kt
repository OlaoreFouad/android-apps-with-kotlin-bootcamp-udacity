package dev.foodie.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        val clickableViews: List<View> = listOf(box_one, box_two_text, box_three_text, box_four_text, box_five_text, constraint_layout, red_button,
            green_button, yellow_button)

        clickableViews.forEach {
            it.setOnClickListener { view ->
                    makeColored(view)
            }
        }
    }

    private fun makeColored(it: View) {
        when(it.id) {
            R.id.box_one -> it.setBackgroundColor(Color.BLUE)
            R.id.box_two_text -> it.setBackgroundColor(Color.YELLOW)
            R.id.box_three_text -> it.setBackgroundColor(Color.RED)
            R.id.box_four_text -> it.setBackgroundResource(android.R.color.holo_orange_dark)
            R.id.box_five_text -> it.setBackgroundResource(android.R.color.holo_purple)
            R.id.red_button -> box_three_text.setBackgroundColor(resources.getColor(R.color.colorRed))
            R.id.yellow_button -> box_four_text.setBackgroundColor(resources.getColor(R.color.colorYellow))
            R.id.green_button -> box_five_text.setBackgroundColor(resources.getColor(R.color.colorGreen))
            else -> it.setBackgroundColor(Color.LTGRAY)
        }
    }
}
