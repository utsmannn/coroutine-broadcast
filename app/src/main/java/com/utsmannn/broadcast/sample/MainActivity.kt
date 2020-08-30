package com.utsmannn.broadcast.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utsmannn.broadcast.Broadcast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val KEY = "message"
    private val KEY2 = "uhuy"
    private val KEY3 = "yoi"

    data class SampleData(
            var message: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observingBroadcast()

        val dataMessage = "This message send from broadcast"
        GlobalScope.launch {
            delay(2000)
            Broadcast.with(this).post(KEY, dataMessage)

            delay(1000)
            Broadcast.with(this).post(KEY2, "uhuy data")

            // post data class
            val data = SampleData(message = "message from data class")
            delay(1000)
            Broadcast.with(this).post(KEY3, data)
        }
    }

    private fun observingBroadcast() {

        // single key
        Broadcast.with(GlobalScope).observer(KEY) { data ->
            GlobalScope.launch(Dispatchers.Main) {
                val message = data as String
                tx_log.text = message
            }
        }

        // multiple key
        Broadcast.with(GlobalScope).observer { key, data ->
            when (key) {
                KEY2 -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        val message = data as String
                        tx_log.text = message
                    }
                }
                KEY3 -> {
                    // observing data class
                    GlobalScope.launch(Dispatchers.Main) {
                        val dataSample = data as SampleData
                        tx_log.text = dataSample.message
                    }
                }
            }
        }
    }
}