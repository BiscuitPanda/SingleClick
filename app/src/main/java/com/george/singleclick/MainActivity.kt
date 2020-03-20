package com.george.singleclick

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.george.singleclick.annotation.SingleClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.setOnClickListener(object : View.OnClickListener {
            @SingleClick
            override fun onClick(v: View?) {
            }

        })

    }
}
