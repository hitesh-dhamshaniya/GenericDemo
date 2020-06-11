package com.dhl.genricdemo.datacatch

import com.dhl.genricdemo.datacatch.AppConstants.Companion.APP_ANIMATION
import com.dhl.genricdemo.datacatch.AppConstants.Companion.KOTLIN_FLOW


object DataHolder {

    private val listTopic = ArrayList<String>()

    init {
        listTopic.add(KOTLIN_FLOW)
        listTopic.add(APP_ANIMATION)
    }

    fun getTopicList() = listTopic

}