package za.co.riggaroo.kotlincoroutinesexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

/**
 * @author rebeccafranks
 * @since 2018/01/20.
 */

class MainActivity : AppCompatActivity() {

    private val background = newFixedThreadPoolContext(2, "bg")
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        job = launch(background) {
            val result = requestData()
            launch(UI) {
                textViewHello.text = result
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private suspend fun requestData(): String {
        Log.d("MainActivity", "Co - routines start")
        Thread.sleep(3000)
        Log.d("MainActivity", "Co - routines finish")

        return "DONE"
    }
}
