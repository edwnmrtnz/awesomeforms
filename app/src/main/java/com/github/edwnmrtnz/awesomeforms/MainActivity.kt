package com.github.edwnmrtnz.awesomeforms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentOne.OnFragmentOneListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            replace(R.id.flContainer, FragmentOne())
    }

    override fun onSave() {
        addToBackStack(R.id.flContainer, FragmentTwo())
    }

}
