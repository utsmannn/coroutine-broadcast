<p align="center">
  <img src="https://images.unsplash.com/photo-1550935114-99de2f488f47?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=80"/>
</p>

<p align="center">
  <a href="LICENSE"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"></a>
  <a href="https://github.com/utsmannn/coroutine-broadcast/pulls"><img alt="Pull request" src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat"></a>
  <a href="https://kotlinlang.org/docs/reference/coroutines-overview.html"><img alt="Coroutine docs" src="https://img.shields.io/badge/Kotlin-Coroutine-blue?logo=kotlin&style=flat"></a>
  <a href="https://twitter.com/utsmannn"><img alt="Twitter" src="https://img.shields.io/twitter/follow/utsmannn"></a>
  <a href="https://github.com/utsmannn"><img alt="Github" src="https://img.shields.io/github/followers/utsmannn?label=follow&style=social"></a>
  <h1 align="center">Simple Broadcast using Coroutine</h1>
</p>

### Download
```groovy
// on progress
```

### Usage
#### Observing data
```kotlin
Broadcast.with(GlobalScope).observer { key, data ->
    if (key == "your key") {
        // data observable here
        // udpate your ui or logic
    }
}
```

#### Post data
```kotlin
Broadcast.with(GlobalScope).post("your key", dataMessage)
````

### Sample Activity
```kotlin
class MainActivity : AppCompatActivity() {
    private val KEY = "message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observingBroadcast()

        val dataMessage = "This message send from broadcast"
        GlobalScope.launch {
            delay(2000)
            // post data
            Broadcast.with(this).post(KEY, dataMessage)
        }
    }

    // observing data
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