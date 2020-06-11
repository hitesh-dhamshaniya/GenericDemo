package com.dhl.genricdemo.screens

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.dhl.genricdemo.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        btn_press_me.setOnClickListener {
            animationStart()
        }
    }

    private fun animationStart() {
        ValueAnimator.ofFloat(0f, 3600f).apply {
            repeatMode = REVERSE //ValueAnimator.RESTART
            repeatCount = INFINITE
            interpolator = AccelerateDecelerateInterpolator()
            duration = 10000
            addUpdateListener {
                val progress = it.animatedValue as Float
//                Log.e("Under Animation ", "==>  $progress")
//                text_happy.rotationX = progress
                text_happy.rotationY = progress
                view_fan.rotation = progress
                text_arrow.rotationY = progress
                view_yatch.translationX = progress / 10
                view_hotair.translationY = progress / 10
                btn_press_me.translationZ = progress / 50
                view_cloud.alpha = (3600 - progress) / 3600
                view_sun.scaleX = progress / 3600 + 1
                view_sun.scaleY = progress / 3600 + 1
            }
        }
            .start()
    }
}