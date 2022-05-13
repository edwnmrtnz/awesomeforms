package com.github.edwnmrtnz.awesomeforms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.github.edwnmrtnz.awesomeforms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etFirstName.setError("first name is required")
        binding.etLastName.setError("last name is required")
        binding.etGender.setError("this field is required")
        val adapter = ArrayAdapter(
                this,
                R.layout.awesomeform_dropdown_popup_item,
                resources.getStringArray(R.array.exposed_dropdown_content)
        )

        binding.etGender.setAdapter(adapter)


        binding.etMobileNumber.removeError()

    }
}
