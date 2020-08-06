package com.dhl.genricdemo.screens

import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.dhl.genricdemo.R
import kotlinx.android.synthetic.main.activity_accessibility.*
import java.io.BufferedReader
import java.io.InputStreamReader

class AccessibilityOnOffActivity : AppCompatActivity() {
    var isEnabled = false

    val COMMAND = "pm grant com.dhl.genricdemo android.permission.WRITE_SECURE_SETTINGS"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accessibility)

        runShellCommand(COMMAND)
        onClickButton()
    }

    private val TALKBACK_SERVICE_NAME =
        "com.google.android.marvin.talkback/.TalkBackService"

    private fun updateTalkBackState(enableTalkBack: Boolean) {
        if (enableTalkBack) {
            enableAccessibilityService(TALKBACK_SERVICE_NAME)
        } else {
            disableAccessibilityServices()
        }
    }

    private fun enableAccessibilityService(name: String) {
        Settings.Secure.putString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
            name
        )
        Settings.Secure.putString(contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, "1")
    }

    private fun disableAccessibilityServices() {
        Settings.Secure.putString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
            ""
        )
        Settings.Secure.putString(contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, "0")
    }

    private fun onClickButton() {
        btnOnOff.setOnClickListener {
            isEnabled = !isEnabled
            updateTalkBackState(isEnabled)
        }
    }

    private fun setCallCommand() {
        val process = Runtime.getRuntime()
            .exec("adb shell pm grant com.dhl.genricdemo android.permission.WRITE_SECURE_SETTINGS")
        val bufferedReader = BufferedReader(
            InputStreamReader(process.inputStream)
        )
    }

    @Throws(Exception::class)
    private fun runShellCommand(command: String) {
        val process = Runtime.getRuntime().exec(command)
        process.waitFor()
    }
}