package dev.foodie.dessertpusher

import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import com.example.android.dessertpusher.DessertTimer
import dev.foodie.dessertpusher.databinding.ActivityMainBinding
import timber.log.Timber

const val KEY_REVENUE = "key_revenue"
const val KEY_DESSERTS_SOLD = "key_desserts_sold"
const val KEY_SECONDS_COUNT = "key_seconds_count"

class MainActivity : AppCompatActivity() {

    private var revenue = 0
    private var dessertsSold = 0

    private lateinit var binding: ActivityMainBinding
    private lateinit var dessertTimer: DessertTimer

    data class Dessert(val imageId: Int, val price: Int, val startProductionAmount: Int)
    private val allDesserts = listOf(
        Dessert(R.drawable.cupcake, 30, 50),
        Dessert(R.drawable.donut, 10, 5),
        Dessert(R.drawable.eclair, 15, 20),
        Dessert(R.drawable.froyo, 30, 50),
        Dessert(R.drawable.gingerbread, 50, 100),
        Dessert(R.drawable.honeycomb, 100, 200),
        Dessert(R.drawable.icecreamsandwich, 500, 500),
        Dessert(R.drawable.jellybean, 1000, 1000),
        Dessert(R.drawable.kitkat, 2000, 2000),
        Dessert(R.drawable.lollipop, 3000, 4000),
        Dessert(R.drawable.marshmallow, 4000, 8000),
        Dessert(R.drawable.nougat, 5000, 16000),
        Dessert(R.drawable.oreo, 6000, 20000)
    )

    private var currentDessert = allDesserts[0]

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.i("onCreate called!")

        dessertTimer = DessertTimer(lifecycle)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        savedInstanceState?.apply {
            revenue = getInt(KEY_REVENUE)
            dessertsSold = getInt(KEY_DESSERTS_SOLD)
            val secondsCount = getInt(KEY_SECONDS_COUNT)

            Timber.i("Revenue: $revenue\nDesserts sold: $dessertsSold\n Seconds count: $secondsCount")
        }

//        if (savedInstanceState != null) {

//        }

        binding.revenue = revenue
        binding.amountSold = dessertsSold

        binding.dessertButton.setOnClickListener { onDessertClicked() }

        binding.dessertButton.setImageResource(currentDessert.imageId)
    }

    private fun onDessertClicked() {
        // Update the score
        revenue += currentDessert.price
        dessertsSold++

        binding.revenue = revenue
        binding.amountSold = dessertsSold

        // Show the next dessert
        showCurrentDessert()
    }

    private fun showCurrentDessert() {
        var newDessert = allDesserts[0]
        for (dessert in allDesserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                newDessert = dessert
            }
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            else break
        }

        // If the new dessert is actually different than the current dessert, update the image
        if (newDessert != currentDessert) {
            currentDessert = newDessert
            binding.dessertButton.setImageResource(newDessert.imageId)
        }
    }

    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setText(getString(R.string.share_text, dessertsSold, revenue))
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.share_item -> onShare()
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called!")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("onRestoreInstanceState called!")
        savedInstanceState.apply {
            revenue = getInt(KEY_REVENUE)
            dessertsSold = getInt(KEY_DESSERTS_SOLD)
            val secondsCount = getInt(KEY_SECONDS_COUNT)

            Timber.i("Revenue: $revenue\nDesserts sold: $dessertsSold\n Seconds count: $secondsCount")
        }
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called!")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_REVENUE, revenue)
        outState.putInt(KEY_DESSERTS_SOLD, dessertsSold)
        outState.putInt(KEY_SECONDS_COUNT, dessertTimer.secondsCount)
        Timber.i("onSaveInstance state called!")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called!")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called!")
    }
}
