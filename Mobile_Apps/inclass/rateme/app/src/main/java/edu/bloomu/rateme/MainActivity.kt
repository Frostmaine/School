package edu.bloomu.rateme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import java.util.*

/**
 * Displays a toast in response to a button click and logs a timestamp
 * when transitioning from one activity state to another.
 *
 * @author Matthew Yackiel
 */
class MainActivity : AppCompatActivity() {

    private val logTag = "LifeCycleEvent"

    // The old Date class is used to create a time stamp because LocalDateTime
    // requires Android API level 26 1 higher than 25 nougat (the tablet)
    private val timeOfCreation: String = // time stamp code
        Date().toString().split(" ")[3]; // hh:mm:ss

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val msg = String.format("CREATED (created at %s)", timeOfCreation)
        Log.d(logTag, msg)
    }

    override fun onStart() {
        super.onStart()
        val msg = String.format("STARTED (created at %s)", timeOfCreation)
        Log.d(logTag, msg)
    }

    override fun onResume() {
        super.onResume()
        val msg = String.format("RESUMED (created at %s)", timeOfCreation)
        Log.d(logTag, msg)
    }

    override fun onPause() {
        super.onPause()
        val msg = String.format("PAUSED (created at %s)", timeOfCreation)
        Log.d(logTag, msg)
    }

    override fun onStop() {
        super.onStop()
        val msg = String.format("STOPPED (created at %s)", timeOfCreation)
        Log.d(logTag, msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        val msg = String.format("DESTROYED (created at %s)", timeOfCreation)
        Log.d(logTag, msg)
    }

    /**
     * Event Handler for button. Displays toast with text customized
     * according to the number of stars selected in rating bar.
     */
    fun rate(view: View) {
        val rb = findViewById<RatingBar>(R.id.ratingBar)
        val i = rb.rating.toInt()
        val msg = arrayOf(
            "Your rating is not valid and reflects poorly on you",
            "Your rating is not fair. You are heartless.",
            "Your rating is not accurate. Try again.",
            "Ok, but the app is better than you realize",
            "That is the correct rating. Good job.",
            "Oh you are too kind!"
        )
        Toast.makeText(applicationContext, msg[i], Toast.LENGTH_LONG).show()
    }
}

/*
Instructions for tablet.
1. go to settings
2. about tablet
  -  Phone info: tap build number 7 times
3. enable debugging
 settings > developer options > usb debugging on
 */