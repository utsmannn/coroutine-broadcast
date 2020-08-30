package com.utsmannn.broadcast.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utsmannn.broadcast.Broadcast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val KEY = "message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observingBroadcast()

        val dataMessage = "This message send from broadcast"
        GlobalScope.launch {
            delay(2000)
            Broadcast.with(this).post(KEY, dataMessage)
        }
    }

    private fun observingBroadcast() {
        Broadcast.with(GlobalScope).observer { key, data ->
            if (key == KEY) {
                GlobalScope.launch(Dispatchers.Main) {
                    val message = data as String
                    tx_log.text = message
                }
            }
        }
    }
}