package com.dhl.genricdemo.utils

import android.app.Application
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Second way
 */
class TimerToast(application: Application, lifecycleOwner: LifecycleOwner) : LifecycleObserver {
    private var isStarted = false
    private val lifeCycle = lifecycleOwner.lifecycle

    init {
        lifeCycle.addObserver(this)
    }

    private val timer = object : CountDownTimer(60000, 3000) {
        override fun onFinish() {
            if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                Toast.makeText(application, "Finish", Toast.LENGTH_SHORT).show()
        }

        override fun onTick(millisUntilFinished: Long) {
            if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                Toast.makeText(application, "$millisUntilFinished", Toast.LENGTH_SHORT).show()
        }
    }


    fun start() {
        if (!isStarted) {
            timer.start()
            isStarted = true
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        timer.cancel()
    }
}

/*
class TimerToast(application: Application) {
    private val timer = object : CountDownTimer(60000, 3000) {
        override fun onFinish() {
            Toast.makeText(application, "Finish", Toast.LENGTH_SHORT).show()
        }

        override fun onTick(millisUntilFinished: Long) {
            Toast.makeText(application, "$millisUntilFinished", Toast.LENGTH_SHORT).show()
        }
    }

    fun start() {
        timer.start()
    }


    fun stop() {
        timer.cancel()
    }
}*/
