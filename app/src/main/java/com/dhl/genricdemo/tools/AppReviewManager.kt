package com.dhl.genricdemo.tools

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory

class AppReviewManager {
    private val TAG: String = AppReviewManager::class.java.simpleName
    private var reviewInfo: ReviewInfo? = null
    private lateinit var reviewManager: ReviewManager
    fun init(context: Context) {
        reviewManager = ReviewManagerFactory.create(context)
        //Request a ReviewInfo object ahead of time (Pre-cache)
        val requestFlow = reviewManager.requestReviewFlow()
        requestFlow.addOnCompleteListener { request ->
            reviewInfo = if (request.isSuccessful) {
                //Received ReviewInfo object
                Log.e(TAG, "Review request successful")
                request.result
            } else {
                Log.e(TAG, "Review request failure")
                //Problem in receiving object
                null
            }
        }
    }

    fun handleResult(activity: Activity) {
        Log.e(TAG, "In handle result")
        Handler().postDelayed({
            reviewInfo?.let {
                val flow = reviewManager.launchReviewFlow(activity, it)
                //Showing toast is only for testing purpose, this shouldn't be implemented in production app.
                flow.addOnSuccessListener {
                    Toast.makeText(
                        activity, "Thanks for the feedback!", Toast.LENGTH_LONG
                    ).show()
                }
                flow.addOnFailureListener {
                    Toast.makeText(activity, "${it.message}", Toast.LENGTH_LONG).show()
                }
                flow.addOnCompleteListener {
                    Toast.makeText(activity, "Completed!", Toast.LENGTH_LONG).show()
                }
            }
        }, 3000)
    }
}