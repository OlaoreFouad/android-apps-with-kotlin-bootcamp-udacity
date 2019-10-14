package dev.foodie.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: dev.foodie.aboutme.databinding.ActivityMainBinding

    private val myName = MyName("Fouad Olaore")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        done_button.setOnClickListener { addNickname(it) }
        binding.bioText.setTextColor(resources.getColor(android.R.color.holo_red_dark))
    }

    fun addNickname(v: View) {
        val nickname = nickname_edit_text.text
        myName?.nickname = nickname.toString()

        binding.apply {
            doneButton.visibility = View.GONE
            nicknameEditText.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        done_button.visibility = View.GONE
        nickname_edit_text.visibility = View.GONE
        nickname_text.text = nickname
        nickname_text.visibility = View.VISIBLE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    companion object {

        data class MyName(var name: String = "", var nickname: String = "")

    }
}
