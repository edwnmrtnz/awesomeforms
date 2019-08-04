package com.github.edwnmrtnz.awesomeforms.library

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.appcompat.widget.AppCompatEditText
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

@Styleable("AwesomeFormPhonePrefixEditText")
class AwesomeFormPhonePrefixEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val tvFieldLabel by lazy { findViewById<AppCompatTextView>(R.id.tvFieldLabelTitle) }
    private val tlField by lazy { findViewById<TextInputLayout>(R.id.tlField) }
    private val tvAssistiveText by lazy { findViewById<AppCompatTextView>(R.id.tvAssistiveText) }
    private val prefixDivider by lazy { findViewById<View>(R.id.prefixDivider) }
    private val tvPrefix by lazy { findViewById<AppCompatTextView>(R.id.tvPrefix) }

    @StyleableChild(R2.styleable.AwesomeFormPhonePrefixEditText_fieldStyle)
    internal val etField by lazy { findViewById<AppCompatEditText>(R.id.etField) }

    private var isErrorEnabled = false
    private var assistiveText : String? = null

    init {
        View.inflate(context, R.layout.awesomeform_prefix_edittext, this)
        style(attrs)
        textChangeListener()

        etField.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->

            if(!isErrorEnabled && hasFocus) {
                prefixDivider.layoutParams.width = 6
                prefixDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.AwesomeForm_dividerColor))
            } else if(isErrorEnabled && hasFocus) {
                prefixDivider.layoutParams.width = 6
                prefixDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
            } else {
                prefixDivider.layoutParams.width = 2
                prefixDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.AwesomeForm_dividerColor))
            }

            prefixDivider.requestLayout()
        }
    }

    private fun textChangeListener() {
        etField.addTextChangedListener(object : TextWatcher {
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

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_prefixTextColor)
    fun setPrefixTextColor(color: Int) {
        this.tvPrefix.setTextColor(color)
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_endIconDrawable)
    fun setEndIconDrawable(drawable: Drawable) {
        this.tlField.endIconDrawable = drawable
        this.setEndIconMode(TextInputLayout.END_ICON_CUSTOM)
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_endIconMode)
    fun setEndIconMode(mode: Int = TextInputLayout.END_ICON_NONE) {
        this.tlField.endIconMode = mode
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_fieldLabel)
    fun setFieldLabel(fieldLabel : String) {
        this.tvFieldLabel.text = fieldLabel
        this.tvFieldLabel.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_fieldLabelTextColor)
    fun setFieldLabelTextColor(fieldLabelTextColor : Int) {
        this.tvFieldLabel.setTextColor(fieldLabelTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_assistiveText)
    fun setAssistiveText(assistiveText : String) {
        this.assistiveText = assistiveText
        this.tvAssistiveText.text = assistiveText
        this.tvAssistiveText.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_assistiveTextColor)
    fun setAssistiveTextColor(assistiveTextColor: Int) {
        this.tvAssistiveText.setTextColor(assistiveTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_placeholderText)
    fun setPlaceHolderText(placeHolderText : String) {
        this.etField.hint = placeHolderText
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_placeholderTextColor)
    fun setPlaceHolderTextColor(placeHolderTextColor : Int) {
        this.etField.setHintTextColor(placeHolderTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_android_imeOptions)
    fun setImeOptions(imeOptions : Int) {
        this.etField.imeOptions = imeOptions
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_android_inputType)
    fun setInputType(inputType : Int) {
        this.etField.inputType = inputType
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_android_maxLines)
    fun setMaxLines(maxLine : Int) {
        this.etField.maxLines = maxLine
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_android_maxLength)
    fun setMaxLength(maxLength : Int) {
        this.etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_android_focusable)
    fun setIsFocusable(isFocusable : Boolean) {
        this.etField.isFocusable = isFocusable
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_android_focusableInTouchMode)
    fun setIsFocusableInTouchMode(isFocusableInTouchMode : Boolean) {
        this.etField.isFocusableInTouchMode = isFocusableInTouchMode
    }

    @Attr(R2.styleable.AwesomeFormPhonePrefixEditText_android_clickable)
    fun setIsClickable(isClickable : Boolean) {
        this.etField.isClickable = isClickable
    }

    fun removeError() {
        isErrorEnabled = false
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.material_textinputlayout_box_color)
        tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.material_textinputlayout_box_color)
        prefixDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.AwesomeForm_dividerColor))

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

    fun getEditText() = etField

    fun setText(text : String) = etField.setText(text)

    fun getText() = etField.text.toString()

}