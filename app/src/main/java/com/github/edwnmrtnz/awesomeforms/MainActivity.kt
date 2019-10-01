package com.github.edwnmrtnz.awesomeforms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val genderAdapter = ArrayAdapter(
            this,
            R.layout.awesomeform_dropdown_popup_item,
            listOf("Male", "Female")
        )
        etGender.setAdapter(genderAdapter)

    }
}
