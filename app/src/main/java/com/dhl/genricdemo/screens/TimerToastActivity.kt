package com.dhl.genricdemo.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhl.genricdemo.R
import com.dhl.genricdemo.utils.TimerToast
import kotlinx.android.synthetic.main.activity_timer_toast.*

/**
 * This activity demonstrate use of LifeCycle owner importants
 * There are two problem we face if we remove life cycle owner, one of them we can handle without life cycle owner
 * 1. When application goes in background, toast will still visible
 * 2. Even when user press back button than also user get toast on dashboard
 *
 * We can handle second case without LifeCycle owner by over ride on destroy method of activity and stop timer. However there may be solution
 * for first problem as well but it might be difficult and long
 */
class TimerToastActivity : AppCompatActivity() {

    private lateinit var mTimerToast: TimerToast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_toast)
        mTimerToast = TimerToast(application, this)
        initUI()
    }

    private fun initUI() {
        btnTimerToast.setOnClickListener {
            mTimerToast.start()
        }
    }

    /**
     * Uncomment for first method, when use life cycle owner activity don't need onDestroy
     */
    /*override fun onDestroy() {
        super.onDestroy()
        mTimerToast.stop()
    }*/
}