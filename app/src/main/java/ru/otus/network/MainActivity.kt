package ru.otus.network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if ((applicationContext as NetworkApplication).isStartPackage) {
            setContentView(R.layout.activity_main_start)
        } else {
            setContentView(R.layout.activity_main_finish)
        }
    }
}
