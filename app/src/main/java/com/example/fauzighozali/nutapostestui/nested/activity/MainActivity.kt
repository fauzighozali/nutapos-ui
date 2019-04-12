package com.example.fauzighozali.nutapostestui.nested.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fauzighozali.nutapostestui.R

class MainActivity : AppCompatActivity() {

    var btnSimpan: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSimpan = findViewById(R.id.btn_simpan)
        btnSimpan?.setOnClickListener{
            startActivity(Intent(this, MainTwoActivity::class.java))
        }
    }
}
