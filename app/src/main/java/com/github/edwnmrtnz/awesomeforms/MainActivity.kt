package com.github.edwnmrtnz.awesomeforms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        etFirstName.setError("first name is required")
//        etLastName.setError("last name is required")
//        etNumber.setError("input a number")
//        etPassword.setError("password required")
//        etGender.setError("this field is required")
//        etText.setError("this field is required")
//
//        val adapter = ArrayAdapter(
//            this,
//            R.layout.awesomeform_dropdown_popup_item,
//            resources.getStringArray(R.array.exposed_dropdown_content)
//        )
//
//        etGender.setAdapter(adapter)
    }
}
