package com.example.lab8

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.pow
import kotlin.math.sqrt

object CircleDrawer {

    @SuppressLint("ClickableViewAccessibility")
    fun drawCircle(layout: ConstraintLayout, img: ImageView) {
        val x = Array(2) { 0.0f }
        val y = Array(2) { 0.0f }
        layout.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.pointerCount == 2) {
                val paint = Paint().apply {
                    Paint.Style.STROKE
                    strokeWidth = 1.0f
                }
                for (i in 0 until motionEvent.pointerCount) {
                    val ids = motionEvent.getPointerId(i)
                    x[ids] = motionEvent.getX(i)
                    y[ids] = motionEvent.getY(i)
                }
                val r = sqrt(
                    (x[1] - x[0]).pow(2.0f) + (y[1] - y[0]).pow(2.0f)
                ) / 2.0f
                when (motionEvent.actionMasked) {
                    MotionEvent.ACTION_MOVE -> {
                        val bmp = Bitmap.createBitmap(
                            layout.width,
                            layout.height,
                            Bitmap.Config.ARGB_8888
                        )
                        val canvas = Canvas(bmp)
                        canvas.drawCircle(
                            ((x[0] + x[1]) / 2.0f),
                            ((y[0] + y[1]) / 2.0f), r, paint
                        )
                        img.setImageBitmap(bmp)
                    }
                    MotionEvent.ACTION_POINTER_UP -> {
                        img.setImageBitmap(null)
                    }
                }
            }
            true
        })
    }
}
