package com.dhl.genricdemo.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhl.genricdemo.R
import com.github.dhl.linkablelog.Log
import kotlinx.android.synthetic.main.activity_kotlin_flow.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class KotlinFlowActivity : AppCompatActivity() {
    //define flow and its type is an Int
    lateinit var flow: Flow<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_flow)

        initUI()
    }

    private fun initUI() {
        setUpFlow()
        setUpClick()
    }

    private fun setUpClick() {
        btnFlowCLick.setOnClickListener {
            it.isEnabled = false
            tvEmit.text = ""
            CoroutineScope(Dispatchers.Main).launch {
                flow.collect {
                    Log.d("SetUpClick", "Collection of $it")
                    tvEmit.text = "${tvEmit.text}\n$it"
                    if (it == 9) {
                        btnFlowCLick.isEnabled = true
                    }
                }
            }
        }
    }

    private fun setUpFlow() {
        flow = flow {
            Log.d("SetUpFlow", "Start Flow")
            (0..10).forEach {
                kotlinx.coroutines.delay(500)
                Log.d("SetUpFlow", "Emitting $it")
                emit(it)
            }
        }.flowOn(Dispatchers.Default)
    }
}