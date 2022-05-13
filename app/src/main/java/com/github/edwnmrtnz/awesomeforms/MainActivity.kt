package com.github.edwnmrtnz.awesomeforms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import com.github.edwnmrtnz.awesomeforms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        if(savedInstanceState == null) {
            binding.etFirstName.setError("First name is required")
            binding.etLastName.setError("Last name is required")
        }

//        binding.etGender.setError("this field is required")
//        val adapter = ArrayAdapter(
//                this,
//                R.layout.awesomeform_dropdown_popup_item,
//                resources.getStringArray(R.array.exposed_dropdown_content)
//        )
//
//        binding.etGender.setAdapter(adapter)
//
//
//        binding.etMobileNumber.removeError()

    }
}
