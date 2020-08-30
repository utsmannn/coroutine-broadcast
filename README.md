<p align="center">
  <img src="https://images.unsplash.com/photo-1550935114-99de2f488f47?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=80"/>
</p>

<h1 align="center">Simple Broadcast using Coroutine</h1>
<p align="center">
  <a href="LICENSE"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"></a>
  <a href="https://bintray.com/kucingapes/utsman/com.utsman.broadcast/_latestVersion"><img alt="License" src="https://api.bintray.com/packages/kucingapes/utsman/com.utsman.broadcast/images/download.svg"></a>
  <a href="https://github.com/utsmannn/coroutine-broadcast/pulls"><img alt="Pull request" src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat"></a>
  <a href="https://kotlinlang.org/docs/reference/coroutines-overview.html"><img alt="Coroutine docs" src="https://img.shields.io/badge/Kotlin-Coroutine-blue?logo=kotlin&style=flat"></a>
  <a href="https://twitter.com/utsmannn"><img alt="Twitter" src="https://img.shields.io/twitter/follow/utsmannn"></a>
  <a href="https://github.com/utsmannn"><img alt="Github" src="https://img.shields.io/github/followers/utsmannn?label=follow&style=social"></a>
  <h3 align="center">Lightweight library for broadcasting using kotlin coroutine</h3>
</p>

---

### Download
```groovy
// add in your dependencies
dependencies {
    // kotlin coroutine required
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.8'

    // add broadcast lib
    implementation 'com.utsman.broadcast:broadcast:1.0.1'
}
```

### Usage
#### Observing data
```kotlin

// single key
Broadcast.with(GlobalScope).observer("your key") {  data ->
    GlobalScope.launch(Dispatchers.Main) {
        // data observable here in main scope
        // update your ui or logic
    }
}

// multiple key
Broadcast.with(GlobalScope).observer { key, data ->
    when (key) {
        "key 1" -> // data key 1
        "key 2" -> // data key 2
    }
}
```

#### Post data
```kotlin
Broadcast.with(GlobalScope).post("your key", "your data any type")
````

### Sample Activity
```kotlin
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
```

---
```
Copyright 2020 Muhammad Utsman

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```