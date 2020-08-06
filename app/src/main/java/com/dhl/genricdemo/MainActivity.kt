package com.dhl.genricdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhl.genricdemo.adapter.TopicAdapter
import com.dhl.genricdemo.callbacks.TopicClickListener
import com.dhl.genricdemo.datacatch.AppConstants
import com.dhl.genricdemo.screens.AccessibilityOnOffActivity
import com.dhl.genricdemo.screens.AnimationActivity
import com.dhl.genricdemo.screens.KotlinFlowActivity
import com.dhl.genricdemo.screens.TimerToastActivity
import com.dhl.genricdemo.tools.AppReviewManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val APP_REVIEW_ACTIVITY_CALLBACK = 1601
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        rvTopic.adapter = TopicAdapter(topicClickListener)
        AppReviewManager().init(this)
    }

    private val topicClickListener = object : TopicClickListener {
        override fun onTopicClick(topic: String) {

            when (topic) {
                AppConstants.KOTLIN_FLOW -> {
                    startActivityForResult(Intent(this@MainActivity, KotlinFlowActivity::class.java), APP_REVIEW_ACTIVITY_CALLBACK)
                }
                AppConstants.APP_ANIMATION -> {
                    startActivity(Intent(this@MainActivity, AnimationActivity::class.java))
                }
                AppConstants.ACCESSIBILITY -> {
                    startActivity(Intent(this@MainActivity, AccessibilityOnOffActivity::class.java))
                }
                AppConstants.TIMER_TOAST -> {
                    startActivity(Intent(this@MainActivity, TimerToastActivity::class.java))
                }
            }

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == APP_REVIEW_ACTIVITY_CALLBACK && resultCode == Activity.RESULT_OK) {
            AppReviewManager().handleResult(this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}