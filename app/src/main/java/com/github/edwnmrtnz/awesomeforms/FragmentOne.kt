package com.github.edwnmrtnz.awesomeforms


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_fragment_one.view.*
import java.lang.RuntimeException

/**
 * A simple [Fragment] subclass.
 */
class FragmentOne : Fragment() {

    private var onFragmentOneListener : OnFragmentOneListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnFragmentOneListener) {
            onFragmentOneListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentOneListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val suffixAdapter = ArrayAdapter(
            context!!,
            R.layout.awesomeform_dropdown_popup_item,
            listOf("", "Jr", "III", "IV", "V")
        )
        view.etSuffix.setAdapter(suffixAdapter)
    }
    interface OnFragmentOneListener {
        fun onSave()
    }


}
