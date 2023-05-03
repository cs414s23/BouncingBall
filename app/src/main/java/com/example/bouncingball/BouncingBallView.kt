package com.example.bouncingball

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class BouncingBallView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "BouncingBallView"

    // Variables for controlling bouncing ball
    private val SIZE = 100f
    private var ballX = 0f
    private var ballY = 0f
    private var dx = 15f
    private var dy = 9f

    // Point object for color
    private val p = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return


        // Draw a blue oval shape
        p.color = Color.BLUE
        val oval = RectF(ballX,ballY,ballX + SIZE,ballY + SIZE)
        canvas.drawOval(oval, p)
    }

    fun move(){

        // Update x coordinate
        ballX += dx

        // Check to see the ball hits the edges of the screen
        // If so, horizontally bounce
        if (ballX + SIZE >= width || ballX <= 0){
            dx = -dx
        }

        // Update y coordinate
        ballY += dy

        // Check to see the ball hits the edges of the screen
        // If so, vertically bounce
        if (ballY + SIZE >= height || ballY <= 0){
            dy = -dy
        }

        // Re-draw the screen so that we can see moving objects
        postInvalidate()
    }

    fun reset(){
        ballX = 0f
        ballY = 0f
        dx = 15f
        dy = 9f

        // Re-draw the screen
        postInvalidate()
    }
}