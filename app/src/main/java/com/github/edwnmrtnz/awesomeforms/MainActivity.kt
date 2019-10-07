package com.github.edwnmrtnz.awesomeforms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentOne.OnFragmentOneListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        replace(R.id.flContainer, FragmentOne())
    }

    override fun onSave() {
        addToBackStack(R.id.flContainer, FragmentTwo())
    }

}
