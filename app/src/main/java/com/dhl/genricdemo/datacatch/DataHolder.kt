package com.dhl.genricdemo.datacatch

import com.dhl.genricdemo.datacatch.AppConstants.Companion.ACCESSIBILITY
import com.dhl.genricdemo.datacatch.AppConstants.Companion.APP_ANIMATION
import com.dhl.genricdemo.datacatch.AppConstants.Companion.KOTLIN_FLOW
import com.dhl.genricdemo.datacatch.AppConstants.Companion.TIMER_TOAST


object DataHolder {

    private val listTopic = ArrayList<String>()

    init {
        listTopic.add(KOTLIN_FLOW)
        listTopic.add(APP_ANIMATION)
        listTopic.add(ACCESSIBILITY)
        listTopic.add(TIMER_TOAST)
    }

    fun getTopicList() = listTopic

}