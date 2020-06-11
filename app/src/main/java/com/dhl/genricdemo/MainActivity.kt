package com.dhl.genricdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhl.genricdemo.adapter.TopicAdapter
import com.dhl.genricdemo.callbacks.TopicClickListener
import com.dhl.genricdemo.datacatch.AppConstants
import com.dhl.genricdemo.screens.AnimationActivity
import com.dhl.genricdemo.screens.KotlinFlowActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        rvTopic.adapter = TopicAdapter(topicClickListener)
    }

    private val topicClickListener = object : TopicClickListener {
        override fun onTopicClick(topic: String) {

            when (topic) {
                AppConstants.KOTLIN_FLOW -> {
                    startActivity(Intent(this@MainActivity, KotlinFlowActivity::class.java))
                }
                AppConstants.APP_ANIMATION -> {
                    startActivity(Intent(this@MainActivity, AnimationActivity::class.java))
                }
            }

        }
    }
}