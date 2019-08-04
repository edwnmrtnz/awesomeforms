package com.github.edwnmrtnz.awesomeforms.library

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.airbnb.paris.annotations.Attr
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.annotations.StyleableChild
import com.airbnb.paris.extensions.style
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by edwinmartinez on July 31, 2019
 */

@Styleable("AwesomeFormSpinnerEditText")
class AwesomeFormSpinnerEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val tvFieldLabel by lazy { findViewById<AppCompatTextView>(R.id.tvFieldLabelTitle) }
    private val tlField by lazy { findViewById<TextInputLayout>(R.id.tlField) }
    private val tvAssistiveText by lazy { findViewById<AppCompatTextView>(R.id.tvAssistiveText) }

    @StyleableChild(R2.styleable.AwesomeFormSpinnerEditText_fieldStyle)
    internal val tvField by lazy { findViewById<AutoCompleteTextView>(R.id.tvField) }

    private var isErrorEnabled = false
    private var assistiveText : String? = null

    init {
        View.inflate(context, R.layout.awesomeform_spinner_edittext, this)
        style(attrs)
        textChangeListener()
    }

    private fun textChangeListener() {
        tvField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(isErrorEnabled) {
                    removeError()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Ignore
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Ignore
            }
        })
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_startIconDrawable)
    fun setStartIconDrawable(drawable: Drawable) {
        this.tlField.startIconDrawable = drawable
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_fieldLabel)
    fun setFieldLabel(fieldLabel : String) {
        this.tvFieldLabel.text = fieldLabel
        this.tvFieldLabel.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_fieldLabelTextColor)
    fun setFieldLabelTextColor(fieldLabelTextColor : Int) {
        this.tvFieldLabel.setTextColor(fieldLabelTextColor)
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_assistiveText)
    fun setAssistiveText(assistiveText : String) {
        this.assistiveText = assistiveText
        this.tvAssistiveText.text = assistiveText
        this.tvAssistiveText.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_assistiveTextColor)
    fun setAssistiveTextColor(assistiveTextColor: Int) {
        this.tvAssistiveText.setTextColor(assistiveTextColor)
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_placeholderText)
    fun setPlaceHolderText(placeHolderText : String) {
        this.tvField.hint = placeHolderText
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_placeholderTextColor)
    fun setPlaceHolderTextColor(placeHolderTextColor : Int) {
        this.tvField.setHintTextColor(placeHolderTextColor)
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_android_maxLines)
    fun setMaxLines(maxLine : Int) {
        this.tvField.maxLines = maxLine
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_android_maxLength)
    fun setMaxLength(maxLength : Int) {
        this.tvField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_android_focusable)
    fun setIsFocusable(isFocusable : Boolean) {
        this.tvField.isFocusable = isFocusable
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_android_focusableInTouchMode)
    fun setIsFocusableInTouchMode(isFocusableInTouchMode : Boolean) {
        this.tvField.isFocusableInTouchMode = isFocusableInTouchMode
    }

    @Attr(R2.styleable.AwesomeFormSpinnerEditText_android_clickable)
    fun setIsClickable(isClickable : Boolean) {
        this.tvField.isClickable = isClickable
    }

    fun removeError() {
        isErrorEnabled = false
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.material_textinputlayout_box_color)
        tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.material_textinputlayout_box_color)

        if(assistiveText != null) {
            tvAssistiveText.visibility = View.VISIBLE
            tvAssistiveText.text = assistiveText
        } else {
            tvAssistiveText.visibility = View.GONE
        }
    }

    fun setError(errorMessage : String) {
        isErrorEnabled = true
        tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
        tvAssistiveText.visibility = View.VISIBLE
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
        tvAssistiveText.text = errorMessage
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.AwesomeForm_color_error)
    }

    fun getAutoCompleteTextView() = tvField

    fun setAdapter(adapter: ArrayAdapter<*>) {
        tvField.setAdapter(adapter)
    }

    fun setText(text : String) = tvField.setText(text)

    fun getText() = tvField.text.toString()

}