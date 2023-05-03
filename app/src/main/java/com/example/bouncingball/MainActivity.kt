package com.example.bouncingball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private var isRunning = false
    private var speed = 50L // L means long type

    lateinit var ballView: BouncingBallView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ballView = findViewById(R.id.ball_view)
        // make it full screen -- optional
        // viewFullScreen()
    }


    fun startBouncingClick(view: View) {

        if (isRunning) {
            Toast.makeText(this, "it has already started", Toast.LENGTH_SHORT).show()
            return
        }

        isRunning = true
        Thread{
            while (true) {
                if(!isRunning) {
                    return@Thread
                }
                ballView.move()
                Thread.sleep(speed)
            }
        }.start()
    }

    fun stopBouncingClick(view: View) {
        isRunning = false
    }

    fun resetButton(view: View) {
        ballView.reset()
    }


    fun increaseSpeed(view: View) {
        if (speed > 10){
            speed -= 10
        }
        else{
            Toast.makeText(this, "It is fast enough!", Toast.LENGTH_SHORT).show()
        }
    }

    fun decreaseSpeed(view: View) {
        speed += 10
    }


    // A helper function to hide navbar and status bar
    private fun viewFullScreen() {
        // Hide the status bar.
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        window.decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)

        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
    }


    override fun onStop() {
        super.onStop()
        isRunning = false
    }
}
